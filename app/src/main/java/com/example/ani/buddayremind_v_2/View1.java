package com.example.ani.buddayremind_v_2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class View1 extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    TextView t1,t2,t3,t4,t5;
    Button b1,b2;
    Cursor res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view1);
        t1= (TextView) findViewById(R.id.name);
        t2= (TextView) findViewById(R.id.phone);
        t3= (TextView) findViewById(R.id.tdob);
        t4= (TextView) findViewById(R.id.time);
        t5= (TextView) findViewById(R.id.spinner);
        b1= (Button) findViewById(R.id.btn_submit);
        b2= (Button)findViewById(R.id.edit);

        String val=getIntent().getExtras().getString("Value");
        Toast.makeText(this," recieved "+ val, Toast.LENGTH_SHORT).show();
        res = helper.getSingledata(val);
        //A=getIntent().getExtras().getString("Value");
        if (res.moveToNext()) {
            t1.setText(res.getString(1));
            t2.setText(res.getString(2));
            //spinner2.setText(val.substring(16,26));
            t3.setText(res.getString(3));
            t4.setText(res.getString(4));
            t5.setText(res.getString(5));
        }
    }
    public void btnback(View v)
    {
        Intent i1=new Intent(this,MainActivity.class);
        startActivity(i1);
        finish();
    }
    public void onbtnedit(View v)
    {
        Toast.makeText(this," "+res.getString(0), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Edit.class);
        String v1=res.getString(0);
        i.putExtra("Value",v1);
        startActivity(i);
        finish();
    }
}