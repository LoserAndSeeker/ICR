package ICR.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ICR.com.R;
//通知界面，会议快要开始时通知参会者，详情请对照系统设计文档
public class InformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_layout);
    }
}
