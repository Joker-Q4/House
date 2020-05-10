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
import cn.edu.sau.adapter.ContractAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.VO.ContractVO;
import cn.edu.sau.request.RequestMethod;

public class ContractList extends Activity {

    private List<ContractVO> contractlist = new ArrayList<>();
    private ContractAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractlist);

        // 绑定
        RecyclerView recyclerView = findViewById(R.id.contractlist_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        // 添加适配器
        adapter = new ContractAdapter(contractlist);
        recyclerView.setAdapter(adapter);

        // 测试用例
        contractlist.clear();

        init();
    }

    private void init() {
        Intent intent = getIntent();
        String QMIND = intent.getStringExtra(KeyValue.QMIND);
        String QMAXD = intent.getStringExtra(KeyValue.QMAXD);
        String CN = intent.getStringExtra(KeyValue.CN);
        String HA = intent.getStringExtra(KeyValue.HA);
        String ON = intent.getStringExtra(KeyValue.ON);

        JSONObject jsonObject = new JSONObject();
        //向服务器发送数据
        try {
            jsonObject.put(KeyValue.QMIND, QMIND);
            jsonObject.put(KeyValue.QMAXD, QMAXD);
            jsonObject.put(KeyValue.CN, CN);
            jsonObject.put(KeyValue.HA, HA);
            jsonObject.put(KeyValue.ON, ON);

            query(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void query(final JSONObject jsO){
        RequestMethod.queryContractList(this,jsO, new MyInterface.onQueryHousesCallback() {
            @Override
            public void Success(JSONArray JsonArray) {
                Log.e("joker", "查询成功");
                Log.e("joker", JsonArray.toString());
                for(int i=0;i<JsonArray.length();i++) {
                    try {
                        ContractVO contract = new ContractVO();
                        JSONObject JsonObject = JsonArray.getJSONObject(i);

                        contract.setId(Integer.valueOf(JsonObject.getString("id")));
                        contract.setOwner_name(JsonObject.getString(KeyValue.ON));
                        contract.setRenter_name(JsonObject.getString(KeyValue.RNA));
                        contract.setContract_photo(JsonObject.getString(KeyValue.CP));

                        contractlist.add(contract);
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
    }
}
