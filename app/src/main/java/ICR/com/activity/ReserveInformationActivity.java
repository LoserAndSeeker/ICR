package ICR.com.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageButton;
import ICR.com.R;
import ICR.com.dao.insertconferenceDao;
import ICR.com.dao.insertparticipateDao;
import ICR.com.dao.register_repeat_Dao;

import android.widget.Button ;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReserveInformationActivity extends BaseActivity{

    private ImageButton backaction;//返回
    private Button     reserveimport;//提交
    private ImageButton user_import;//搜索确认
    private EditText   preserve_name;//会议名称
    private TextView   preserve_time;//会议时间
    private TextView   preserve_user;//预订人
    private EditText   search_people;//搜索框
    private TextView   user_play;//添加的用户，名称显示框
    private TextView   room_name;

    String namelist[]=new String[100];
    String nameall;
    int i=0;


    private void initView(){
        backaction=(ImageButton)findViewById(R.id.imageButtonLeft1);
        reserveimport=(Button)findViewById(R.id.reserve_push);
        user_import=(ImageButton)findViewById(R.id.user_import);
        preserve_name=(EditText) findViewById(R.id.name_Right);
        preserve_time=(TextView)findViewById(R.id.time_Right);
        preserve_user=(TextView)findViewById(R.id.people_Right);
        search_people=(EditText)findViewById(R.id.user_edit);
        user_play=(TextView)findViewById(R.id.user_play);
        room_name = (TextView) findViewById(R.id.room_Right);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserveinformation_layout);
        initView();//初始化参数
        i=0;
        nameall="";
        //preserve_name.setText(Static_Room_id);
        room_name.setText(Static_Room+"会议室");
        preserve_user.setText(static_user_name);//显示当前预定人
        /*SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm E");
        Date data=new Date(System.currentTimeMillis());

        String view_time=sd.format(data);*/
        preserve_time.setText(Static_Date+" "+Static_Clock);
//        Date date = new Date(System.currentTimeMillis());
//        time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));

        backaction.setOnClickListener(new View.OnClickListener() {//返回
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        user_import.setOnClickListener(new View.OnClickListener() {//搜索
            @Override
            public void onClick(View v) {
                    String thename=search_people.getText().toString();

                    if(static_flag==1&&register_repeat_Dao.sendLoginRequest(thename)==2){
                    namelist[i]=thename;
                    i++;
                    nameall+=thename+" ";
                    user_play.setText(nameall);
                    search_people.setText("");
                    Toast.makeText(ReserveInformationActivity.this,"成功添加该用户！",Toast.LENGTH_SHORT).show();
                }
                    else {
                    Toast.makeText(ReserveInformationActivity.this,"该用户名不存在！",Toast.LENGTH_SHORT).show();
                }
            }
        });



        reserveimport.setOnClickListener(new View.OnClickListener() {//提交
            @Override
            public void onClick(View v) {
                String start=null,end=null;
                start=getStart(Static_Clock);
                end = getEnd(Static_Clock);
                if(i>0) {
                    if (static_flag == 1) {
                        insertconferenceDao.registerpost(preserve_name.getText().toString(), Static_Date + " " + start, Static_Date + " " + end, Static_Room_id);
                        for (int j = 0; j < i; j++) {
                            insertparticipateDao.registerpost(Static_Room, Static_Date + " " + Static_Clock, namelist[j]);
                        }
                        startActivity(new Intent(ReserveInformationActivity.this, HaveReservedActivity.class));
                    }
                }else{
                    showToast(ReserveInformationActivity.this,"参会人员不能为空，请继续添加",1000);
                }

            }
        });






    }
}
