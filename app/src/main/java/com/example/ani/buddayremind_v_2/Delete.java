package com.example.ani.buddayremind_v_2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Delete extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    TextView e1, e2;
    TextView t1, t2,id;
    Button b2,b3;
    TextView s;
    String x1,x2,x3,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        id = (TextView) findViewById(R.id.id);
        e1 = (TextView) findViewById(R.id.name);
        e2 = (TextView) findViewById(R.id.phone);
        t1 = (TextView) findViewById(R.id.tdob);
        t2 = (TextView) findViewById(R.id.time);
        b2 = (Button) findViewById(R.id.btn_no);
        b3 = (Button) findViewById(R.id.btn_yes);
        s = (TextView) findViewById(R.id.spinner);

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
            s.setText(res.getString(5));
        }
    }
    public void onbtnyes(View v) {
        String id1 = id.getText().toString();
        int result = helper.deleteData(id1);
        Toast.makeText(this,result+":Rows Affected " , Toast.LENGTH_SHORT).show();
        Intent i1=new Intent(this,MainActivity.class);
        startActivity(i1);
        finish();
    }
    public void onbtnno(View v) {
        Intent i1=new Intent(this,MainActivity.class);
        startActivity(i1);
        finish();
    }
}