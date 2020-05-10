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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import cn.edu.sau.ui.chart.house.Chart_Area;
import cn.edu.sau.ui.chart.house.Chart_Rent;
import cn.edu.sau.ui.chart.house.Chart_Residential_Quarters;
import cn.edu.sau.ui.chart.house.Chart_Respective_Area;
import cn.edu.sau.ui.chart.house.Chart_Stater;
import cn.edu.sau.url.UrlString;

public class HousingInfoFragment extends PictureSelectFragment{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater mInflater;
    private View view1,view2,view3;
    private List<String> mTitleList = new ArrayList<>();
    private List<View>mViewList = new ArrayList<>();

    // 第一个界面
    private ImageView imageView,imageView1,imageView2,imageView3;
    private EditText username,phone,rent,area,floor,condition,residential_quarters,address,property_right,description;
    private Spinner respective_area,apartment,orientation;  //下拉框
    private Button add;

    // 第二个界面
    private EditText query_min_rent,query_max_rent,query_min_area,query_max_area,query_residential_quarters,query_respective_area,query_stater;
    private Button query;
    private Spinner query_apartment,query_orientation;  //下拉框

    // 第三个界面
    private Button LArea,LRent,LRQuarters,LRArea,LStater;

    // 关键字
    String RES_A = "";
    String APART = "";
    String ORIEN = "";
    String QAT = "";
    String QOT = "";

    public static HousingInfoFragment newInstance() {
        return new HousingInfoFragment();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_housing_information;
    }

    @Override
    public void initViews(View view) {
        mViewPager = view.findViewById(R.id.view);
        mTabLayout = view.findViewById(R.id.tabs);
 //       mPictureIv = view.findViewById(R.id.frag_picture_iv);
        mInflater = LayoutInflater.from(getActivity());
        // 获取到三个布局
        view1 = mInflater.inflate(R.layout.hlayout_hadd,null);
        view2 = mInflater.inflate(R.layout.hlayout_hquery,null);
        view3 = mInflater.inflate(R.layout.hlayout_hstatistic,null);
        // 将布局加入列表
        mViewList.add(view1);
        mViewList.add(view2);
        mViewList.add(view3);
        // 设置标题
        mTitleList.add("房屋添加");
        mTitleList.add("房屋查询");
        mTitleList.add("房屋统计");
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);    //设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));   //添加选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(mViewList,mTitleList);
        mViewPager.setAdapter(mAdapter);    //给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);     //将TabLayout和ViewPager关联起来。
        mTabLayout.setTabsFromPagerAdapter(mAdapter);  //给Tabs设置适配器
        initView1(view1);           // 三个界面的监听事件绑定（初始化）
        initView2(view2);
        initView3(view3);
    }

    // 第一个界面的初始化
    public void initView1(View view){
        username = view.findViewById(R.id.username);
        phone = view.findViewById(R.id.phone);
        rent = view.findViewById(R.id.rent);
        apartment = view.findViewById(R.id.apartment);
        area = view.findViewById(R.id.area);
        floor = view.findViewById(R.id.floor);
        orientation = view.findViewById(R.id.orientation);
        condition = view.findViewById(R.id.condition);
        residential_quarters = view.findViewById(R.id.residential_quarters);
        address = view.findViewById(R.id.address);
        property_right = view.findViewById(R.id.property_right);
        description = view.findViewById(R.id.description);
        imageView1 = view.findViewById(R.id.image1);
        imageView2 = view.findViewById(R.id.image2);
        imageView3 = view.findViewById(R.id.image3);
        respective_area = view.findViewById(R.id.respective_area);
        add = view.findViewById(R.id.add);
    }

    // 第二个界面的初始化
    public void initView2(View view){
        query_min_rent = view.findViewById(R.id.query_min_rent);
        query_max_rent = view.findViewById(R.id.query_max_rent);
        query_apartment = view.findViewById(R.id.query_apartment);
        query_min_area = view.findViewById(R.id.query_min_area);
        query_max_area = view.findViewById(R.id.query_max_area);
        query_orientation = view.findViewById(R.id.query_orientation);
        query_residential_quarters= view.findViewById(R.id.query_residential_quarters);
        query_respective_area = view.findViewById(R.id.query_respective_area);
        query_stater= view.findViewById(R.id.query_stater);
        query = view.findViewById(R.id.query);
    }

    // 第三个界面的初始化
    public void initView3(View view){
        LArea = view.findViewById(R.id.LArea);
        LRent = view.findViewById(R.id.LRent);
        LRQuarters = view.findViewById(R.id.LRQuarters);
        LRArea = view.findViewById(R.id.LRArea);
        LStater = view.findViewById(R.id.LStater);
    }

    @Override
    public void initEvents() {
        // 设置图片点击监听
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView = imageView1;
                KeyValue.num = 0;
                selectPicture();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView = imageView2;
                KeyValue.num = 1;
                selectPicture();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView = imageView3;
                KeyValue.num = 2;
                selectPicture();
            }
        });

        // 添加Spinner事件监听
        respective_area.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
      //          RES_A = m_respective_area[arg2];
                // 字符串写在了array下面
                  RES_A = getActivity().getResources().getStringArray(R.array.respective_area)[arg2];
     //           Toast.makeText(getActivity(),"你点击了：" + RES_A, Toast.LENGTH_SHORT).show();
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // 添加Spinner事件监听
        apartment.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                APART = getActivity().getResources().getStringArray(R.array.apartment)[arg2];
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // 添加Spinner事件监听
        orientation.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                ORIEN = getActivity().getResources().getStringArray(R.array.orientation)[arg2];
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // 添加Spinner事件监听
        query_apartment.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                QAT = getActivity().getResources().getStringArray(R.array.apartment)[arg2];
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });

        // 添加Spinner事件监听
        query_orientation.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                QOT = getActivity().getResources().getStringArray(R.array.orientation)[arg2];
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
                String NAME = username.getText().toString();
                String PHONE = phone.getText().toString();
                String RENT = rent.getText().toString();
