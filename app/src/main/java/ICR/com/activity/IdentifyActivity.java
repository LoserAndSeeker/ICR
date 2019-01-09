package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import ICR.com.R;
//审核管理页面，详情请对照系统设计文档
public class IdentifyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.identify_layout);
        Button button1 = (Button) findViewById(R.id.button_identify_request);
        Button button2 = (Button) findViewById(R.id.button_permission);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentifyActivity.this,  IdentifyRequestActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentifyActivity.this,  PermissionActivity.class);
                startActivity(intent);
            }
        });

        ImageButton Identifyback= (ImageButton) findViewById(R.id.request_back2);
        Identifyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    }
}
