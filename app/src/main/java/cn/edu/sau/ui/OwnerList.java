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
import cn.edu.sau.adapter.OwnerAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.pojo.VO.OwnerVO;
import cn.edu.sau.request.RequestMethod;

        public class OwnerList extends Activity {

            private List<OwnerVO> ownerlist = new ArrayList<>();
            private OwnerAdapter adapter;

            @Override
            protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_ownerlist);

                RecyclerView recyclerView = findViewById(R.id.ownerlist_recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
                recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
                recyclerView.setLayoutManager(layoutManager);
                // 添加适配器
                adapter = new OwnerAdapter(ownerlist);
                recyclerView.setAdapter(adapter);

                Intent intent = getIntent();
                String name = intent.getStringExtra("name");

                // 测试用例
                ownerlist.clear();

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
        RequestMethod.queryOwnerList(this, jsO, new MyInterface.onQueryOwnersCallback() {
            @Override
            public void Success(JSONArray JsonArray) {
                Log.e("joker", "查询成功");
                Log.e("joker", JsonArray.toString());
                for(int i=0;i<JsonArray.length();i++) {
                    try {
                        OwnerVO owner = new OwnerVO();
                        JSONObject JsonObject = JsonArray.getJSONObject(i);
                        owner.setId(Integer.valueOf(JsonObject.getString("id")));
                        owner.setName(JsonObject.getString("name"));

                        ownerlist.add(owner);
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
