package ICR.com.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ICR.com.R;
import ICR.com.dao.LogoutDao;

//“我的”界面，详情请对照系统设计文档
public class PersonalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_layout);
        Button button1 = (Button) findViewById(R.id.button_suggestion);
        Button button2 = (Button) findViewById(R.id.button_inform);
        Button button3 = (Button) findViewById(R.id.button_logout);
        Button button4 = (Button) findViewById(R.id.button_cancel);
        Button button5 = (Button) findViewById(R.id.buttonPerson);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PersonalActivity.this,  suggestionActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this,  InformActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(PersonalActivity.this);  //出现一个对话框
                dialog.setTitle("This is Logout");
                dialog.setMessage("确认退出当前账号吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PersonalActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(PersonalActivity.this);  //出现一个对话框
                dialog.setTitle("This is Cancel");
                dialog.setMessage("确认注销当前账号吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LogoutDao.logoutPost();

                        Intent intent = new Intent(PersonalActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalActivity.this,PersonalInformationActivity.class);
                startActivity(intent);
            }
        });
    }
}
