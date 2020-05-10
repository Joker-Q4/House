package cn.edu.sau.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sau.adapter.ViewPagerAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.request.RequestMethod;
import cn.edu.sau.ui.basic.BaseFragment;

public class CustomerDemandFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater mInflater;
    private View view1,view2,view3;
    private List<String> mTitleList = new ArrayList<>();
    private List<View>mViewList = new ArrayList<>();

    private EditText username,sex,age,idnumber,R_min_rent,R_max_rent,R_min_area,R_max_area,R_quarters,pnumber,address;
    private Button add;
    private Spinner apartment,R_area;  //下拉框

    private EditText tenantname;
    private Button query;

    private String AT = "";
    private String RA = "";

    public static CustomerDemandFragment newInstance() {
        return new CustomerDemandFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_customer_demand;
    }

    @Override
    public void initViews(View view) {
        mViewPager = view.findViewById(R.id.view);
        mTabLayout = view.findViewById(R.id.tabs);
        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.cdlayout_cdadd,null);
        view2 = mInflater.inflate(R.layout.cdlayout_cdquery,null);
        mViewList.add(view1);
        mViewList.add(view2);
        mTitleList.add("房客需求添加");
        mTitleList.add("房客查询");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(mViewList,mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);  //将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        initView1(view1);           // 三个界面的监听事件绑定（初始化）
        initView2(view2);
    }

    public void initView1(View view) {
        username = view.findViewById(R.id.username);
        sex = view.findViewById(R.id.sex);
        age = view.findViewById(R.id.age);
        idnumber = view.findViewById(R.id.idnumber);
        R_min_rent = view.findViewById(R.id.R_min_rent);
        R_max_rent = view.findViewById(R.id.R_max_rent);
        apartment = view.findViewById(R.id.apartment);
        R_min_area = view.findViewById(R.id.R_min_area);
        R_max_area = view.findViewById(R.id.R_max_area);
        R_quarters = view.findViewById(R.id.R_quarters);
        R_area = view.findViewById(R.id.R_area);
        pnumber = view.findViewById(R.id.pnumber);
        address = view.findViewById(R.id.address);
        add = view.findViewById(R.id.add);
    }

    public void initView2(View view) {
        tenantname = view.findViewById(R.id.tenantname);
        query = view.findViewById(R.id.query);
    }

    @Override
    public void initEvents() {

        //添加Spinner事件监听
        apartment.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //          RES_A = m_respective_area[arg2];
                AT = getActivity().getResources().getStringArray(R.array.apartment)[arg2];
     //           Toast.makeText(getActivity(),"你点击了：" + AT, Toast.LENGTH_SHORT).show();
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        //添加Spinner事件监听
        R_area.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //          RES_A = m_respective_area[arg2];
                RA = getActivity().getResources().getStringArray(R.array.respective_area)[arg2];
        //        Toast.makeText(getActivity(),"你点击了：" + RA, Toast.LENGTH_SHORT).show();
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // 添加按钮触发事件
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TNAME = username.getText().toString();
                String TSEX = sex.getText().toString();
                String TAGE = age.getText().toString();
                String TIDCard = idnumber.getText().toString();
                String TMINRENT = R_min_rent.getText().toString();
                String TMAXRENT = R_max_rent.getText().toString();
  //              String TAPART = apartment.getText().toString();
                String TMINArea = R_min_area.getText().toString();
                String TMAXArea = R_max_area.getText().toString();
                String TRQuarters = R_quarters.getText().toString();
  //              String TRArea = R_area.getText().toString();
                String TPNUM = pnumber.getText().toString();
                String TADDR = address.getText().toString();

                JSONObject jsonObject = new JSONObject();
                //向服务器发送数据
                try {
                    jsonObject.put(KeyValue.TNAME, TNAME);
                    jsonObject.put(KeyValue.TSEX, TSEX);
                    jsonObject.put(KeyValue.TAGE, TAGE);
                    jsonObject.put(KeyValue.TIDCard, TIDCard);
                    jsonObject.put(KeyValue.TMINRENT, TMINRENT);
                    jsonObject.put(KeyValue.TMAXRENT, TMAXRENT);
                    jsonObject.put(KeyValue.TAPART, AT);
                    jsonObject.put(KeyValue.TMINArea, TMINArea);
                    jsonObject.put(KeyValue.TMAXArea, TMAXArea);
                    jsonObject.put(KeyValue.TRQuarters, TRQuarters);
                    jsonObject.put(KeyValue.TRArea, RA);
                    jsonObject.put(KeyValue.TPNUM, TPNUM);
                    jsonObject.put(KeyValue.TADDR, TADDR);

                    add(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TenantList.class);
                String name = tenantname.getText().toString().trim();
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }

    // 添加房客信息
    public void add(final JSONObject jsO){
        RequestMethod.addTenantToList(getActivity(),jsO, new MyInterface.onLoginCallback() {
            @Override
            public void Success() {
                Log.e("joker", "添加成功");
            }
            @Override
            public void Fail() {
                Log.e("joker", "添加失败");
            }
        });
    }
}
