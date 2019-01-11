package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ICR.com.R;
//会议室控制中心，详情请对照系统设计文档
public class ControlcenterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controlcenter_layout);
        hindBar();
        Button button1 = (Button) findViewById(R.id.button_status);
        Button button2 = (Button) findViewById(R.id.button_report);
        Button button3 = (Button) findViewById(R.id.button_feedback);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlcenterActivity.this,  StatusActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ControlcenterActivity.this,  ReportActivity.class);
                startActivity(intent);*/
                showToast(ControlcenterActivity.this,"前方道路正在施工",500);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(ControlcenterActivity.this,  FeedbackActivity.class);
                startActivity(intent);*/
                showToast(ControlcenterActivity.this,"前方道路也在施工",500);
            }
        });
    }
}
