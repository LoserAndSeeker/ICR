package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ICR.com.R;
//我的会议室界面，详情请对照系统设计文档
public class MyroomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myroom_layout);
        Button button1 = (Button) findViewById(R.id.button_reserved);
        Button button2 = (Button) findViewById(R.id.button_finished);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyroomActivity.this, HaveReservedActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyroomActivity.this, HavefinishedActivity.class);
                startActivity(intent);
            }
        });
    }
}
