package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import ICR.com.R;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        hindBar();
        Button button1 = (Button) findViewById(R.id.register_finish);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final EditText et_password = (EditText) findViewById(R.id.register_password);
        final ImageButton bt_change_mode1 = (ImageButton) findViewById(R.id.imageButton3);
        bt_change_mode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_password.getInputType() == 128) {//如果现在是显示密码模式
                    et_password.setInputType(129);//设置为隐藏密码
                    //    bt_change_mode.setText("密码不可见");
                } else {
                    et_password.setInputType(128);//设置为显示密码
                    //   bt_change_mode.setText("密码可见");
                }
                et_password.setSelection(et_password.getText().length());//设置光标的位置到末尾
            }
        });
    }
}
