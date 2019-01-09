package ICR.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ICR.com.R;

public class MainManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_manager_layout);

        Button manage1=(Button) findViewById(R.id.button_reserveManager);
        Button manage2=(Button) findViewById(R.id.button_myroomManager);
        Button manage3=(Button) findViewById(R.id.button_checkManager);
        Button manage4=(Button) findViewById(R.id.button_controlManager);
        manage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManagerActivity.this,  ReserveActivity.class);
                startActivity(intent);
            }
        });
        manage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManagerActivity.this,  MyroomActivity.class);
                startActivity(intent);
            }
        });

        manage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManagerActivity.this,  IdentifyActivity.class);
                startActivity(intent);
            }
        });
        manage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainManagerActivity.this, ControlcenterActivity.class);
                startActivity(intent);
            }
        });



    }
}
