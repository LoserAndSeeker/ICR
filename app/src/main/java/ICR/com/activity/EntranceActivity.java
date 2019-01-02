package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import ICR.com.R;

public class EntranceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_layout);
        hindBar();//隐藏标题栏
        go();//从入口界面延迟跳转到登陆界面
    }
    public void go(){   //从入口界面延迟跳转到登陆界面
        Timer time = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(EntranceActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        };time.schedule(timerTask, 2000);//修改这个数字就可修改延迟时间
    }
}
