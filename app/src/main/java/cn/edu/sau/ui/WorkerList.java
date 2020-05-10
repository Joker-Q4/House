package cn.edu.sau.ui;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sau.adapter.WorkerAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.Worker;
import cn.edu.sau.request.RequestMethod;

public class WorkerList extends Activity implements View.OnClickListener {

    private List<Worker> workerList = new ArrayList<Worker>();
    private WorkerAdapter adapter;
    private EditText name;
    private EditText password;
    private Button add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wokerlist);

        name = findViewById(R.id.worker_name);
        password = findViewById(R.id.worker_pass);
        add = findViewById(R.id.add_one);
        add.setOnClickListener(this);

        // 绑定
        RecyclerView recyclerView = findViewById(R.id.workerlist_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        // 添加适配器
        adapter = new WorkerAdapter(workerList);
        recyclerView.setAdapter(adapter);

        // 测试用例
        workerList.clear();

        RequestMethod.queryWorkerList(this, new MyInterface.onQueryWorkersCallback() {
            @Override
            public void Success(JSONArray JsonArray) {
                Log.e("joker", "列表查询成功");
      //          Worker wo = new Worker();
                for(int i=0;i<JsonArray.length();i++) {
                    try {
                        Worker worker = new Worker();
                        JSONObject JsonObject = JsonArray.getJSONObject(i);
                   //     Log.e("joker", JsonObject.getString("name"));
                  //      Log.e("joker", JsonObject.getString("password"));
                        worker.setName(JsonObject.getString("name"));
                        worker.setPassword(JsonObject.getString("password"));
                        workerList.add(worker);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void Fail() {
                Log.e("joker", "列表查询失败");
            }
        });

        // 刷新显示
        adapter.notifyItemInserted(workerList.size() - 1);

    }

    public void add(final JSONObject jsO){
        RequestMethod.addWorkerToList(this,jsO, new MyInterface.onLoginCallback() {
            @Override
            public void Success() {
                Log.e("joker", "添加成功");
                Toast.makeText(WorkerList.this,"添加成功",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void Fail() {
                Log.e("joker", "添加失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        String user = name.getText().toString();
        String pwd = password.getText().toString();
        JSONObject jsonObject = new JSONObject();
        switch (v.getId()){
            case R.id.add_one:
                //向服务器发送数据
                try {
                    jsonObject.put(KeyValue.USER, user);
                    jsonObject.put(KeyValue.PWD, pwd);
                    add(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
