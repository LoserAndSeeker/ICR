package ICR.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import ICR.com.R;
//通知界面，会议快要开始时通知参会者，详情请对照系统设计文档
public class InformActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inform_layout);
        ImageButton imageButton1=(ImageButton) findViewById(R.id.imageButtonLeft);

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
