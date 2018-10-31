package com.example.ani.buddayremind_v_2;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Tonepicker extends AppCompatActivity {
    ListView lv;
    Button b1;
    MediaPlayer player;
    String x1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tonepicker);
        lv=(ListView)findViewById(R.id.lv);
        b1 = (Button) findViewById(R.id.btn_set);
        String[] A=new String[]{"a","b","c"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, A);
        lv.setAdapter(adapter);
        player = MediaPlayer.create(this,R.raw.a);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        if(player.isPlaying()==true)
                            player.stop();
                        player = MediaPlayer.create(Tonepicker.this,R.raw.a);
                        player.start();
                        x1=lv.getItemAtPosition(position).toString();
                        //Toast.makeText(getApplicationContext(), "Position:"+position, Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        if(player.isPlaying()==true)
                            player.stop();
                        player = MediaPlayer.create(Tonepicker.this,R.raw.b);
                        player.start();
                        x1=lv.getItemAtPosition(position).toString();
                        break;
                    case 2:
                        if(player.isPlaying()==true)
                            player.stop();
                        player = MediaPlayer.create(Tonepicker.this,R.raw.c);
                        player.start();
                        x1=lv.getItemAtPosition(position).toString();
                        break;
                }
            }
        });
    }
    public void btn_set1(View v) {
        //String x1=String.valueOf(lv.getSelectedItem());
        //Intent i5 = new Intent(this,form.class);
        player.stop();
        Intent i5 = new Intent();
        i5.putExtra("value1",x1);
        setResult(4,i5);
        finish();
        Toast.makeText(this, x1, Toast.LENGTH_SHORT).show();
    }
}