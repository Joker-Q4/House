package cn.edu.sau.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.request.RequestMethod;

public class TenantOne extends Activity {

    private EditText username,sex,age,idnumber,R_min_rent,R_max_rent,apartment,R_min_area,R_max_area,R_quarters,R_area,pnumber,address;
    private Button update,delete;

    String id = "";

    JSONObject jsonOb = new JSONObject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenantone);
        init();
    }

    public void init() {
        username = findViewById(R.id.username);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        idnumber = findViewById(R.id.idnumber);
        R_min_rent = findViewById(R.id.R_min_rent);
        R_max_rent = findViewById(R.id.R_max_rent);
        apartment = findViewById(R.id.apartment);
        R_min_area = findViewById(R.id.R_min_area);
        R_max_area = findViewById(R.id.R_max_area);
        R_quarters = findViewById(R.id.R_quarters);
        R_area = findViewById(R.id.R_area);
        pnumber = findViewById(R.id.pnumber);
        address = findViewById(R.id.address);

        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        try {
            jsonOb.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                query(jsonOb);
            }
        }).start();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TNAME = username.getText().toString();
                String TSEX = sex.getText().toString();
                String TAGE = age.getText().toString();
                String TIDCard = idnumber.getText().toString();
                String TMINRENT = R_min_rent.getText().toString();
                String TMAXRENT = R_max_rent.getText().toString();
                String TAPART = apartment.getText().toString();
                String TMINArea = R_min_area.getText().toString();
                String TMAXArea = R_max_area.getText().toString();
                String TRQuarters = R_quarters.getText().toString();
                String TRArea = R_area.getText().toString();
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
                    jsonObject.put(KeyValue.TAPART, TAPART);
                    jsonObject.put(KeyValue.TMINArea, TMINArea);
                    jsonObject.put(KeyValue.TMAXArea, TMAXArea);
                    jsonObject.put(KeyValue.TRQuarters, TRQuarters);
                    jsonObject.put(KeyValue.TRArea, TRArea);
                    jsonObject.put(KeyValue.TPNUM, TPNUM);
                    jsonObject.put(KeyValue.TADDR, TADDR);
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
                //向服务器发送数据
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
        RequestMethod.deleteTenant(this, jsO, new MyInterface.onDeleteTenantCallback() {
            @Override
            public void Success() {
                Toast.makeText(TenantOne.this,"删除成功",Toast.LENGTH_SHORT).show();
                Log.e("joker", "删除成功");
                finish();
            }

            @Override
            public void Fail() {

            }
        });
    }

    public void update(final JSONObject jsO){
        RequestMethod.updateTenant(this, jsO, new MyInterface.onUpdateTenantCallback() {
            @Override
            public void Success() {
                Toast.makeText(TenantOne.this,"修改成功",Toast.LENGTH_SHORT).show();
                Log.e("joker", "修改成功");
                finish();
            }

            @Override
            public void Fail() {

            }
        });
    }

    public void query(final JSONObject jsO){
        RequestMethod.queryTenantOne(this,jsO, new MyInterface.onQueryTenantOneCallback() {
            @Override
            public void Success(JSONObject jsonObject) {
                Log.e("joker", "查询成功");

                try {
                    username.setText(jsonObject.getString(KeyValue.TNAME));
                    sex.setText(jsonObject.getString(KeyValue.TSEX));
                    age.setText(jsonObject.getString(KeyValue.TAGE));
                    idnumber.setText(jsonObject.getString(KeyValue.TIDCard));
                    R_min_rent.setText(jsonObject.getString(KeyValue.TMINRENT));
                    R_max_rent.setText(jsonObject.getString(KeyValue.TMAXRENT));
                    apartment.setText(jsonObject.getString(KeyValue.TAPART));
                    R_min_area.setText(jsonObject.getString(KeyValue.TMINArea));
                    R_max_area.setText(jsonObject.getString(KeyValue.TMAXArea));
                    R_quarters.setText(jsonObject.getString(KeyValue.TRQuarters));
                    R_area.setText(jsonObject.getString(KeyValue.TRArea));
                    pnumber.setText(jsonObject.getString(KeyValue.TPNUM));
                    address.setText(jsonObject.getString(KeyValue.TADDR));
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
}
