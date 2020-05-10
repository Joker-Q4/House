package cn.edu.sau.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import cn.edu.sau.adapter.ViewPagerAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.request.RequestMethod;
import cn.edu.sau.response.ResponseMethod;
import cn.edu.sau.ui.basic.PictureSelectFragment;
import cn.edu.sau.ui.chart.contract.Chart_Agent;
import cn.edu.sau.ui.chart.contract.Chart_Date;
import cn.edu.sau.url.UrlString;

public class HousesLeaseFragment extends PictureSelectFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater mInflater;
    private View view1,view2,view3;
    private List<String> mTitleList = new ArrayList<>();
    private List<View>mViewList = new ArrayList<>();
    private String rs = "";

    private EditText contract_number,house_number,house_address,owner_name,owner_number,owner_id_number,tenant_name,tenant_number,tenant_id_number,rent,tenancy,agent,agency_fee,contract_date;
    private ImageView image;
    private Button add;

    private EditText query_min_date,query_max_date,query_contract_number,query_house_address,query_owner_name;
    private Button query;

    private Button HLDate,HLAgent;

    public static HousesLeaseFragment newInstance() {
        return new HousesLeaseFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_houses_lease;
    }

    @Override
    public void initViews(View view) {
        mViewPager = view.findViewById(R.id.view);
        mTabLayout = view.findViewById(R.id.tabs);
        mInflater = LayoutInflater.from(getActivity());
        view1 = mInflater.inflate(R.layout.hllayout_hladd,null);
        view2 = mInflater.inflate(R.layout.hllayout_hlquery,null);
        view3 = mInflater.inflate(R.layout.hllayout_hlstatistic,null);
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        mTitleList.add("添加合同");
        mTitleList.add("查询合同");
        mTitleList.add("统计合同");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(mViewList,mTitleList);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);  //将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
        initView1(view1);           // 三个界面的监听事件绑定（初始化）
        initView2(view2);
        initView3(view3);
    }
    public void initView1(View view) {
        contract_number = view.findViewById(R.id.contract_number);
        house_number = view.findViewById(R.id.house_number);
        house_address = view.findViewById(R.id.house_address);
        owner_name = view.findViewById(R.id.owner_name);
        owner_number = view.findViewById(R.id.owner_number);
        owner_id_number = view.findViewById(R.id.owner_id_number);
        tenant_name = view.findViewById(R.id.tenant_name);
        tenant_number = view.findViewById(R.id.tenant_number);
        tenant_id_number = view.findViewById(R.id.tenant_id_number);
        rent = view.findViewById(R.id.rent);
        tenancy = view.findViewById(R.id.tenancy);
        agent = view.findViewById(R.id.agent);
        agency_fee = view.findViewById(R.id.agency_fee);
        contract_date = view.findViewById(R.id.contract_date);
        image = view.findViewById(R.id.image);
        add = view.findViewById(R.id.add);
    }

    public void initView2(View view) {
        query_min_date = view.findViewById(R.id.query_min_date);
        query_max_date = view.findViewById(R.id.query_max_date);
        query_contract_number = view.findViewById(R.id.query_contract_number);
        query_house_address = view.findViewById(R.id.query_house_address);
        query_owner_name = view.findViewById(R.id.query_owner_name);
        query = view.findViewById(R.id.query);
    }

    public void initView3(View view) {
        HLDate = view.findViewById(R.id.HLDate);
        HLAgent = view.findViewById(R.id.HLAgent);
    }

    @Override
    public void initEvents() {
        // 设置图片点击监听
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });

        // 设置裁剪图片结果监听
        setOnPictureSelectedListener(new OnPictureSelectedListener() {
            @Override
            public void onPictureSelected(Uri fileUri, Bitmap bitmap) {
                String filePath = fileUri.getEncodedPath();
                final String imagePath = Uri.decode(filePath);

                uploadImage(imagePath);

            }
        });

        // 添加按钮的事件监听
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CN = contract_number.getText().toString();
                String HN = house_number.getText().toString();
                String HA = house_address.getText().toString();
                String ON = owner_name.getText().toString();
                String OP = owner_number.getText().toString();
                String OI = owner_id_number.getText().toString();
                String RNA = tenant_name.getText().toString();
                String RNU = tenant_number.getText().toString();
                String RI = tenant_id_number.getText().toString();
                String R = rent.getText().toString();
                String LT = tenancy.getText().toString();
                String A = agent.getText().toString();
                String AF = agency_fee.getText().toString();
                String CD = contract_date.getText().toString();
                String CP = rs;

                JSONObject jsonObject = new JSONObject();
                //向服务器发送数据
                try {
                    jsonObject.put(KeyValue.CN, CN);
                    jsonObject.put(KeyValue.HN, HN);
                    jsonObject.put(KeyValue.HA, HA);
                    jsonObject.put(KeyValue.ON, ON);
                    jsonObject.put(KeyValue.OP, OP);
                    jsonObject.put(KeyValue.OI, OI);
                    jsonObject.put(KeyValue.RNA, RNA);
                    jsonObject.put(KeyValue.RNU, RNU);
                    jsonObject.put(KeyValue.RI, RI);
                    jsonObject.put(KeyValue.R, R);
                    jsonObject.put(KeyValue.LT, LT);
                    jsonObject.put(KeyValue.A, A);
                    jsonObject.put(KeyValue.AF, AF);
                    jsonObject.put(KeyValue.CD, CD);
                    jsonObject.put(KeyValue.CD, CD);
                    jsonObject.put(KeyValue.CP, CP);

                    add(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        // 查询按钮监听事件
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String QMIND = query_min_date.getText().toString();
                String QMAXD = query_max_date.getText().toString();
                String CN = query_contract_number.getText().toString();
                String HA = query_house_address.getText().toString();
                String ON = query_owner_name.getText().toString();

                Intent intent = new Intent(getActivity(), ContractList.class);
                intent.putExtra(KeyValue.QMIND, QMIND);
                intent.putExtra(KeyValue.QMAXD, QMAXD);
                intent.putExtra(KeyValue.CN, CN);
                intent.putExtra(KeyValue.HA, HA);
                intent.putExtra(KeyValue.ON, ON);
                startActivity(intent);

//                JSONObject jsonObject = new JSONObject();
//                //向服务器发送数据
//                try {
//                    jsonObject.put(KeyValue.QMIND, QMIND);
//                    jsonObject.put(KeyValue.QMAXD, QMAXD);
//                    jsonObject.put(KeyValue.CN, CN);
//                    jsonObject.put(KeyValue.HA, HA);
//                    jsonObject.put(KeyValue.ON, ON);
//
//                    query(jsonObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });

        // 按日期统计数据
        HLDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chart_Date.class);
                startActivity(intent);
            }
        });

        // 按中介人统计数据
        HLAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chart_Agent.class);
                startActivity(intent);
            }
        });
    }

    // 添加合同信息
    public void add(final JSONObject jsO){
        RequestMethod.addContractToList(getActivity(),jsO, new MyInterface.onLoginCallback() {
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

    // 测试 查询
    public void query(final JSONObject jsO){
        RequestMethod.queryContractList(getActivity(),jsO, new MyInterface.onQueryHousesCallback() {
            @Override
            public void Success(JSONArray JsonArray) {
                Log.e("joker", "查询成功");
                Log.e("joker", JsonArray.toString());
            }
            @Override
            public void Fail() {
                Log.e("joker", "查询失败");
            }
        });
    }

    /**
     * 上传图片
     * @param imagePath
     */
    private void uploadImage(String imagePath) {
        new HousesLeaseFragment.NetworkTask().execute(imagePath);
    }

    /**
     * 访问网络AsyncTask,访问网络在子线程进行并返回主线程通知访问的结果
     */
    class NetworkTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return ResponseMethod.doPost(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            if(!"error".equals(result)) {
                Log.i(TAG, "图片地址 " + UrlString.BASE_URL + result);
                Glide.with(mContext)
                        .load(UrlString.BASE_URL + result)
                        .into(image);
                rs = result;
            } else
                Toast.makeText(mContext,"error!",Toast.LENGTH_SHORT).show();
        }
    }
}