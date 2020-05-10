package cn.edu.sau.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.request.RequestMethod;

public class Login extends Activity implements View.OnClickListener{

    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private CheckBox rememberPass;
    private TextView register;
    private TextView exchange;
    private String account;
    private String password;
    private String acc = "";
    private String pass = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //绑定按钮
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPass = findViewById(R.id.remember);
        login = findViewById(R.id.login);
        exchange = findViewById(R.id.change);
        //监听事件
        login.setOnClickListener(this);
        exchange.setOnClickListener(this);
    }

    public void register (final JSONObject jsO){
        RequestMethod.RequestLogin(this,jsO, new MyInterface.onLoginCallback() {
            @Override
            public void Success() {
                Log.e("joker", "登陆成功");
                Intent intent = new Intent(Login.this,MainActivity.class);
                Toast.makeText(Login.this,"登陆成功",Toast.LENGTH_SHORT).show();
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
            case R.id.login:

                // TODO 测试 如果需要把把下面的注释打开，即可连接服务器验证登陆
                Intent intent = new Intent(Login.this,MainActivity.class);
                Toast.makeText(Login.this,"登陆成功",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

             /*   String user = accountEdit.getText().toString();
                String pwd = passwordEdit.getText().toString();
                //向服务器发送数据
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put(KeyValue.USER, user);
                    jsonObject.put(KeyValue.PWD, pwd);
                    register(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                break;
            case R.id.change:
                Intent in = new Intent(Login.this,AdminLogin.class);
                startActivity(in);
                finish();
                break;
        }

    }

/*
        //是否记住密码
        boolean isRemember = pref.getBoolean("remember",false);
        if(isRemember){
            account = pref.getString("account","");
            password = pref.getString("password","");
          //  msgList =  pref.getString("msgList","");
            accountEdit.setText(account);
            passwordEdit.setText(password);
            rememberPass.setChecked(true);
        }
        try {
            Intent intent0 = getIntent();
            acc = intent0.getStringExtra("account");
            pass = intent0.getStringExtra("password");

            //判断是否记住
            if(isRemember) {
                String acc1 = accountEdit.getText().toString().trim();
                String pass1 = passwordEdit.getText().toString().trim();
                editor = pref.edit();
                if(rememberPass.isChecked()){
                    editor.putBoolean("remember",true);
                    editor.putString("account",acc1);
                    editor.putString("password",pass1);
                } else {
                    editor.clear();
                }
                editor.commit();
            }

            //判断传递值是否为空
            if(!(acc.equals("")&&pass.equals(""))){
                accountEdit.setText(acc);
                passwordEdit.setText(pass);
            }
        }catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            //登陆事件
            case R.id.login:
                String account1 = accountEdit.getText().toString().trim();
                String password1 = passwordEdit.getText().toString().trim();

                BmobUser us = new BmobUser();
                us.setUsername(account1);
                us.setPassword(password1);

                user = new MyUser();
                user.setUsername(account1);
                user.setPassword(password1);
                MyUser.setUni(account1);

                //记住密码
                editor = pref.edit();
                if(rememberPass.isChecked()){
                    editor.putBoolean("remember",true);
                    editor.putString("account",account1);
                    editor.putString("password",password1);
                } else {
                    editor.clear();
                }
                editor.commit();

                // 登录验证
                us.login(new SaveListener<BmobUser>() {

                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(login.this, "登陆成功！", Toast.LENGTH_SHORT).show();

                            Intent intent1 = new Intent(login.this,MainActivity.class);
                            startActivity(intent1);
                            finish();
                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                        }else{
                            Toast.makeText(login.this,"用户名无效，请重新注册！",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
                //点击登录按钮，跳转到登录界面
            case R.id.zhuce:
                Intent intent2 = new Intent(this,Register.class);
                startActivity(intent2);
                finish();
                break;
        }
    }*/
}
