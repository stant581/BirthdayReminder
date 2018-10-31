package com.example.ani.buddayremind_v_2;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb=new DatabaseHelper(this);
    ListView gv;
    List<String> A = new ArrayList<String>();
    String B=new String();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gv=(ListView) findViewById(R.id.gv);
        Cursor res = myDb.getAlldata();
        StringBuffer stbf = new StringBuffer();

        if (res != null && res.getCount() > 0) {
            A.add("ID      Name            Mobile          DOB ");
            while (res.moveToNext()) {
                A.add(res.getString(0)+"      "+res.getString(1)+"            "+res.getString(2)+"          "+res.getString(3));
            }
            ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,A);
            gv.setAdapter(adapter);
            registerForContextMenu(gv);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),"Item Clicked "+ A.get(position),Toast.LENGTH_SHORT).show();

                }
            });
            Toast.makeText(this, "Data Retrieved Successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "No data to retrieve", Toast.LENGTH_SHORT).show();
        }




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)menuInfo;
        B=A.get(info.position);
        int id= B.indexOf(0);

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select the Action");
        menu.add(0, id, 0, "View");
        menu.add(0, id, 0, "Delete");
        menu.add(0, id, 0, "Modify");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle() == "View") {
            Toast.makeText(this, "View is selected "+B.charAt(0), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, View1.class);
            String v1=B.substring(0,2);
            i.putExtra("Value",v1);
            startActivity(i);
            finish();
        }
        if (item.getTitle() == "Delete") {
            Toast.makeText(this, "Deleted is selected "+B.charAt(0), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Delete.class);
            String v1=B.substring(0,2);
            i.putExtra("Value",v1);
            startActivity(i);
            finish();
        }
        else if (item.getTitle() == "Modify") {
            Toast.makeText(this,"Data Updated Successfully "+B.charAt(0), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, Edit.class);
            String v1=B.substring(0,2);
            i.putExtra("Value",v1);
            startActivity(i);
            finish();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.create:
                Toast.makeText(this, "Create New Record", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this,Create.class));
                Intent i1=new Intent(this,Create.class);
                startActivity(i1);
                return true;
            case R.id.setting:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                Intent i2=new Intent(this,SettingsActivity.class);
                startActivity(i2);
                return true;
            case R.id.logout:
                Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    public void share(View view)
    {
        Intent i=new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("email"));
        String[] s={"stant581@gmail.com","abhijeetbanerjee360@gmail.com"};
        i.putExtra(Intent.EXTRA_EMAIL,s);
        i.putExtra(Intent.EXTRA_SUBJECT,"The Budday Reminder app.");
        i.putExtra(Intent.EXTRA_TEXT,"Hi, Please Checkout our latest update of the Budday Reminder app");
        i.setType("message/rfc822");
        Intent chooser=Intent.createChooser(i,"Launch Email");
        startActivity(chooser);
    }



}
