package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ICR.com.ListView.ReserveAdapter;
import ICR.com.ListView.Reserved2;
import ICR.com.ListView.Room2;
import ICR.com.ListView.RoomAdapter;
import ICR.com.R;
import ICR.com.dao.croom_readDao;
import ICR.com.dao.havereservedDao;

/**
 * reserved_info,从数据库读入的信息存入该二维String数组，需要的数据，{会议名称，会议id，会议室名，开始时间，结束时间}
 * ReservedList是一维ArrayList<Reserved2>数组，用来实现动态增删，将reserved_info里的所有信息提取类似“101，财务部会议，2,2019-01-10,08:00,09:00”存入
 * adapter 是已预订适配器，不能直接将数组信息显示，必须传给适配器，然后适配器传给ListView显示
 * ListView 用来显示adapter内的信息
 * static_confer_id是BaseActivity中的全局变量，用来存放选择查看的已预订会议id
 */
public class HaveJoinedActivity extends BaseActivity {

    //String[][] reserved_info =  {{"101","财务部会议","2","2019-01-10 08:00:00","2019-01-10 09:00:00"},{"101","财务部会议","2","2019-01-10 08:00:00","2019-01-10 09:00:00"}};
    private String joined_info[][];
    ReserveAdapter adapter;
    ListView listView;
    private ArrayList<Reserved2> JoinedList = new ArrayList<Reserved2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.have_joined);
        hindBar();
        System.out.println("测试1");
        //havereservedDao.sendLoginRequest();
        initFinishedList();
        adapter = new ReserveAdapter(
                HaveJoinedActivity.this,R.layout.reserved_item,JoinedList);//RoomList传给适配器
        listView = (ListView) findViewById(R.id.HaveJoined_room);//选择显示的位置
        //点击事件，可以识别点的是哪一个item，并且能得到对应id即position
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reserved2 reserved = JoinedList.get(position); //定义一个Room2类对象存放点击的那一行的RoomList中的信息
                static_confer_id = reserved.getConfer_id();//获取这个room的名字，传给BaseActivity中的全局变量Static_Room
                Static_Room = reserved.getName();
                Static_Date = reserved.getDate();
                Static_Clock = reserved.getStart();
                Intent intent = new Intent(HaveJoinedActivity.this, JoinedReadActivity.class);
                startActivity(intent);

                //Toast.makeText(HaveReservedActivity.this,static_confer_id,Toast.LENGTH_SHORT).show();

            }
        });
        listView.setAdapter(adapter);
    }

    private void initFinishedList(){
        System.out.println("This is test");

        if(static_flag==1) {
            joined_info = havereservedDao.sendLoginRequest(2);

            for (int i = 0; i < joined_info.length; i++) {
                System.out.println("：   ***开始进入预定构建****" + joined_info[i][1]);
                //Reserved2类：会议室名称，会议名称，会议id，会议日期，开始时间，结束时间
                //reserved_info:{会议室名，会议名称，会议id，开始时间，结束时间}
                String date = null, clock1 = null, clock2 = null;
                date = getDate(joined_info[i][3]);//会议开始日期
                clock1 = getClock(joined_info[i][3]);//会议该日期下开始时间
                clock2 = getClock(joined_info[i][4]);//会议该日期下结束时间
                Reserved2 joined = new Reserved2(joined_info[i][0], joined_info[i][1], joined_info[i][2], date, clock1, clock2);
                JoinedList.add(joined);
            }
        }
    }
}
