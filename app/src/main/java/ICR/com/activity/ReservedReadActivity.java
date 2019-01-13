package ICR.com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ICR.com.ListView.Person2;
import ICR.com.ListView.PersonAdapter;
import ICR.com.ListView.Room2;
import ICR.com.ListView.StatusAdapter;
import ICR.com.R;
import ICR.com.dao.conference_set_statusDao;
import ICR.com.dao.croom_readDao;
import ICR.com.dao.delete_participateDao;
import ICR.com.dao.insertparticipateDao;
import ICR.com.dao.participate_readDao;
import ICR.com.dao.register_repeat_Dao;

public class ReservedReadActivity extends BaseActivity {

    String person_info[][];
    PersonAdapter adapter;
    ListView listView;
    private ArrayList<Person2> PersonList = new ArrayList<Person2>();

    private ImageButton user_import;
    private EditText search_people;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserved_read);

        user_import=(ImageButton)findViewById(R.id.user_import2);
        search_people=(EditText)findViewById(R.id.user_edit2);

        hindBar();
        initRoomList();

        adapter = new PersonAdapter(
                ReservedReadActivity.this,R.layout.reserved_read_item,PersonList);//RoomList传给适配器
        listView = (ListView) findViewById(R.id.Reserved_info_read);//选择显示的位置
        //点击事件，可以识别点的是哪一个item，并且能得到对应id即position
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person2 person = PersonList.get(position); //定义一个Room2类对象存放点击的那一行的RoomList中的信息
                //获取这个room的名字，传给BaseActivity中的全局变量Static_Room
                //if(person.getName().equals(static_user_name))
                Alert(position,person.getName());

            }
        });
        listView.setAdapter(adapter);

        user_import.setOnClickListener(new View.OnClickListener() {//搜索
            @Override
            public void onClick(View v) {
                String thename = search_people.getText().toString();

                if (register_repeat_Dao.sendLoginRequest(thename) == 2) {
                    insertparticipateDao.registerpost(Static_Room,Static_Date+" "+Static_Clock,thename);
                    Person2 person = new Person2(thename,"删除");
                    PersonList.add(person);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(ReservedReadActivity.this, "该用户名不存在！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void initRoomList() {
        PersonList.clear();
        if(static_flag==1) {
            person_info = participate_readDao.participatepost(Static_Room, Static_Date + " " + Static_Clock);
            for (int i = 0; i < person_info[0].length; i++) {
                Person2 person = new Person2(person_info[0][i], "删除");

                PersonList.add(person);
                //System.out.println(person_info[0][0]);
            }
        }
    }

    public void Alert(int i, final String person_name){
        AlertDialog.Builder dialog = new AlertDialog.Builder (ReservedReadActivity.this);

        dialog.setTitle("您选择了"+person_name);
        dialog.setMessage("确认将其删除吗？");

        dialog.setCancelable(false);
        final int x = i;//x指删除ReserveList列表中的第x条记录
        //点击确认从会议室名为b的预定信息列表ReserveList中删除第i条信息
        //这个x只是ReserveList里面的第i条，并不是rs_list里的第i条
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PersonList.remove(x);
                if(static_flag==1)
                    delete_participateDao.registerpost(Static_Room,Static_Date+" "+Static_Clock,person_name);
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
