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
import java.util.Timer;
import java.util.TimerTask;
import cn.edu.sau.joker.MyInterface;
import cn.edu.sau.joker.R;
import cn.edu.sau.request.RequestMethod;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class Chart_Stater extends AppCompatActivity{

    /*第二部分*/
    //饼状图
    private PieChartView pieChartView;
    private PieChartData pieCharData;
    private List<SliceValue> sliceValues = new ArrayList<>();
    private int[] pieData = new int[2];
    private int[] color = {
            Color.parseColor("#356fb3")
            , Color.parseColor("#b53633")};  //饼状图每块的颜色
    private String[] stateChar = {"已租", "未租"};  //每块的名字


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_house_stater);

        query();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //饼状图
                pieChartView = findViewById(R.id.SStater);
                pieChartView.setOnValueTouchListener(selectListener);//为饼状图设置事件监听器
                setPieChartData();
                initPieChart();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 1000);//1秒后执行TimeTask的run方法
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
            pieData[0] = Integer.valueOf(jsonObject.getString("statertrue"));
            pieData[1] = Integer.valueOf(jsonObject.getString("staterfalse"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //设置饼状图中的数据
    private void setPieChartData() {
        for (int i = 0; i < pieData.length; ++i) {
            SliceValue sliceValue = new SliceValue((float) pieData[i], color[i]);
            sliceValues.add(sliceValue);//添加到集合中
        }
    }
    //初始化饼状图
    private void initPieChart() {
        pieCharData = new PieChartData();
        pieCharData.setHasLabels(true);                     //显示标签
        pieCharData.setHasLabelsOnlyForSelected(false);     //不用点击显示占的百分比
        pieCharData.setHasLabelsOutside(false);             //数据是否显示在饼图外侧
        pieCharData.setValues(sliceValues);                 //填充数据
        pieCharData.setCenterCircleColor(Color.WHITE);      //设置环形中间的颜色
        pieCharData.setHasCenterCircle(true);               //是否显示中心圆
        pieCharData.setCenterCircleScale(0.5f);             //设置中心圆所占饼图的比例
        pieCharData.setCenterText1("数据");                 //设置中心圆默认显示的文字
        pieChartView.setPieChartData(pieCharData);          //为饼图设置数据
        pieChartView.setValueSelectionEnabled(true);        //选择饼状图中的块会变大
        pieChartView.setAlpha(0.9f);                        //设置透明度
        pieChartView.setCircleFillRatio(1f);                //设置饼图大小,占整个View的比例
    }
    //数据所占的百分比
    private String calPercent(int i) {
        String result = "";
        int sum = 0;
        for(int j = 0; j < pieData.length; j++) {
            sum += pieData[j];
        }
        result = String.format("%.2f", (float) pieData[i] * 100 / sum) + "%";
        return result;
    }
    //饼状图的事件监听器
    PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {
        @Override
        public void onValueDeselected() {
        }
        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            //选择对应图形后，在中间部分显示相应信息
            pieCharData.setCenterText1(stateChar[arg0]);   //中心圆中的第一文本
            pieCharData.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
        }
    };
}
