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

public class OwnerOne extends Activity {

    private EditText name,sex,age,idhouse,pnumber,idnumber,address;
    private Button update,delete;

    String id = "";

    JSONObject jsonOb = new JSONObject();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerone);
        init();
    }

    public void init() {
        name = findViewById(R.id.username);
        sex = findViewById(R.id.sex);
        age = findViewById(R.id.age);
        idhouse = findViewById(R.id.id_house);
        pnumber = findViewById(R.id.pnumber);
        idnumber = findViewById(R.id.id_number);
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
                String ONAME = name.getText().toString();
                String OSEX = sex.getText().toString();
                String OHID = idhouse.getText().toString();
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
                    jsonObject.put(KeyValue.OHID, OHID);
                    jsonObject.put(KeyValue.OPNUM, OPNUM);
                    jsonObject.put(KeyValue.OIDCard, OIDCard);
                    jsonObject.put(KeyValue.OADDR, OADDR);
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
        RequestMethod.deleteOwner(this, jsO, new MyInterface.onDeleteOwnerCallback() {
            @Override
            public void Success() {
                Toast.makeText(OwnerOne.this,"删除成功",Toast.LENGTH_SHORT).show();
                Log.e("joker", "删除成功");
                finish();
            }

            @Override
            public void Fail() {

            }
        });
    }

    public void update(final JSONObject jsO){
        RequestMethod.updateOwner(this, jsO, new MyInterface.onUpdateOwnerCallback() {
            @Override
            public void Success() {
                Toast.makeText(OwnerOne.this,"修改成功",Toast.LENGTH_SHORT).show();
                Log.e("joker", "修改成功");
                finish();
            }

            @Override
            public void Fail() {

            }
        });
    }

    public void query(final JSONObject jsO){
        RequestMethod.queryOwnerOne(this,jsO, new MyInterface.onQueryOwnerOneCallback() {
            @Override
            public void Success(JSONObject jsonObject) {
                Log.e("joker", "查询成功");

                try {
                    name.setText(jsonObject.getString(KeyValue.ONAME));
                    sex.setText(jsonObject.getString(KeyValue.OSEX));
                    age.setText(jsonObject.getString(KeyValue.OAGE));
                    idhouse.setText(jsonObject.getString(KeyValue.OHID));
                    pnumber.setText(jsonObject.getString(KeyValue.OPNUM));
                    idnumber.setText(jsonObject.getString(KeyValue.OIDCard));
                    address.setText(jsonObject.getString(KeyValue.OADDR));
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
