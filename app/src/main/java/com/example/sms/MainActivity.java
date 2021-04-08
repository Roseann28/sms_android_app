package com.example.sms;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText messages,numbers;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messages=findViewById(R.id.Inputmessage);
        numbers=findViewById(R.id.Inputnumber);
        button=findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        sends();
                    }else {
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }

            }
        });
    }
    public void sends(){
       String number=numbers.getText().toString().trim();
       String message=messages.getText().toString().trim();
       try {
           SmsManager smsManager=SmsManager.getDefault();
           smsManager.sendTextMessage(number,null,message,null,null);
           Toast.makeText(getBaseContext(),"Message sent successfully",Toast.LENGTH_LONG).show();
    }catch (Exception exception) {
           Toast.makeText(getBaseContext(),"Failed to send",Toast.LENGTH_LONG).show();
       }
       }
}