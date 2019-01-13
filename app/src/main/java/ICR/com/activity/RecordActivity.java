package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import ICR.com.R;
import ICR.com.dao.conference_readDao;
import ICR.com.dao.conference_record_readDao;

public class RecordActivity extends BaseActivity {

    private ImageButton recordback;//返回按钮
    private Button record_video;//查看视频
    private Button changesignin;//更换到签到记录
    private TextView record_name;//会议名称
    private TextView record_time;//会议预定时间
    private TextView record_person;//会议预定人
    private TextView record_start;//会议开始时间
    private TextView record_end;//会议结束时间
    private TextView record_room;//会议地点
    private EditText record_summary;//会议摘要


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_layout);
        initView();
        recordback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RecordActivity.this,FinishedReadActivity.class));
            }
        });


    }

    private void initView(){
        recordback=(ImageButton)findViewById(R.id.record_back);
        record_video=(Button)findViewById(R.id.record_video);
        changesignin=(Button)findViewById(R.id.button_signin);
        record_name=(TextView)findViewById(R.id.record_name_right);
        record_time=(TextView)findViewById(R.id.record_time_right);
        record_person=(TextView)findViewById(R.id.record_person_Right);
        record_start=(TextView)findViewById(R.id.record_start_right);
        record_end=(TextView)findViewById(R.id.record_end_right);
        record_room=(TextView)findViewById(R.id.record_room_right);
        record_summary=(EditText) findViewById(R.id.record_summary_right);
        String[][] info = conference_record_readDao.participatepost(Static_Room,Static_Date+" "+Static_Clock);
        record_name.setText(info[0][0]);
        record_time.setText(info[0][1]);
        record_person.setText(info[0][5]);
        record_start.setText(info[0][2]);
        record_end.setText(info[0][3]);
        record_room.setText(info[0][4]+" "+Static_Room+"会议室");

    }
}
