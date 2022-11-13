package com.example.gaurdian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DashBoard extends AppCompatActivity {
 Button sosbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sosbtn = findViewById(R.id.sos);
        sosbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(DashBoard.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
                {
                    // when permission is granted
                    sendMessage();// it is if permission is automatically granted
                }else{
                    ActivityCompat.requestPermissions(DashBoard.this,new String[]{Manifest.permission.SEND_SMS},100);
                }
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }

    private void sendMessage() {
        String s = "9760025987";
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(s,null,"please send help",null,null);
        Toast.makeText(this, "Help reaching soon", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        {
            if(requestCode==100 && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
            {
                sendMessage();// it is when system is asking for the permission
            }else{
                // when permission is denied
                Toast.makeText(this, "permission denied grant permission first", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
