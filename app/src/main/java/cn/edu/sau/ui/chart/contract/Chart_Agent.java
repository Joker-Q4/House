package cn.edu.sau.ui.chart.contract;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.request.RequestMethod;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class Chart_Agent extends AppCompatActivity{

    // 柱状图
    private ColumnChartView CAgent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_contract_agent);
        CAgent = findViewById(R.id.CAgent);
        query();
    }

    // 信息统计查询
    public void query(){
        RequestMethod.queryContractStatistics(this, new MyInterface.onStatisticsCallback() {
            @Override
            public void Success(JSONObject jsonObject) {
                // 解析
                initEv(jsonObject);
            }
            @Override
            public void Fail() {
                Log.e("joker", "查询失败");
            }
        });
    }

    public void initEv(JSONObject jsonObject){
        String[] agent = new String[100];    //横坐标
        JSONObject jsonOb = new JSONObject();
        int i = 0;
        Iterator<String> LRQuarters = jsonObject.keys();
        while(LRQuarters.hasNext()){
            // 获得key
            agent[i] = LRQuarters.next();
            if(!agent[i].contains("-")) {
                try {
                    jsonOb.put(agent[i],jsonObject.getString(agent[i]));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
        initLRent(agent, jsonOb);
    }

    // 初始化面积柱状图
    private void initLRent(String[] agent, JSONObject jsonObject){
        int[] columnY = {0, 1, 2, 3, 4, 5};     //纵坐标
        List<AxisValue> axisValues = new ArrayList<>();  //存储X轴标注
        List<AxisValue> axisYValues = new ArrayList<>(); //存储Y轴标注
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> subcolumnValues;             //存储

        for (int k = 0; k < columnY.length; k++) {
            axisYValues.add(new AxisValue(k).setValue(columnY[k]));
        }
        for (int i = 0; i < agent.length; ++i) {

            try {
                subcolumnValues = new ArrayList<>();
                String va = jsonObject.getString(agent[i]);
                Log.e("joker", "数量：" + va);
                subcolumnValues.add(new SubcolumnValue(Integer.valueOf(va), ChartUtils.pickColor()));
                // 点击柱状图就展示数据量
                axisValues.add(new AxisValue(i).setLabel(agent[i]));
                columns.add(new Column(subcolumnValues).setHasLabelsOnlyForSelected(true));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //X轴
        Axis axisX = new Axis(axisValues);
        axisX.setHasLines(false);
        axisX.setTextColor(Color.BLACK);
        //Y轴
        Axis axisY = new Axis(axisYValues);
        axisY.setHasLines(true);           //设置Y轴有线条显示
        axisY.setTextColor(Color.BLACK);   //设置文本颜色
        axisY.setMaxLabelChars(5);         //设置坐标轴标签显示的最大字符数
        //设置柱状图的相关属性
        ColumnChartData CAgentData = new ColumnChartData(columns);
        CAgentData.setAxisXBottom(axisX);   //设置X轴在底部
        CAgentData.setAxisYLeft(axisY);     //设置Y轴在左侧
        CAgent.setColumnChartData(CAgentData);
        CAgent.setValueSelectionEnabled(true);    //设置柱状图可以被选择
        CAgent.setZoomType(ZoomType.HORIZONTAL);  //设置缩放类型为水平缩放
    }
}