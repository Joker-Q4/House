package cn.edu.sau.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.request.RequestMethod;

public class AdminLogin extends Activity implements View.OnClickListener {

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private CheckBox rememberPass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //绑定按钮
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember);
        login = findViewById(R.id.adminlogin);
        //监听事件
        login.setOnClickListener(this);
    }

    public void register (final JSONObject jsO){
        RequestMethod.adminRequestLogin(this,jsO, new MyInterface.onLoginCallback() {
            @Override
            public void Success() {
                Log.e("joker", "登陆成功");
                Intent intent = new Intent(AdminLogin.this,WorkerList.class);
                Toast.makeText(AdminLogin.this,"登陆成功",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
            @Override
            public void Fail() {
                Log.e("joker", "登陆失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.adminlogin:

                Intent intent = new Intent(AdminLogin.this,WorkerList.class);
                Toast.makeText(AdminLogin.this,"登陆成功",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

//                String user = accountEdit.getText().toString();
//                String pwd = passwordEdit.getText().toString();，
//                //向服务器发送数据
//                JSONObject jsonObject = new JSONObject();
//                try {
//                    jsonObject.put(KeyValue.USER, user);
//                    jsonObject.put(KeyValue.PWD, pwd);
//                    register(jsonObject);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
                break;
        }
    }
}
