package ICR.com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ICR.com.ListView.Room2;
import ICR.com.ListView.RoomAdapter;
import ICR.com.ListView.StatusAdapter;
import ICR.com.R;
import ICR.com.dao.conference_set_statusDao;
import ICR.com.dao.croom_readDao;

/**
 * 会议室状态设置界面，管理员可以强制修改会议室状态，包括占用，空闲，故障三个状态，详情请对照系统设计文档
 * room_info代表从数据库传入的房间信息，是二维数组，内部数据每一行是这样的{"1","101","30","可用","行政楼1楼"}
 * RoomList是一维ArrayList<Room2>数组，用来实现动态增删，将room_info里的所有信息提取类似"101","30","可用","行政楼1楼"存入
 * adapter 是会议室状态适配器，不能直接将数组信息显示，必须传给适配器，然后适配器传给ListView显示
 * ListView 用来显示adapter内的信息
 */

public class StatusActivity extends BaseActivity {
    //该数组会从数据库读入，暂时做测试用
  /*  private String[][] room_info = {{"1","101","30","可用","行政楼1楼"},
            {"2","102","30","可用","行政楼1楼"},
            {"3","103","40","可用","行政楼2楼"},
            {"4","104","20","可用","行政楼3楼"},
            {"5","105","50","可用","行政楼4楼"},
            {"6","106","30","可用","行政楼5楼"}};*/
  String room_info[][];
    StatusAdapter adapter;
    ListView listView;
    private ArrayList<Room2> RoomList = new ArrayList<Room2>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_layout);
        hindBar();

        initRoomList();
        adapter = new StatusAdapter(
                StatusActivity.this,R.layout.room_status_item,RoomList);//RoomList传给适配器
        listView = (ListView) findViewById(R.id.Status_room);//选择显示的位置
        //点击事件，可以识别点的是哪一个item，并且能得到对应id即position
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Room2 room = RoomList.get(position); //定义一个Room2类对象存放点击的那一行的RoomList中的信息
                Static_Room = room.getName();//获取这个room的名字，传给BaseActivity中的全局变量Static_Room
                Alert(position,room.getName());

            }
        });
        listView.setAdapter(adapter);
    }

    //初始化RoomList，即上面提到的提取过程，将每一个房间的"101","30","可用","行政楼1楼"提取放入RoomList
    private void initRoomList(){
        room_info= croom_readDao.sendLoginRequest();
         for(int i=0;i<room_info.length;i++)
         {
            Room2 room = new Room2(room_info[i][1],room_info[i][2],room_info[i][3],room_info[i][4],room_info[i][0]);
            RoomList.add(room);
         }

     /*   new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        room_info= croom_readDao.sendLoginRequest();

                        for(int i=0;i<5;i++)
                        {
                            System.out.println("   ：传递数据正常"+room_info[i][0]+"-"+room_info[i][1]+"-"+room_info[i][2]+"-"+room_info[i][3]+"-"+room_info[i][4]+"-"+room_info[i][5]);
                        }

                        for(int i=0;i<room_info.length;i++){
                            Room2 room = new Room2(room_info[i][1],room_info[i][2],room_info[i][3],room_info[i][4]);
                            RoomList.add(room);
                        }


                    }
                }).start();*/

    }

    /**
     * 弹出对话框函数，参数i为当前点击的会议室位置类似于id，参数room_name为当前点击的会议室名称
     * 如果会议室可用，设为不可用，若本来不可用，设为可用
     */
    public void Alert( int i, String room_name){
        AlertDialog.Builder dialog = new AlertDialog.Builder (StatusActivity.this);
        if(RoomList.get(i).getStatus().equals("可用")){
            dialog.setTitle("当前"+room_name+"会议室可用");
            dialog.setMessage("确认将其设为不可用吗？");
        }else{
            dialog.setTitle("当前"+room_name+"会议室不可用");
            dialog.setMessage("确认将其设为可用吗？");
        }

        dialog.setCancelable(false);
        final int x = i;//x指删除ReserveList列表中的第x条记录
        //点击确认从会议室名为b的预定信息列表ReserveList中删除第i条信息
        //这个x只是ReserveList里面的第i条，并不是rs_list里的第i条
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(RoomList.get(x).getStatus().equals("可用")){
                    RoomList.get(x).setStatus("不可用");
                    room_info[x][3] = "不可用";

                }else {
                    RoomList.get(x).setStatus("可用");
                    room_info[x][3] = "可用";
                }
                /*new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            conference_set_statusDao.statuspost(RoomList.get(x).getName(),RoomList.get(x).getStatus());

                        }
                }).start();*/

                conference_set_statusDao.statuspost(RoomList.get(x).getName(),RoomList.get(x).getStatus());
                adapter.notifyDataSetChanged();//更新预定信息列表
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.show();
    }

}