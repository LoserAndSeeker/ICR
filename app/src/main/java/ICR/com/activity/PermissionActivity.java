package ICR.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ICR.com.R;
//权限设置界面，将账号设为管理员或者普通用户，详情请对照系统设计文档
public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);
    }
}
