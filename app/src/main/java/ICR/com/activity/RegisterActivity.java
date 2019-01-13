package ICR.com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ICR.com.R;
import ICR.com.dao.delete_participateDao;
import ICR.com.dao.registerDao;
import ICR.com.dao.register_repeat_Dao;

public class RegisterActivity extends BaseActivity {
    private TextView Developer;
    private TextView Visitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        hindBar();
        final EditText et_password = (EditText) findViewById(R.id.register_password);
        final EditText et_name = (EditText) findViewById(R.id.register_name);
        final EditText et_phone = (EditText) findViewById(R.id.register_phone);
        final ImageButton bt_change_mode1 = (ImageButton) findViewById(R.id.imageButton3);
        Developer = (TextView) findViewById(R.id.developer);
        Visitor = (TextView) findViewById(R.id.visitor);

        Button button1 = (Button) findViewById(R.id.register_finish);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String nm=et_name.getText().toString();
                final String pw=et_password.getText().toString();
                final String ph=et_phone.getText().toString();
                if(register_repeat_Dao.sendLoginRequest(ph)==1){
                    Toast.makeText(RegisterActivity.this,"phone number is already existed",Toast.LENGTH_SHORT).show();}

                if(register_repeat_Dao.sendLoginRequest(ph)==0){
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                registerDao.registerpost(nm,ph,pw);//获取数据的功能，方法时http
                            }
                        }

                ).start();
               finish();
            }}
        });

        bt_change_mode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_password.getInputType() == 128) {//如果现在是显示密码模式
                    et_password.setInputType(129);//设置为隐藏密码
                    //    bt_change_mode.setText("密码不可见");
                } else {
                    et_password.setInputType(128);//设置为显示密码
                    //   bt_change_mode.setText("密码可见");
                }
                et_password.setSelection(et_password.getText().length());//设置光标的位置到末尾
            }
        });

        Developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alert(static_connect);
            }
        });

        Visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                static_flag = 0;
                userTel = "8008820";
                static_user_name = "路人甲";
                static_user_sex = "随便";
                static_user_postition = "总经理";
                static_user_email = "8008820@163.com";
                static_user_isadmin = "1";
                static_password="";

                String[][] info =
                        {{"1","101","30","可用","行政楼1楼"},
                        {"2","102","30","可用","行政楼1楼"},
                        {"3","201","40","可用","行政楼2楼"},
                        {"4","301","20","可用","行政楼3楼"},
                        {"5","401","50","可用","行政楼4楼"},
                        {"6","501","30","可用","行政楼5楼"}};
                static_room_info =info;
                showToast(RegisterActivity.this,"哦呦，不错哦！ 进入游客模式！",1500);
                Intent intent=new Intent(RegisterActivity.this,MainManagerActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Alert(String s){
        AlertDialog.Builder dialog = new AlertDialog.Builder (RegisterActivity.this);
        String context;
        final EditText edit = new EditText(RegisterActivity.this);
        //dialog.setTitle("Congratulations！开发者模式！");
        dialog.setMessage("Congratulations！发现开发者模式！！！");
        dialog.setView(edit);
        dialog.setCancelable(false);
        edit.setText(s);
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                static_connect = edit.getText().toString();
                Toast.makeText(RegisterActivity.this, "你输入的是: " + static_connect, Toast.LENGTH_SHORT).show();
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
