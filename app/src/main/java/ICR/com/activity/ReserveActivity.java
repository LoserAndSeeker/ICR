package ICR.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ICR.com.ListView.Room2;
import ICR.com.ListView.RoomAdapter;
import ICR.com.R;
import ICR.com.dao.croom_readDao;
//会议室预定界面，详情请对照系统设计文档
/**
 * room_info代表从数据库传入的房间信息，是二维数组，内部数据每一行是这样的{"1","101","30","可用","行政楼1楼"}
 * RoomList是一维ArrayList<Room2>数组，用来实现动态增删，将room_info里的所有信息提取类似"101","30","可用","行政楼1楼"存入
 * adapter 是会议室适配器，不能直接将数组信息显示，必须传给适配器，然后适配器传给ListView显示
 * ListView 用来显示adapter内的信息
 * Static_Room是BaseActivity中的全局变量，用来存放选择的room名称
 */
public class ReserveActivity extends BaseActivity {
    //到时候由数据库传入，这里仅作测试用
  /*  private String[][] room_info = {{"1","101","30","可用","行政楼1楼"},
            {"2","102","30","可用","行政楼1楼"},
            {"3","103","40","可用","行政楼2楼"},
            {"4","104","20","可用","行政楼3楼"},
            {"5","105","50","可用","行政楼4楼"},
            {"6","106","30","可用","行政楼5楼"}};*/

  //String room_info[][]= croom_readDao.sendLoginRequest();
    String room_info[][] ;
    RoomAdapter adapter;
    ListView listView;
    private ArrayList<Room2> RoomList = new ArrayList<Room2>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_layout);
        hindBar();
        //







        //
        initRoomList();
        adapter = new RoomAdapter(
                ReserveActivity.this,R.layout.room_item,RoomList);//RoomList传给适配器
        listView = (ListView) findViewById(R.id.Reserve_room);//选择显示的位置
        //点击事件，可以识别点的是哪一个item，并且能得到对应id即position
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room2 room = RoomList.get(position); //定义一个Room2类对象存放点击的那一行的RoomList中的信息
                Static_Room = room.getName();//获取这个room的名字，传给BaseActivity中的全局变量Static_Room
                Static_Room_id = room.getId();
                if(room.getStatus().equals("不可用"))
                    Toast.makeText(ReserveActivity.this,"抱歉，该会议室暂不可用，请重新选择",Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(ReserveActivity.this, TimeSelectActivity.class);
                    startActivity(intent);
                }
            }
        });
        listView.setAdapter(adapter);
    }

    //初始化RoomList，即上面提到的提取过程，将每一个房间的"101","30","可用","行政楼1楼"提取放入RoomList
    private void initRoomList(){


      /*  new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("开始获取会议室数据");
                       room_info= croom_readDao.sendLoginRequest();

                       for(int i=0;i<5;i++)
                        {
                            System.out.println("   ：传递数据正常"+room_info[i][0]+"-"+room_info[i][1]+"-"+room_info[i][2]+"-"+room_info[i][3]+"-"+room_info[i][4]+"-"+room_info[i][5]);
                        }

                        for(int i=0;i<room_info.length;i++){
                           System.out.println("：   ***开始进入构建UI****"+room_info[i][1]);
                            Room2 room = new Room2(room_info[i][1],room_info[i][2],room_info[i][3],room_info[i][4]);
                            RoomList.add(room);
                        }


                    }
                }).start();*/
      room_info= croom_readDao.sendLoginRequest();
         for(int i=0;i<room_info.length;i++)
         {
                System.out.println("：   ***开始进入构建UI****"+room_info[i][1]);
                Room2 room = new Room2(room_info[i][1],room_info[i][2],room_info[i][3],room_info[i][4],room_info[i][0]);
                RoomList.add(room);
         }
    }
}

