package cn.edu.sau.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import cn.edu.sau.adapter.OwnerAdapter;
import cn.edu.sau.adapter.ViewPagerAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.VO.OwnerVO;
import cn.edu.sau.request.RequestMethod;
import cn.edu.sau.ui.basic.BaseFragment;

public class HouseOwnerFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater mInflater;
    private View view1,view2;
    private List<String> mTitleList = new ArrayList<>();
    private List<View>mViewList = new ArrayList<>();

//    private List<OwnerVO> ownerlist = new ArrayList<>();
//    private OwnerAdapter adapter;

    private EditText name,sex,age,pnumber,idnumber,address;
    private Button add;

    private EditText ownername;
    private Button query;

    public static HouseOwnerFragment newInstance() {
        return new HouseOwnerFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_house_owner;
    }

    @Override
    public void initViews(View view) {

        mViewPager = view.findViewById(R.id.view);
        mTabLayout = view.findViewById(R.id.tabs);
        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.holayout_hoadd,null);
        view2 = mInflater.inflate(R.layout.holayout_hoquery,null);
        mViewList.add(view1);
        mViewList.add(view2);
        mTitleList.add("房主信息添加");
        mTitleList.add("房主信息查询");
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

/*    private void init() {
        Intent intent = getIntent();
        String QMinRent = intent.getStringExtra(KeyValue.QMinRent);
        String QMaxRent = intent.getStringExtra(KeyValue.QMaxRent);
        String QApartment = intent.getStringExtra(KeyValue.QApartment);
        String QMinArea = intent.getStringExtra(KeyValue.QMinArea);
        String QMaxArea = intent.getStringExtra(KeyValue.QMaxArea);
        String QOrientation = intent.getStringExtra(KeyValue.QOrientation);
        String QResidentialQuarters = intent.getStringExtra(KeyValue.QResidentialQuarters);
        String QRespectiveArea = intent.getStringExtra(KeyValue.QRespectiveArea);
        String QStater = intent.getStringExtra(KeyValue.QStater);

        JSONObject jsonObject = new JSONObject();
        //向服务器发送数据
        try {
            jsonObject.put(KeyValue.QMinRent, QMinRent);
            jsonObject.put(KeyValue.QMaxRent, QMaxRent);
            jsonObject.put(KeyValue.QApartment, QApartment);
            jsonObject.put(KeyValue.QMinArea, QMinArea);
            jsonObject.put(KeyValue.QMaxArea, QMaxArea);
            jsonObject.put(KeyValue.QOrientation, QOrientation);
            jsonObject.put(KeyValue.QResidentialQuarters, QResidentialQuarters);
            jsonObject.put(KeyValue.QRespectiveArea, QRespectiveArea);
            jsonObject.put(KeyValue.QStater, QStater);

            query();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/



    public void initView1(View view) {
        name = view.findViewById(R.id.username);
        sex = view.findViewById(R.id.sex);
        age = view.findViewById(R.id.age);
        pnumber = view.findViewById(R.id.pnumber);
        idnumber = view.findViewById(R.id.id_number);
        address = view.findViewById(R.id.address);
        add = view.findViewById(R.id.add);
    }
    public void initView2(View view) {
        ownername = view.findViewById(R.id.query_owner);
        query = view.findViewById(R.id.query);
    }

    @Override
    public void initEvents() {
        // 添加按钮触发事件
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ONAME = name.getText().toString();
                String OSEX = sex.getText().toString();
                String OAGE = age.getText().toString();
                String OPNUM = pnumber.getText().toString();
                String OIDCard = idnumber.getText().toString();
                String OADDR = address.getText().toString();

                JSONObject jsonObject = new JSONObject();
                //向服务器发送数据
                try {
                    jsonObject.put(KeyValue.ONAME, ONAME);
                    jsonObject.put(KeyValue.OSEX, OSEX);
                    jsonObject.put(KeyValue.OAGE, OAGE);
                    jsonObject.put(KeyValue.OPNUM, OPNUM);
                    jsonObject.put(KeyValue.OIDCard, OIDCard);
                    jsonObject.put(KeyValue.OADDR, OADDR);

                    add(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),OwnerList.class);
                String name = ownername.getText().toString().trim();
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }

    // 添加房主信息
    public void add(final JSONObject jsO){
        RequestMethod.addOwnerToList(getActivity(),jsO, new MyInterface.onLoginCallback() {
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
