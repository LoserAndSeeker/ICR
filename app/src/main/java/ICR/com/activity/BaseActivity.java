package ICR.com.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ICR.com.R;
//通用类，如果有什么方法所有都能用，可以定义在这
public class BaseActivity extends AppCompatActivity {
    public void hindBar(){ //隐藏标题栏，所有界面想隐藏标题栏都只要写一个hindBar()；
        ActionBar actionbar = getSupportActionBar();
        if(actionbar!=null)
            actionbar.hide();
    }
}
