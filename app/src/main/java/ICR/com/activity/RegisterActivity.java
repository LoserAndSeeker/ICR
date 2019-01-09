package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import ICR.com.R;
import ICR.com.dao.registerDao;
import ICR.com.dao.register_repeat_Dao;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        hindBar();
        final EditText et_password = (EditText) findViewById(R.id.register_password);
        final EditText et_name = (EditText) findViewById(R.id.register_name);
        final EditText et_phone = (EditText) findViewById(R.id.register_phone);
        final ImageButton bt_change_mode1 = (ImageButton) findViewById(R.id.imageButton3);
        Button button1 = (Button) findViewById(R.id.register_finish);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String nm=et_name.getText().toString();
                final String pw=et_password.getText().toString();
                final String ph=et_phone.getText().toString();
                if(register_repeat_Dao.sendLoginRequest(ph)==1){
                    Toast.makeText(RegisterActivity.this,"phone number is already existed",Toast.LENGTH_SHORT).show();}

                if(register_repeat_Dao.sendLoginRequest(ph)==0){
                new Thread(
                        new Runnable() {
                            @Override
                            public void run() {
                                registerDao.registerpost(nm,ph,pw);//获取数据的功能，方法时http
                            }
                        }

                ).start();
               finish();
            }}
        });

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
