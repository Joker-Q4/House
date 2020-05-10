package cn.edu.sau.ui.chart.house;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
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

public class Chart_Rent extends AppCompatActivity {

    // 柱状图
    private ColumnChartView SRent;
//    private ColumnChartData SRentData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_house_rent);
        SRent = findViewById(R.id.SRent);
        query();
    }

    // 信息统计查询
    public void query(){
        RequestMethod.queryHouseStatistics(this, new MyInterface.onStatisticsCallback() {
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
        try {
            String LRent = jsonObject.getString("LRent");
            initLRent(LRent);
//            initLArea();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 初始化面积柱状图
    private void initLRent(String LRent){
        Log.e("joker", "LRent:" + LRent);
//        String[] area = {"0 - 50", "51 - 100", "101 - 150", "151 - 200", "200+"};    //横坐标
        String[] rent = {"0-1000", "1001-1500", "1501-2000", "2001-3000", "3000+"};    //横坐标
        int[] columnY = {0, 1, 2, 3, 4, 5};     //纵坐标
        List<AxisValue> axisValues = new ArrayList<>();  //存储X轴标注
        List<AxisValue> axisYValues = new ArrayList<>(); //存储Y轴标注
        List<Column> columns = new ArrayList<>();
        List<SubcolumnValue> subcolumnValues;             //存储

        String[] Rent = LRent.split(",");
        for (int k = 0; k < columnY.length; k++) {
            axisYValues.add(new AxisValue(k).setValue(columnY[k]));
        }
        for (int i = 0; i < Rent.length; ++i) {
            subcolumnValues = new ArrayList<>();
            for (int j = 0; j < 1; ++j) {
                switch (i + 1) {
                    case 1:
                        subcolumnValues.add(new SubcolumnValue(Integer.valueOf(Rent[0]), ChartUtils.COLOR_BLUE));
                        break;
                    case 2:
                        subcolumnValues.add(new SubcolumnValue(Integer.valueOf(Rent[1]), ChartUtils.COLOR_GREEN));
                        break;
                    case 3:
                        subcolumnValues.add(new SubcolumnValue(Integer.valueOf(Rent[2]), ChartUtils.COLOR_RED));
                        break;
                    case 4:
                        subcolumnValues.add(new SubcolumnValue(Integer.valueOf(Rent[3]), ChartUtils.COLOR_ORANGE));
                        break;
                    case 5:
                        subcolumnValues.add(new SubcolumnValue(Integer.valueOf(Rent[4]), ChartUtils.COLOR_VIOLET));
                        break;
                }
            }
            // 点击柱状图就展示数据量
            axisValues.add(new AxisValue(i).setLabel(rent[i]));
            columns.add(new Column(subcolumnValues).setHasLabelsOnlyForSelected(true));
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
        ColumnChartData SRentData = new ColumnChartData(columns);
        SRentData.setAxisXBottom(axisX);   //设置X轴在底部
        SRentData.setAxisYLeft(axisY);     //设置Y轴在左侧
        SRent.setColumnChartData(SRentData);
        SRent.setValueSelectionEnabled(true);    //设置柱状图可以被选择
        SRent.setZoomType(ZoomType.HORIZONTAL);  //设置缩放类型为水平缩放
    }
}
