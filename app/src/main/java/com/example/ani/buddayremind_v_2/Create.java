package com.example.ani.buddayremind_v_2;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Create extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText e1, e2;
    TextView t1, t2;
    Button b1, b2, b3,b4,b5;
    TextView s;
    String x1,x2,x3,y1,y2;
    AlarmManager alarmManager;
    private PendingIntent pending_intent;
    private AlarmReceiver alarm;
    Create inst;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.phone);
        t1 = (TextView) findViewById(R.id.tdob);
        t2 = (TextView) findViewById(R.id.time);
        b1 = (Button) findViewById(R.id.btn_dob);
        b2 = (Button) findViewById(R.id.btn_time);
        b3 = (Button) findViewById(R.id.btn_submit);
        b4 = (Button) findViewById(R.id.btn_tone);
        b5 = (Button) findViewById(R.id.button);
        s = (TextView) findViewById(R.id.spinner);
        this.context = this;
        // Get the alarm manager service
        final Intent myIntent = new Intent(this.context, AlarmReceiver.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();
        b3.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)

            @Override
            public void onClick(View v) {
                String name = e1.getText().toString();
                String phone = e2.getText().toString();
                String tdob = t1.getText().toString();
                String time = t2.getText().toString();
                String alarm1 = s.getText().toString();
                if(name.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "DATA INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                }
                int x=0;
                Boolean result = helper.insertData(name, phone, tdob, time, alarm1);
                if (result == true) {
                    Toast.makeText(getApplicationContext(), "DATA INSERTED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                    x = 100;
                }
                if(x>0) {
                    calendar.add(Calendar.SECOND, 3);
                    //setAlarmText("You clicked a button");

                    final int hour = Integer.parseInt(y1);
                    final int minute = Integer.parseInt(y2);
                    ;

                    Log.e("Create", "In the receiver with " + hour + " and " + minute);

                    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(y1));
                    calendar.set(Calendar.MINUTE, Integer.parseInt(y2));

                    myIntent.putExtra("extra", "yes");
                    pending_intent = PendingIntent.getBroadcast(Create.this, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending_intent);


                    // now you should change the set Alarm text so it says something nice


                    //setAlarmText("Alarm set to " + hour + ":" + minute);
                    Toast.makeText(getApplicationContext(), "You set the alarm", Toast.LENGTH_SHORT).show();
                }

                else {
                    Toast.makeText(getApplicationContext(), "DATA INSERTION FAILED", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(Create.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    public void back(View view)
    {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }

    public void onbtndob(View v)
    {
        //Toast.makeText(this, "Btn dob click", Toast.LENGTH_SHORT).show();
        Intent i2=new Intent(getApplicationContext(),Datepicker.class);
        startActivityForResult(i2,2);
    }

    public void onbtntime(View v) {
        Intent i1=new Intent(getApplicationContext(),Timepicker.class);
        startActivityForResult(i1,3);
    }
    public void onbtntone(View v) {
        Intent i1=new Intent(getApplicationContext(),Tonepicker.class);
        startActivityForResult(i1,4);
    }

    @Override
    protected  void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==2)
        {
            x1= data.getStringExtra("value1");
            x2= data.getStringExtra("value2");
            x3= data.getStringExtra("value3");
            t1.setText(x1+"/"+x2+"/"+x3);
        }
        if(requestCode==3)
        {
            y1= data.getStringExtra("value1");
            y2= data.getStringExtra("value2");
            t2.setText(y1+":"+y2);
        }
        if(requestCode==4)
        {
            y1= data.getStringExtra("value1");
            s.setText(y1);
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        inst = this;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.e("Create", "on Destroy");
    }
}
