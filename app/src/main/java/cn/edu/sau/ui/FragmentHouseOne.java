package cn.edu.sau.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
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

import java.util.Timer;
import java.util.TimerTask;

import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.House;
import cn.edu.sau.pojo.VO.HouseVO;
import cn.edu.sau.request.RequestMethod;
import cn.edu.sau.response.ResponseMethod;
import cn.edu.sau.ui.basic.PictureSelectFragment;
import cn.edu.sau.url.UrlString;

public class FragmentHouseOne extends PictureSelectFragment {

    private ImageView imageView,imageView1,imageView2,imageView3;
    private EditText username,phone,rent,apartment,area,floor,orientation,condition,residential_quarters,address,property_right,description;
    private Spinner respective_area;  //下拉框
    private Button update,delete;

//    String image1,image2,image3;

    JSONObject jsonOb = new JSONObject();

    String id = "";

    String RES_A;

    public static FragmentHouseOne newInstance() {
        return new FragmentHouseOne();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_houseone;
    }

    @Override
    public void initViews(View view) {
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

        Intent intent = mActivity.getIntent();
        id = intent.getStringExtra("id");
        KeyValue.image[0] = intent.getStringExtra(KeyValue.IMAGE1);
        KeyValue.image[1] = intent.getStringExtra(KeyValue.IMAGE2);
        KeyValue.image[2] = intent.getStringExtra(KeyValue.IMAGE3);

        try {
            jsonOb.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        update = view.findViewById(R.id.update);
        delete = view.findViewById(R.id.delete);
    }

    @Override
    public void initEvents() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                query(jsonOb);
            }
        }).start();

        Glide.with(mContext)
                .load(UrlString.BASE_URL + KeyValue.image[0])
                .into(imageView1);

        Glide.with(mContext)
                .load(UrlString.BASE_URL + KeyValue.image[1])
                .into(imageView2);

        Glide.with(mContext)
                .load(UrlString.BASE_URL + KeyValue.image[2])
                .into(imageView3);

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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME = username.getText().toString();
                String PHONE = phone.getText().toString();
                String RENT = rent.getText().toString();
                String APART = apartment.getText().toString();
                String AREA = area.getText().toString();
                String FLOOR = floor.getText().toString();
                String ORIEN = orientation.getText().toString();
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
                    jsonObject.put("id", id);

                    update(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("id", id);
                    delete(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        //添加Spinner事件监听
        respective_area.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //          RES_A = m_respective_area[arg2];
                RES_A = getActivity().getResources().getStringArray(R.array.respective_area)[arg2];
         //       Toast.makeText(getActivity(),"你点击了：" + RES_A, Toast.LENGTH_SHORT).show();
                //设置显示当前选择的项
                arg0.setVisibility(View.VISIBLE);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });


        // 设置裁剪图片结果监听
        setOnPictureSelectedListener(new OnPictureSelectedListener() {
            @Override
            public void onPictureSelected(Uri fileUri, Bitmap bitmap) {
//                mPictureIv.setImageBitmap(bitmap);

                String filePath = fileUri.getEncodedPath();
                final String imagePath = Uri.decode(filePath);

                uploadImage(imagePath);

            }
        });
    }

    public void delete(final JSONObject jsO){
        RequestMethod.deleteHouse(mActivity, jsO, new MyInterface.onDeleteHouseCallback() {
            @Override
            public void Success() {
                Toast.makeText(mActivity,"删除成功",Toast.LENGTH_SHORT).show();
                Log.e("joker", "删除成功");
                mActivity.finish();
            }

            @Override
            public void Fail() {

            }
        });
    }

    public void update(final JSONObject jsO){
        RequestMethod.updateHouse(mActivity, jsO, new MyInterface.onUpdateHouseCallback() {
            @Override
            public void Success() {
                Toast.makeText(mActivity,"修改成功",Toast.LENGTH_SHORT).show();
                Log.e("joker", "修改成功");
                mActivity.finish();
            }

            @Override
            public void Fail() {

            }
        });
    }

    public void query(final JSONObject jsO){
        RequestMethod.queryHouseOne(mActivity,jsO, new MyInterface.onQueryHouseOneCallback() {
            @Override
            public void Success(JSONObject jsonObject) {
                Log.e("joker", "查询成功");

                try {

                    username.setText(jsonObject.getString(KeyValue.NAME));
                    phone.setText(jsonObject.getString(KeyValue.PHONE));
                    rent.setText(jsonObject.getString(KeyValue.RENT));
                    apartment.setText(jsonObject.getString(KeyValue.APART));
                    area.setText(jsonObject.getString(KeyValue.AREA));
                    floor.setText(jsonObject.getString(KeyValue.FLOOR));
                    orientation.setText(jsonObject.getString(KeyValue.ORIEN));
                    condition.setText(jsonObject.getString(KeyValue.COND));
                    residential_quarters.setText(jsonObject.getString(KeyValue.RES_Q));
                    address.setText(jsonObject.getString(KeyValue.ADDR));
                    property_right.setText(jsonObject.getString(KeyValue.PRO_R));
                    description.setText(jsonObject.getString(KeyValue.DESC));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
        new FragmentHouseOne.NetworkTask().execute(imagePath);
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
                Log.e(TAG, "图片地址：" + UrlString.BASE_URL + result);
                Glide.with(mContext)
                        .load(UrlString.BASE_URL + result)
                        .into(imageView);
                KeyValue.image[KeyValue.num] = result;
//                KeyValue.num++;
                imageView = null;
            } else
                Toast.makeText(mContext,"error!",Toast.LENGTH_SHORT).show();
        }
    }
}
