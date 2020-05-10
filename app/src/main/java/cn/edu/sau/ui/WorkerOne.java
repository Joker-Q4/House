package cn.edu.sau.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.request.RequestMethod;

public class WorkerOne extends Activity implements View.OnClickListener {

    private TextView name;
    private EditText password;
    private Button update;
    private Button delete;
    private String workername = "";
    private String workerpassword = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wokerone);
        name = findViewById(R.id.onename);
        password = findViewById(R.id.onepassword);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);

        // 获取上一个页面传进来的信息
        try {
            Intent intent = getIntent();
            workername = intent.getStringExtra("workername");
            workerpassword = intent.getStringExtra("workerpassword");
        }catch (Exception e) {}
        if(!workername.equals("") && !workerpassword.equals("")){
            name.setText(workername);
            password.setText(workerpassword);
        }
    }

    /**
     * 修改工作人员的账号密码
     * 可重用登陆检测方法
     * @param jsO
     */
    public void update(final JSONObject jsO){
        RequestMethod.updateWokerList(this,jsO, new MyInterface.onLoginCallback() {
            @Override
            public void Success() {
                Log.e("joker", "修改成功");
            }
            @Override
            public void Fail() {
                Log.e("joker", "修改失败");
            }
        });
    }

    /**
     * 删除一个工作人员
     * 可重用登陆检测方法
     * @param jsO
     */
    public void delete(final JSONObject jsO){
        RequestMethod.delWorkerFromList(this,jsO, new MyInterface.onLoginCallback() {
            @Override
            public void Success() {
                Log.e("joker", "删除成功");
            }
            @Override
            public void Fail() {
                Log.e("joker", "删除失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        String user = name.getText().toString();
        String pwd = password.getText().toString();
        JSONObject jsonObject = new JSONObject();
        switch (v.getId()){
            case R.id.update:
                //向服务器发送数据
                try {
                    jsonObject.put(KeyValue.USER, user);
                    jsonObject.put(KeyValue.PWD, pwd);
                    update(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
                break;
            case R.id.delete:
                //向服务器发送数据
                try {
                    jsonObject.put(KeyValue.USER, user);
                    jsonObject.put(KeyValue.PWD, pwd);
                    delete(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                finish();
                break;
        }
    }
}
