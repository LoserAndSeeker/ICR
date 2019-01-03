package ICR.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ICR.com.R;
//信息审核功能，当用户发出忘记密码的申请时，审核用户身份，详情请对照系统设计文档
public class IdentifyRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identifyrequest_layout);
    }
}
