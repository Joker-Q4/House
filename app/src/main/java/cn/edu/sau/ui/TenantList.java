package cn.edu.sau.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sau.adapter.TenantAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.pojo.VO.TenantVO;
import cn.edu.sau.request.RequestMethod;

public class TenantList extends Activity {

    private List<TenantVO> tenantList = new ArrayList<>();
    private TenantAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tenantlist);

        RecyclerView recyclerView = findViewById(R.id.tenantlist_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        // 添加适配器
        adapter = new TenantAdapter(tenantList);
        recyclerView.setAdapter(adapter);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        // 测试用例
        tenantList.clear();

        JSONObject jsonObject = new JSONObject();
        try {
            if(name.equals("")) {
                jsonObject.put("name", "");
            } else {
                jsonObject.put("name", name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        query(jsonObject);
    }

    public void query(final JSONObject jsO){
        RequestMethod.queryTenantList(this, jsO, new MyInterface.onQueryTenantsCallback() {
            @Override
            public void Success(JSONArray JsonArray) {
                Log.e("joker", "查询成功");
                Log.e("joker", JsonArray.toString());
                for(int i=0;i<JsonArray.length();i++) {
                    try {
                        TenantVO tenant = new TenantVO();
                        JSONObject JsonObject = JsonArray.getJSONObject(i);
                        tenant.setId(Integer.valueOf(JsonObject.getString("id")));
                        tenant.setName(JsonObject.getString("name"));
                        tenantList.add(tenant);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void Fail() {
                Log.e("joker", "查询失败");
            }
        });

        // 刷新显示
        adapter.notifyItemInserted(0);
    }
}
