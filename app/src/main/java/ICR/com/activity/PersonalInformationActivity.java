package ICR.com.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import ICR.com.R;
import ICR.com.dao.LogoutDao;
import ICR.com.dao.postpersoninfoDao;

public class PersonalInformationActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalinformation_layout);
        ImageButton image1= findViewById(R.id.imageButtonLeft);
      //  Button postdata = findViewById(R.id.save);
        TextView curname=(TextView) findViewById(R.id.name_Right) ;
        TextView curid=(TextView) findViewById(R.id.IDhao_Right) ;
        TextView curtel=(TextView) findViewById(R.id.phone_Right) ;
        final EditText editsex=(EditText) findViewById(R.id.boyorgirl_Right);
        final EditText editoccu=(EditText) findViewById(R.id.occupation_Right);
        final EditText editemail=(EditText) findViewById(R.id.email_Right);
        editsex.setText(static_user_sex);
        editoccu.setText(static_user_postition);
        editemail.setText(static_user_email);




        curname.setText(static_user_name);
        curid.setText(static_user_id);
        curtel.setText(userTel);
        Button image2= findViewById(R.id.save);

        //左上角的返回按钮

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //保存按钮
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String em=   editemail.getText().toString();
             String esx= editsex.getText().toString();
             String eoc= editoccu.getText().toString();
             static_user_email=em;
             static_user_sex=esx;
             static_user_postition=eoc;
                postpersoninfoDao.changepost(esx,eoc,em);
                finish();
            }
        });




    }

}
