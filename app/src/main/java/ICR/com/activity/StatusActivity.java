package ICR.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ICR.com.R;
//会议室状态设置界面，管理员可以强制修改会议室状态，包括占用，空闲，故障三个状态，详情请对照系统设计文档
public class StatusActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_layout);
    }
}
