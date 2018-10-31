package com.example.ani.buddayremind_v_2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText e1, e2,e3,e4,e5;
    TextView t1, t2,id;
    Button b1,b2,b3;
    TextView s;
    String x1,x2,x3,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = (TextView) findViewById(R.id.textView);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText)findViewById(R.id.editText4);
        e5 = (EditText)findViewById(R.id.editText5);
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.btn_time);
        b3 = (Button) findViewById(R.id.btn_submit);
       // s = (TextView) findViewById(R.id.spinner);


        String val=getIntent().getExtras().getString("Value");
        Toast.makeText(this," recieved "+ val, Toast.LENGTH_SHORT).show();
        Cursor res = helper.getSingledata(val);
        //A=getIntent().getExtras().getString("Value");
        if (res.moveToNext()) {
            id.setText(res.getString(0));
            e1.setText(res.getString(1));
            e2.setText(res.getString(2));
            //spinner2.setText(val.substring(16,26));
            t1.setText(res.getString(3));
            t2.setText(res.getString(4));
        }
    }
    public void onbtndob(android.view.View v)
    {
        //Toast.makeText(this, "Btn dob click", Toast.LENGTH_SHORT).show();
        Intent i2=new Intent(getApplicationContext(),Datepicker.class);
        startActivityForResult(i2,2);
        finish();
    }

    public void onbtntime(android.view.View v) {
        Intent i1=new Intent(getApplicationContext(),Timepicker.class);
        startActivityForResult(i1,3);
    }
    public void onbtntone(View v) {
        Intent i1=new Intent(getApplicationContext(),Tonepicker.class);
        startActivityForResult(i1,4);
        finish();
    }

    public void btn_update(View v) {
        String id1 = id.getText().toString();
        String name = e1.getText().toString();
        String phone = e2.getText().toString();
        String tdob = e3.getText().toString();
        String time = e4.getText().toString();
        String alarm1 = e5.getText().toString();


        Boolean result = helper.updateData(id1,name, phone, tdob, time, alarm1);
        if (result == true)
        {
            Toast.makeText(this, "DATA Updated SUCCESSFULLY", Toast.LENGTH_SHORT).show();
            Intent i1=new Intent(this,MainActivity.class);
            startActivity(i1);
        }

        else
            Toast.makeText(this, "DATA INSERTION FAILED", Toast.LENGTH_SHORT).show();
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
            e3.setText(x1+"/"+x2+"/"+x3);
        }
        if(requestCode==3)
        {
            y1= data.getStringExtra("value1");
            y2= data.getStringExtra("value2");
            e4.setText(y1+":"+y2);
        }
        if(requestCode==4)
        {
            y1= data.getStringExtra("value1");
            e5.setText(y1);
        }
    }
}
