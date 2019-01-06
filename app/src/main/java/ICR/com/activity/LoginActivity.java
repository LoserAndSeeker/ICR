package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import ICR.com.R;
import ICR.com.dao.UserDao;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        hindBar();

        Button button1 = (Button) findViewById(R.id.button_main);  //登录
        Button button2 = (Button) findViewById(R.id.button_register);//注册
        Button button3 = (Button) findViewById(R.id.button_passwordrelative);//忘记密码
        final EditText user = (EditText) findViewById(R.id.account_edit);
       final EditText password = (EditText) findViewById(R.id.password_edit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int request=0;
                String nm=user.getText().toString();
                String pw=password.getText().toString();

                request=UserDao.sendLoginRequest(nm,pw);
                if(request==1){
                    userTel=nm;//记录用户名
                Intent intent = new Intent(LoginActivity.this,  MainActivity.class);
                startActivity(intent);}
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,  RegisterActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,  PasswordRelativeActivity.class);
                startActivity(intent);
            }
        });
        final ImageButton bt_change_mode1 = (ImageButton) findViewById(R.id.imageButtonlogin);
        bt_change_mode1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (password.getInputType() == 128) {//如果现在是显示密码模式
                    password.setInputType(129);//设置为隐藏密码
                    //    bt_change_mode.setText("密码不可见");
                } else {
                    password.setInputType(128);//设置为显示密码
                    //   bt_change_mode.setText("密码可见");
                }
                password.setSelection(password.getText().length());//设置光标的位置到末尾
            }
        });
    }
}
