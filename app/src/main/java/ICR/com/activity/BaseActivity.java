package ICR.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ICR.com.R;
import ICR.com.dao.croom_readDao;

//通用类，如果有什么方法所有都能用，可以定义在这
public class BaseActivity extends AppCompatActivity {
    public static String Static_Room; //预定功能选择的room
    public static String Static_Clock;//预定功能选择的时间
    public static String Static_Date;//预定功能选择的日期
    public static String Static_Room_id;//预定功能选择的room_id
    public static String[] Clock= {"08:00:00-09:00:00","09:00:00-10:00:00","10:00:00-11:00:00",
            "11:00:00-12:00:00","12:00:00-13:00:00", "13:00:00-14:00:00",
            "14:00:00-15:00:00","15:00:00-16:00:00","16:00:00-17:00:00",
            "17:00:00-18:00:00","18:00:00-19:00:00","19:00:00-20:00:00"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    //隐藏标题栏，所有界面想隐藏标题栏都只要写一个hindBar();
    public void hindBar(){
        ActionBar actionbar = getSupportActionBar();
        if(actionbar!=null)
            actionbar.hide();
    }

    //public static  String telnum;
    public static String userTel;
    public static String static_user_name;
    public static String static_user_sex;
    public static String static_user_postition;
    public static String static_user_email;
    public static String static_user_isadmin;
    public static String static_user_id;
    public static String static_confer_id;

    //取"2019-1-1 09:00:00"中的"2019-1-1"
    public static String getDate(String time){
        int x = time.indexOf(" ");
        return time.substring(0,x);
        //System.out.println(date+clock);
    }

    //取"2019-1-1 09:00:00"中的"09:00:00"
    public static String getClock(String time){
        int x = time.indexOf(" ");
        return time.substring(x+1);
        //System.out.println(date+clock);
    }

    public static String getStart(String time){
        int x = time.indexOf("-");
        return time.substring(0,x);
    }

    public static String getEnd(String time){
        int x = time.indexOf("-");
        return time.substring(x+1);
    }

    public static void showToast(final Activity activity, final String word, final long time){
        activity.runOnUiThread(new Runnable() {
            public void run() {
                final Toast toast = Toast.makeText(activity, word, Toast.LENGTH_LONG);
                toast.show();
                Handler handler = new android.os.Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        toast.cancel();
                    }
                }, time);
            }
        });
    }

}
