package ICR.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ICR.com.R;
//会议室使用统计，统计会议室一天或者一周或者一月的使用情况详情，请对照系统设计文档
public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_layout);
    }
}
