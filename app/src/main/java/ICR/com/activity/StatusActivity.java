package ICR.com.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ICR.com.R;


//会议室状态设置界面，管理员可以强制修改会议室状态，包括占用，空闲，故障三个状态，详情请对照系统设计文档
public class StatusActivity extends BaseActivity {
    private static int id = 100;
    public int i = 0;//各个按钮的编号
    public int flag[]=new int[100];//用来存储各个按钮的点击状态，可用或者不可用
    //private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status_layout);
        hindBar();
        final LinearLayout lin = (LinearLayout) findViewById(R.id.list_Lin);
        ImageButton button1 = (ImageButton) findViewById(R.id.add_room);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout.LayoutParams LP_FW = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                RelativeLayout newSingleRL=new RelativeLayout(StatusActivity.this);
                newSingleRL=generateSingleLayout(id,"第"+(++i)+"个会议室");
                lin.addView(newSingleRL,LP_FW);//全部用父结点的布局参数
            }
        });

    }
    /**
     * 新建一个列表item
     * @param imageID 新建imageView的ID值
     * @param str  TextView要显示的文字
     * @return 新建的单项布局变量
     */
    @SuppressLint("ResourceType")
    private RelativeLayout generateSingleLayout(int imageID, String str)
    {
        RelativeLayout layout_root_relative=new RelativeLayout(this);

        LinearLayout layout_sub_Lin=new LinearLayout(this);
        layout_sub_Lin.setBackgroundColor(Color.argb(0xff, 0xff, 0xff, 0xff));
        layout_sub_Lin.setOrientation(LinearLayout.VERTICAL);
        layout_sub_Lin.setPadding(5, 5, 5, 5);

        TextView tv = new TextView(this);
        LinearLayout.LayoutParams LP_WW = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setText(str);
        tv.setTextColor(Color.argb(0xff, 0x00, 0x00, 0x00));
        tv.setTextSize(30);
        tv.setLayoutParams(LP_WW);
        layout_sub_Lin.addView(tv);

        RelativeLayout.LayoutParams RL_MW = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);//尤其注意这个位置，用的是父容器的布局参数
        RL_MW.setMargins(5, 5, 10, 5);
        RL_MW.addRule(RelativeLayout.LEFT_OF,imageID);
        layout_root_relative.addView(layout_sub_Lin,RL_MW);

        Button button = new Button(this);
        RelativeLayout.LayoutParams RL_WW = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        button.setPadding(10, 10, 30, 10);
        RL_WW.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        button.setLayoutParams(RL_WW);
        button.setClickable(true);
        button.setId(imageID);
        button.setBackgroundResource(R.drawable.buttonstyle);
        button.setText("可用");
        button.setTextColor(Color.argb(0xff, 0x00, 0xcf, 0xff));
        flag[i]=0;
        final int j=i;
        //final int flag=0;
        final Button button1=button;
         button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(flag[j]==0){
                    //android.widget.Toast.makeText(getApplicationContext(),j+":First",android.widget.Toast.LENGTH_SHORT).show();
                    button1.setText("不可用");
                }
                else{
                    //android.widget.Toast.makeText(getApplicationContext(),j+":Second",android.widget.Toast.LENGTH_SHORT).show();
                    button1.setText("可用");
                }
                flag[j]=(flag[j]+1)%2;
            }
        });
        layout_root_relative.addView(button);

        return layout_root_relative;

    }



}
