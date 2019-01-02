package ICR.com.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    public void hindBar(){ //隐藏标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar!=null)
            actionbar.hide();
    }
}
