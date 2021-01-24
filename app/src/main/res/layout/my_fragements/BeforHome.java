package com.example.oumaima.my_fragements;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BeforHome extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_befor_home);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
             int id = item.getItemId();
                switch(id)
                {
                    case R.id.call:
                        Toast.makeText(com.example.oumaima.my_fragements.BeforHome.this, "call",Toast.LENGTH_SHORT).show();
                       Intent in = new Intent (Intent.ACTION_DIAL, Uri.parse( "tel:0677218995"));
                        startActivity(in);
                        return true;
                    case R.id.mycart:
                        Toast.makeText(com.example.oumaima.my_fragements.BeforHome.this, "mycart",Toast.LENGTH_SHORT).show();
                        Intent in2 = new Intent (com.example.oumaima.my_fragements.BeforHome.this,loginActivity.class);
                        startActivity(in2);
                        return true;
                    case R.id.msg:
                        Toast.makeText(com.example.oumaima.my_fragements.BeforHome.this, "msg",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent( Intent.ACTION_SENDTO, Uri.parse("sms:0677218995"));
                        startActivity(intent);
                        intent.putExtra("sms", "ici un message ");
                        return true;
                    case R.id.settings:
                        Toast.makeText(com.example.oumaima.my_fragements.BeforHome.this, "aide",Toast.LENGTH_SHORT).show();
                        return true;
                        default:
                        return true;
                }

           }

        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    /**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // cela permit d'ajouter un menu défini en XML
        getMenuInflater().inflate(R.menu.menu3points, menu);
        return true;
    }
}
