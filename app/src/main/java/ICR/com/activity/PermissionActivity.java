package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ICR.com.R;
//权限设置界面，将账号设为管理员或者普通用户，详情请对照系统设计文档
public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permission_layout);
        Button changestyle= (Button) findViewById(R.id.button_identify_request1);
        changestyle.setOnClickListener(new View.OnClickListener() {//切换功能
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionActivity.this,  IdentifyActivity.class);
                startActivity(intent);
            }
        });
        ImageButton Permissionback= (ImageButton) findViewById(R.id.request_back1);
      Permissionback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//返回主页
                if (BaseActivity.static_user_isadmin.equals("1")){
                    Intent intent = new Intent(PermissionActivity.this,  MainManagerActivity.class);
                    startActivity(intent);
                }else {Intent intent = new Intent(PermissionActivity.this,  MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