//                String APART = apartment.getText().toString();
                String AREA = area.getText().toString();
                String FLOOR = floor.getText().toString();
    //            String ORIEN = orientation.getText().toString();
                String COND = condition.getText().toString();
                String RES_Q = residential_quarters.getText().toString();
                String ADDR = address.getText().toString();
                String PRO_R = property_right.getText().toString();
                String DESC = description.getText().toString();
                String IMAGE1 = KeyValue.image[0];
                String IMAGE2 = KeyValue.image[1];
                String IMAGE3 = KeyValue.image[2];

                JSONObject jsonObject = new JSONObject();
                //向服务器发送数据
                try {
                    jsonObject.put(KeyValue.NAME, NAME);
                    jsonObject.put(KeyValue.PHONE, PHONE);
                    jsonObject.put(KeyValue.RENT, RENT);
                    jsonObject.put(KeyValue.APART, APART);
                    jsonObject.put(KeyValue.AREA, AREA);
                    jsonObject.put(KeyValue.FLOOR, FLOOR);
                    jsonObject.put(KeyValue.ORIEN, ORIEN);
                    jsonObject.put(KeyValue.COND, COND);
                    jsonObject.put(KeyValue.RES_Q, RES_Q);
                    jsonObject.put(KeyValue.RES_A, RES_A);
                    jsonObject.put(KeyValue.ADDR, ADDR);
                    jsonObject.put(KeyValue.PRO_R, PRO_R);
                    jsonObject.put(KeyValue.DESC, DESC);
                    jsonObject.put(KeyValue.IMAGE1, IMAGE1);
                    jsonObject.put(KeyValue.IMAGE2, IMAGE2);
                    jsonObject.put(KeyValue.IMAGE3, IMAGE3);

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
                String QMinRent = query_min_rent.getText().toString();
                String QMaxRent = query_max_rent.getText().toString();
         //       String QApartment = query_apartment.getText().toString();
                String QMinArea = query_min_area.getText().toString();
                String QMaxArea = query_max_area.getText().toString();
         //       String QOrientation = query_orientation.getText().toString();
                String QResidentialQuarters = query_residential_quarters.getText().toString();
                String QRespectiveArea = query_respective_area.getText().toString();
                String QStater = query_stater.getText().toString();

                Intent intent = new Intent(getActivity(), HouseList.class);
                intent.putExtra(KeyValue.QMinRent, QMinRent);
                intent.putExtra(KeyValue.QMaxRent, QMaxRent);
                intent.putExtra(KeyValue.QApartment, QAT);
                intent.putExtra(KeyValue.QMinArea, QMinArea);
                intent.putExtra(KeyValue.QMaxArea, QMaxArea);
                intent.putExtra(KeyValue.QOrientation, QOT);
                intent.putExtra(KeyValue.QResidentialQuarters, QResidentialQuarters);
                intent.putExtra(KeyValue.QRespectiveArea, QRespectiveArea);
                intent.putExtra(KeyValue.QStater, QStater);

                startActivity(intent);
            }
        });

        // 查询面积统计数据
        LArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chart_Area.class);
                startActivity(intent);
            }
        });

        // 查询租金统计数据
        LRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chart_Rent.class);
                startActivity(intent);
            }
        });

        // 查询小区统计数据
        LRQuarters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chart_Residential_Quarters.class);
                startActivity(intent);
            }
        });

        // 查询区域统计数据
        LRArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chart_Respective_Area.class);
                startActivity(intent);
            }
        });

        // 查询状态统计数据
        LStater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chart_Stater.class);
                startActivity(intent);
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
    }

    // 添加房屋信息
    public void add(final JSONObject jsO){
        RequestMethod.addHouseToList(getActivity(),jsO, new MyInterface.onLoginCallback() {
            @Override
            public void Success() {
                Log.e("joker", "添加成功");
                Toast.makeText(getActivity(),"添加成功!",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void Fail() {
                Log.e("joker", "添加失败");
                Toast.makeText(getActivity(),"添加失败!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 上传图片
     * @param imagePath
     */
    private void uploadImage(String imagePath) {
        new HousingInfoFragment.NetworkTask().execute(imagePath);
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
                Log.i(TAG, "图片地址：" + UrlString.BASE_URL + result);
                Glide.with(mContext)
                        .load(UrlString.BASE_URL + result)
                        .into(imageView);
                KeyValue.image[KeyValue.num] = result;
                imageView = null;
            } else
                Toast.makeText(mContext,"error!",Toast.LENGTH_SHORT).show();
        }
    }
}
