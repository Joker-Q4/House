package cn.edu.sau.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.request.RequestMethod;
import cn.edu.sau.response.ResponseMethod;
import cn.edu.sau.ui.basic.PictureSelectFragment;
import cn.edu.sau.url.UrlString;

public class FragmentContractOne extends PictureSelectFragment {

    private EditText contract_number,house_number,house_address,owner_name,owner_number,owner_id_number,tenant_name,tenant_number,tenant_id_number,rent,tenancy,agent,agency_fee,contract_date;
    private ImageView image;
    private Button update,delete;

    String image_con;

    JSONObject jsonOb = new JSONObject();

    String id = "";

    String RES_A;

    public static FragmentContractOne newInstance() {
        return new FragmentContractOne();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_contractone;
    }

    @Override
    public void initViews(View view) {
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

        Intent intent = mActivity.getIntent();
        id = intent.getStringExtra("id");
        image_con = intent.getStringExtra(KeyValue.CP);

        try {
            jsonOb.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        update = view.findViewById(R.id.update);
        delete = view.findViewById(R.id.delete);
    }

    public void query(final JSONObject jsO){
        RequestMethod.queryContractOne(mActivity,jsO, new MyInterface.onQueryContractOneCallback() {
            @Override
            public void Success(JSONObject jsonObject) {
                Log.e("joker", "查询成功");

                try {
                    contract_number.setText(jsonObject.getString(KeyValue.CN));
                    house_number.setText(jsonObject.getString(KeyValue.HN));
                    rent.setText(jsonObject.getString(KeyValue.HA));
                    owner_name.setText(jsonObject.getString(KeyValue.ON));
                    owner_number.setText(jsonObject.getString(KeyValue.OP));
                    owner_id_number.setText(jsonObject.getString(KeyValue.OI));
                    tenant_name.setText(jsonObject.getString(KeyValue.RNA));
                    tenant_number.setText(jsonObject.getString(KeyValue.RNU));
                    tenant_id_number.setText(jsonObject.getString(KeyValue.RI));
                    rent.setText(jsonObject.getString(KeyValue.R));
                    tenancy.setText(jsonObject.getString(KeyValue.LT));
                    agent.setText(jsonObject.getString(KeyValue.A));
                    agency_fee.setText(jsonObject.getString(KeyValue.AF));
                    contract_date.setText(jsonObject.getString(KeyValue.CD));

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

    @Override
    public void initEvents() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                query(jsonOb);
            }
        }).start();

        Glide.with(mContext)
                .load(UrlString.BASE_URL + image_con)
                .into(image);

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
//                mPictureIv.setImageBitmap(bitmap);

                String filePath = fileUri.getEncodedPath();
                final String imagePath = Uri.decode(filePath);

                uploadImage(imagePath);

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
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
                String CP = image_con;

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
    }

    public void delete(final JSONObject jsO){
        RequestMethod.deleteContract(mActivity, jsO, new MyInterface.onDeleteContractCallback() {
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
        RequestMethod.updateContract(mActivity, jsO, new MyInterface.onUpdateContractCallback() {
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

    /**
     * 上传图片
     * @param imagePath
     */
    private void uploadImage(String imagePath) {
        new FragmentContractOne.NetworkTask().execute(imagePath);
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
                        .into(image);
                image_con = result;
            } else
                Toast.makeText(mContext,"error!",Toast.LENGTH_SHORT).show();
        }
    }
}
