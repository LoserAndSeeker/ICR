package ICR.com.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import ICR.com.ListView.Time2;
import ICR.com.ListView.TimeAdapter;
import ICR.com.R;

/**
 * rs_info代表从数据库传入的时间信息，是二维数组，内部数据每一行是这样的{"1","财务部会议","老黎","2019-1-1 08:00:00","2019-1-1 08:00:00","2019-1-1 09:00:00","101"}
 * TimeList是一维ArrayList<Room>数组，用来实现动态增删，将rs_info里的所有信息提取类似""2019-1-2 09:00:00","2019-1-2 09:00:00"存入
 * adapter 是时间适配器，不能直接将数组信息显示，必须传给适配器，然后适配器传给ListView显示
 * ListView 用来显示adapter内的信息
 * Static_Time是BaseActivity中的全局变量，用来存放选择的时间段
 */
public class TimeSelectActivity extends BaseActivity {
    //到时候由数据库传入，这里仅作测试用
    private String[][] rs_info = {{"1","财务部会议","老黎","2019-01-01 08:00:00","2019-01-01 08:00:00","2019-01-01 09:00:00","101"},
            {"2","财务部会议","老高","2019-01-01 08:00:00","2019-01-01 09:00:00","2019-01-01 10:00:00","101"},
            {"3","财务部会议","老黎","2019-01-01 08:00:00","2019-01-02 14:00:00","2019-01-02 15:00:00","101"},
            {"4","财务部会议","老黎","2019-01-04 08:00:00","2019-01-01 08:00:00","2019-01-01 09:00:00","102"},
            {"5","财务部会议","老黎","2019-01-05 08:00:00","2019-01-01 09:00:00","2019-01-01 10:00:00","102"},
            {"6","财务部会议","老黎","2019-01-06 08:00:00","2019-01-01 10:00:00","2019-01-01 11:00:00","102"},
            {"7","财务部会议","老黎","2019-01-07 08:00:00","2019-01-01 08:00:00","2019-01-01 09:00:00","103"},
            {"8","财务部会议","老黎","2019-01-01 08:00:00","2019-01-01 08:00:00","2019-01-01 09:00:00","104"},
            {"9","财务部会议","老黎","2019-01-01 08:00:00","2019-01-01 08:00:00","2019-01-01 09:00:00","105"},
            {"10","财务部会议","老黎","2019-1-1 08:00:00","2019-1-1 08:00:00","2019-1-1 09:00:00","106"},
            {"11","财务部会议","老黎","2019-1-1 08:00:00","2019-1-1 08:00:00","2019-1-1 09:00:00","106"},
            {"12","财务部会议","老黎","2019-1-1 08:00:00","2019-1-1 08:00:00","2019-1-1 09:00:00","107"},
            {"13","财务部会议","老黎","2019-1-1 08:00:00","2019-1-1 08:00:00","2019-1-1 09:00:00","108"}};
    TimeAdapter adapter;
    ListView listView;
    private ArrayList<Time2> TimeList = new ArrayList<Time2>();

    int mYear, mMonth, mDay; //从日历选择器中选择时间
    Button date;    //最上面的日期按钮
    final int DATE_DIALOG = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_select);
        hindBar();

        adapter = new TimeAdapter(
                TimeSelectActivity.this,R.layout.time_item,TimeList);//TimeList传给适配器
        listView = (ListView) findViewById(R.id.Reserve_time);//选择显示的位置,对应该页面布局中的ListView标签
        //点击事件，可以识别点的是哪一个item，并且能得到对应id即position
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Time2 time = TimeList.get(position); //定义一个time类对象存放点击的那一行的TimeList中的信息
                Static_Clock = time.getTime();//获取这个时间，传给BaseActivity中的全局变量Static_Clock
                if(time.getCan().equals("可预订")) {
                    Intent intent = new Intent(TimeSelectActivity.this, SubmitReserveActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(TimeSelectActivity.this,"抱歉，该会议室已被预定，请重新选择",Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);

        //日历选择器
        date = (Button) findViewById(R.id.select_date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
                //Toast.makeText(TimeSelectActivity.this,date+"haha"+clock,Toast.LENGTH_SHORT).show();
            }
        });

        //这里应该是用来获取系统日期的，不知道为啥mMonth总是少了1，所以我又自定义了一个month让它加一
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        //System.out.println(Static_Date)
        String month="",day="";
        if(mMonth+1<10){
            int m = mMonth+1;
            month = "0"+m;
        }
        if(mDay<10){
            day = "0"+mDay;
        }
        Static_Date = mYear+"-"+month+"-"+day; //将获取的系统日期给全局变量Static_Date
        date.setText(Static_Date);  //将按钮文本设为选好的日期
        initTimeList();
    }

    /**
     * 全局变量Static_Room，定义在BaseActivity中，记录用户选择预定的会议室名
     * 全局变量Static_Date，定义在BaseActivity中，记录用户选择预定的日期
     * 全局变量Clock[12]，定义在BaseActivity中，存放一天可用来预定的时间列表
     */

    //初始化TimeList，即上面提到的提取过程，
    private void initTimeList(){
        TimeList.clear();
        for(int j=0;j<Clock.length;j++) {
            Time2 time = null;
            int flag = 1;
            for (int i = 0; i < rs_info.length; i++) {
                String date = null, clock1 = null, clock2 = null;
                date = getDate(rs_info[i][4]);//会议开始日期
                clock1 = getClock(rs_info[i][4]);//会议该日期下开始时间
                clock2 = getClock(rs_info[i][5]);//会议该日期下结束时间
                //如果预定信息列表中会议室id与Static_Room对应上，会议开始时间与Static_Date对应上，
                // 会议持续时间与Clock[j]对应上，才可以显示为已预定，否则，显示可预定
                if(Static_Room.equals(rs_info[i][6] ) && Static_Date.equals(date)  && Clock[j].equals(clock1+"-"+clock2)){
                    flag = 0;
                    //System.out.println(rs_info[i][6]+"??"+date+"??"+(clock1+"-"+clock2));
                }

            }
            //如果预定信息列表中会议室id与Static_Room对应上，会议开始时间与Static_Date对应上，
            // 会议持续时间与Clock[j]对应上，才可以显示为已预定，否则，显示可预定
            if(flag==1)
                time = new Time2(Clock[j],"可预订");
            else
                time = new Time2(Clock[j],"已预订");
            TimeList.add(time);
        }
    }

    /**
     * 从这里开始是日期选择器了
     */

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear+1;
            mDay = dayOfMonth;

            //下面这部分是我自己加的，目的是为了实时更新时间列表
            //更改选好的日期
            Static_Date = "";
            Static_Date += mYear+"-";
            if(mMonth<10){
                Static_Date += "0"+mMonth+"-";
            }
            else
                Static_Date +=mMonth+"-";
            if(mDay<10){
                Static_Date += "0"+mDay;
            }
            else
                Static_Date += mDay;

            date.setText(Static_Date);//重设按钮文本
            initTimeList();//更新时间列表
            adapter.notifyDataSetChanged();//更新适配器
        }
    };
}
