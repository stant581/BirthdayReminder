package com.example.ani.buddayremind_v_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class Datepicker extends AppCompatActivity {
    DatePicker dp;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datepicker);
        dp = (DatePicker) findViewById(R.id.datePicker);
        b1 = (Button) findViewById(R.id.btn_set);
    }
    public void btn_set1(View v) {
        String x1=String.valueOf(dp.getDayOfMonth());
        String x2=String.valueOf(dp.getMonth()+1);
        String x3=String.valueOf(dp.getYear());
        //Intent i5 = new Intent(this,form.class);
        Intent i5 = new Intent();
        i5.putExtra("value1",x1);
        i5.putExtra("value2",x2);
        i5.putExtra("value3",x3);
        setResult(2,i5);
        finish();
        // Toast.makeText(this, x1+"/"+x2+"/"+x3, Toast.LENGTH_SHORT).show();
    }
}