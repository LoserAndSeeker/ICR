package ICR.com.activity;

import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


import ICR.com.R;
//反馈信息查看，用于查看系统自动进行会议室调度的反馈情况，详情请对照系统设计文档
public class FeedbackActivity extends BaseActivity {
    ConstraintLayout mConstraintLayout1;
    LinearLayout mLinearLayout2;
    int l2Height;//记录会议室预定信息列表的最下面的高度
    private int nowNumber;
    private int flag=0;//判断是否是off状态，这个状态只有on按钮能打开，其他按钮能添加和删除但不显示
    private ArrayList<View> mViews = new ArrayList<View>();//某个会议室的预定信息列表存在这个数组中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_layout);
        hindBar();
        mConstraintLayout1 = (ConstraintLayout) findViewById(R.id.reserved_info);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.item_status); //该会议室预定信息列表总表所在


        mLinearLayout2.post(new Runnable() {
            @Override
            public void run() {
                //l2Height = 0;
                l2Height = mLinearLayout2.getHeight();  //获取mLinearLayout2的高度
                // w = mLinearLayout2.getWidth();
                //AnimTools.anim(mLinearLayout2,mLinearLayout2.getHeight(),mLinearLayout2.getHeight(),1);
                //Toast.makeText(MainActivity.this, "h:" + l2Height, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void on(View v) {
        if(flag==1) {   //处于off状态才能on，不然on了没意义
            //Toast.makeText(MainActivity.this, "h1:" + mLinearLayout2.getHeight(), Toast.LENGTH_SHORT).show();
            //这应该是显示作用，参数1显示mLinearLayout2
            AnimTools.anim(mLinearLayout2, 0,l2Height, 500);
            flag=0;
        }

    }

    public void off(View v) {
        flag=1; //告诉其他函数现在处于off状态了
        l2Height = mLinearLayout2.getHeight();
        AnimTools.anim(mLinearLayout2, 0, 0, 500);


    }

    public void add(View v) {

        nowNumber++;
        final View view = LayoutInflater.from(this).inflate(R.layout.item_1, null);
        LinearLayout LinearLayout3 = (LinearLayout) view.findViewById(R.id.item_inner) ;
        TextView textView1 = (TextView) view.findViewById(R.id.tv_item1);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_item2);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_item3);
        Button button = (Button) view.findViewById(R.id.bt_item);


        textView1.setText("08:00-10:00" );
        textView2.setText("财务部会议" );
        textView3.setText("老李");
        button.setText("取消");

        mViews.add(view);
        mLinearLayout2.addView(view);
        if(flag==0) { //处于on状态才显示add效果，不然就乱了
            AnimTools.anim(mLinearLayout2, mLinearLayout2.getHeight(), mLinearLayout2.getHeight() + AnimTools.dip2px(this, 40), 200);
            l2Height = mLinearLayout2.getHeight();
        }
    }

    public void del(View v) {
        if(flag==0) //处于on状态才显示del效果，不然就乱了
            AnimTools.anim(mLinearLayout2, mLinearLayout2.getHeight(), mLinearLayout2.getHeight() - AnimTools.dip2px(this, 40), 200);

        new Handler().postDelayed(new Runnable() {

            public void run() {
                if(mViews.size()>=1) { //数组内至少得有一个才能删除，不然会崩溃
                    mLinearLayout2.removeView(mViews.get(mViews.size() - 1));
                    mViews.remove(mViews.size() - 1);
                }

            }

        }, 200);
        if(flag==0)
            l2Height = mLinearLayout2.getHeight();
    }
}
