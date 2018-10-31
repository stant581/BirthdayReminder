package com.example.ani.buddayremind_v_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class Timepicker extends AppCompatActivity {
    TimePicker tp;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        tp = (TimePicker) findViewById(R.id.timePicker);
        b1 = (Button) findViewById(R.id.btn_set);
    }
    public void btn_set(View v) {
        String x1=String.valueOf(tp.getCurrentHour());
        String x2=String.valueOf(tp.getCurrentMinute());

        //Intent i5 = new Intent(this,form.class);
        Intent i5 = new Intent();
        i5.putExtra("value1",x1);
        i5.putExtra("value2",x2);

        setResult(3,i5);
        finish();
    }
}