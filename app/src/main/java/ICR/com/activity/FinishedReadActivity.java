package ICR.com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import ICR.com.ListView.Person2;
import ICR.com.ListView.PersonAdapter;
import ICR.com.ListView.Room2;
import ICR.com.ListView.StatusAdapter;
import ICR.com.R;
import ICR.com.dao.conference_set_statusDao;
import ICR.com.dao.croom_readDao;
import ICR.com.dao.read_finish_statusDao;

public class FinishedReadActivity extends BaseActivity {

    String person_info[][];
    PersonAdapter adapter;
    ListView listView;
    private ArrayList<Person2> PersonList = new ArrayList<Person2>();

    private ImageButton signinback;//返回按钮
    private Button changerecord;//更换到会议记录按钮

    private void initView(){
        signinback=(ImageButton)findViewById(R.id.signin_back);
        changerecord=(Button)findViewById(R.id.button_record);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);

        hindBar();
        initView();
        initRoomList();
        adapter = new PersonAdapter(
                FinishedReadActivity.this,R.layout.reserved_read_item,PersonList);//RoomList传给适配器
        listView = (ListView) findViewById(R.id.Finished_info_read);//选择显示的位置
        //点击事件，可以识别点的是哪一个item，并且能得到对应id即position
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person2 person = PersonList.get(position); //定义一个Room2类对象存放点击的那一行的RoomList中的信息
                //获取这个room的名字，传给BaseActivity中的全局变量Static_Room
                Alert(position,person.getName());

            }
        });*/
        listView.setAdapter(adapter);

        signinback.setOnClickListener(new View.OnClickListener() {//返回上一页
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        changerecord.setOnClickListener(new View.OnClickListener() {//跳转到会议记录
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinishedReadActivity.this,RecordActivity.class));
            }
        });
    }

    private void initRoomList() {
        if(static_flag == 1)
            person_info = read_finish_statusDao.participatepost(Static_Room,Static_Date+" "+Static_Clock);
        for (int i = 0; i <person_info.length; i++) {
            Person2 person = new Person2(person_info[i][0],person_info[i][1]);
            PersonList.add(person);
        }
    }

}
