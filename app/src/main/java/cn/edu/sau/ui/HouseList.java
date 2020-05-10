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
import cn.edu.sau.adapter.HouseAdapter;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.key.KeyValue;
import cn.edu.sau.pojo.VO.HouseVO;
import cn.edu.sau.request.RequestMethod;

public class HouseList extends Activity {

    private List<HouseVO> houselist = new ArrayList<>();
    private HouseAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houselist);

        // 绑定
        RecyclerView recyclerView = findViewById(R.id.houselist_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        // 添加适配器
        adapter = new HouseAdapter(houselist);
        recyclerView.setAdapter(adapter);

        // 测试用例
        houselist.clear();

        init();
    }

    private void init() {
        Intent intent = getIntent();
        String QMinRent = intent.getStringExtra(KeyValue.QMinRent);
        String QMaxRent = intent.getStringExtra(KeyValue.QMaxRent);
        String QApartment = intent.getStringExtra(KeyValue.QApartment);
        String QMinArea = intent.getStringExtra(KeyValue.QMinArea);
        String QMaxArea = intent.getStringExtra(KeyValue.QMaxArea);
        String QOrientation = intent.getStringExtra(KeyValue.QOrientation);
        String QResidentialQuarters = intent.getStringExtra(KeyValue.QResidentialQuarters);
        String QRespectiveArea = intent.getStringExtra(KeyValue.QRespectiveArea);
        String QStater = intent.getStringExtra(KeyValue.QStater);

        JSONObject jsonObject = new JSONObject();
        //向服务器发送数据
        try {
            jsonObject.put(KeyValue.QMinRent, QMinRent);
            jsonObject.put(KeyValue.QMaxRent, QMaxRent);
            jsonObject.put(KeyValue.QApartment, QApartment);
            jsonObject.put(KeyValue.QMinArea, QMinArea);
            jsonObject.put(KeyValue.QMaxArea, QMaxArea);
            jsonObject.put(KeyValue.QOrientation, QOrientation);
            jsonObject.put(KeyValue.QResidentialQuarters, QResidentialQuarters);
            jsonObject.put(KeyValue.QRespectiveArea, QRespectiveArea);
            jsonObject.put(KeyValue.QStater, QStater);

            query(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void query(final JSONObject jsO){
        RequestMethod.queryHouseList(this,jsO, new MyInterface.onQueryHousesCallback() {
            @Override
            public void Success(JSONArray JsonArray) {
                Log.e("joker", "查询成功");
                Log.e("joker", JsonArray.toString());
                for(int i=0;i<JsonArray.length();i++) {
                    try {
                        HouseVO house = new HouseVO();
                        JSONObject JsonObject = JsonArray.getJSONObject(i);
                        house.setId(Integer.valueOf(JsonObject.getString("id")));
                        house.setImage1(JsonObject.getString(KeyValue.IMAGE1));
                        house.setImage2(JsonObject.getString(KeyValue.IMAGE2));
                        house.setImage3(JsonObject.getString(KeyValue.IMAGE3));
                        house.setApartment(JsonObject.getString(KeyValue.APART));
                        house.setRent(Double.valueOf(JsonObject.getString(KeyValue.RENT)));
                        houselist.add(house);
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
