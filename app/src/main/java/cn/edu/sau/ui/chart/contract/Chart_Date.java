package cn.edu.sau.ui.chart.contract;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.request.RequestMethod;
import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class Chart_Date extends AppCompatActivity {
    private List<PointValue> pointValues = new ArrayList<>();   // 数据
    private List<AxisValue> axisValues = new ArrayList<>();     // 横坐标
    private LineChartView lineChartView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_contract_date);
        //线形图
        lineChartView = findViewById(R.id.CDate);
        query();
//        setAxisXLables(); //获取x轴的标注
//        setAxisPoints();  //设置坐标点
//        initLineChart();  //初始化线形图
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
        String[] latetime = new String[100];    //横坐标
        JSONObject jsonOb = new JSONObject();
        int len = 0;
        Iterator<String> NewDate = jsonObject.keys();
        while(NewDate.hasNext()){
            // 获得key 并且存入字符串中
            latetime[len] = NewDate.next();
            if(latetime[len].contains("-")) {
                try {
                    jsonOb.put(latetime[len],jsonObject.getString(latetime[len]));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                len++;
            }
        }
        setAxisXLables(latetime, len, jsonOb); // 设置x轴的标注
    }

    //设置X轴的标注
    private void setAxisXLables(String[] latetime, int len, JSONObject jsonObject) {
        Date date;
        Date[] late = new Date[10];
        // 将字符串转换成日期类型
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            for (int i = 0; i < len; i++) {
                late[i] = dateFormat.parse(latetime[i]);
            }
        } catch (ParseException e) {
            Log.e("joker", "转换失败");
            e.printStackTrace();
        }

        // 冒泡对日期进行排序
        try {
            for(int i = 0; i < len - 1; i++) {
                for(int j = 0; j < len - 1 - i; j++){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
                    Date bt=sdf.parse(sdf.format(late[j]));
                    Date et=sdf.parse(sdf.format(late[j+1]));
                    if (!bt.before(et)){
                        date = late[j];
                        late[j] = late[j+1];
                        late[j+1] = date;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        for (int i = 0; i < len; i++) {
            Log.e("joker", "遍历成功:" + dateFormat.format(late[i]));
            axisValues.add(new AxisValue(i).setLabel(dateFormat.format(late[i])));
        }
        setAxisPoints(late, len, jsonObject);  //设置坐标点
    }

    //设置线形图中的每个数据点
    private void setAxisPoints(Date[] late, int len, JSONObject jsonObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        // 获取关键字中的信息并设置
        try {
            for (int i = 0; i < len; i++) {
                Log.e("joker", "数据:" + Integer.valueOf(jsonObject.getString(dateFormat.format(late[i]))));
                pointValues.add(new PointValue(i, Integer.valueOf(jsonObject.getString(dateFormat.format(late[i])))));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initLineChart();  //初始化线形图
    }

    //初始化线形图
    private void initLineChart() {
        //设置线的颜色、形状等属性
        Line line = new Line();
        line.setColor(Color.parseColor("#33b5e5"));
        line.setShape(ValueShape.CIRCLE);  //线形图上数据点的形状为圆形
        line.setCubic(false);              //曲线是否平滑，即是曲线还是折线
        line.setHasLabels(true);           //曲线的数据坐标是否加上备注
        line.setHasLines(true);            //是否显示线条，如果为false 则没有曲线只显示点
        line.setHasPoints(true);           //是否显示圆点，如果为false 则没有圆点只显示线
        line.setValues(pointValues);
        List<Line> lines = new ArrayList<>();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        //X轴
        Axis axisX = new Axis();
        axisX.setHasTiltedLabels(true);    //X轴字体是斜的显示还是直的，true是倾斜显示
        axisX.setTextColor(Color.BLACK);   //设置字体颜色
        axisX.setTextSize(9);              // 设置字体大小，调整为9是最佳，默认12
        axisX.setMaxLabelChars(7);         //设置坐标轴标签显示的最大字符数
        axisX.setValues(axisValues);       //填充X轴的坐标名称
        data.setAxisXBottom(axisX);        //设置x轴在底部
        axisX.setHasLines(true);           //x 轴分割线
        //Y轴
        Axis axisY = new Axis();
        data.setAxisYLeft(axisY);          //设置Y轴在左侧
        axisY.setTextColor(Color.BLACK);   //设置字体颜色
        axisY.setMaxLabelChars(5);         //设置坐标轴标签显示的最大字符数
        //设置线形图的行为属性，如支持缩放、滑动以及平移
        lineChartView.setInteractive(true);
        lineChartView.setZoomType(ZoomType.HORIZONTAL); //设置缩放类型为水平缩放
        lineChartView.setMaxZoom((float) 2);            //最大放大比例
        lineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChartView.setLineChartData(data);
        lineChartView.setVisibility(View.VISIBLE);
    }
}