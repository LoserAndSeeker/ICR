package ICR.com.activity;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.yzq.zxinglibrary.android.CaptureActivity;

import ICR.com.R;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

//底部工具栏界面，就是“首页 二维码 我的”那一栏
public class ToolsLayout extends LinearLayout {

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE_SCAN = 1;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    public ToolsLayout(final Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.bottomtool,this);
        ImageButton mainTurn = (ImageButton) findViewById(R.id.imageButton);
        Button QR = (Button) findViewById(R.id.button_QR_Code);
<<<<<<< HEAD
        ImageButton myTurn = (ImageButton) findViewById(R.id.imageButton2);
=======
        Button myTurn = (Button) findViewById(R.id.button_person);



>>>>>>> a96e3ad6d8de5cd1cd3502e9394ded22ab7bbe8c
        mainTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,  MainActivity.class);
                context.startActivity(intent);//与一般的显示有点区别，请照着写
            }
        });
        QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent intent = new Intent(context,  QRCodeActivity.class);
               // context.startActivity(intent);
                Intent intent = new Intent(context, CaptureActivity.class);
               context.startActivity(intent);
            }
        });
        myTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,  PersonalActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
