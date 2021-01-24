package com.example.oumaima.lastproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Achats extends AppCompatActivity implements com.example.oumaima.lastproject.Tab2Fragment.SendMessage{
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private static final String TAG = "MainActivity";

    private com.example.oumaima.lastproject.SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achats);
        // Log.d(TAG, "onCreate: Starting.");

        //from navigation
        dl = (DrawerLayout)findViewById(R.id.mymain2);
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
                        Toast.makeText(com.example.oumaima.lastproject.Achats.this, "call",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent (Intent.ACTION_DIAL, Uri.parse( "tel:0677218995"));
                        startActivity(in);
                        return true;
                    case R.id.mycart:
                        Toast.makeText(com.example.oumaima.lastproject.Achats.this, "mycart",Toast.LENGTH_SHORT).show();
                        Intent in2 = new Intent (com.example.oumaima.lastproject.Achats.this, com.example.oumaima.lastproject.ListChoices.class);
                        startActivity(in2);
                        return true;
                    case R.id.msg:
                        Toast.makeText(com.example.oumaima.lastproject.Achats.this, "msg",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent( Intent.ACTION_SENDTO, Uri.parse("sms:0677218995"));
                        startActivity(intent);
                        intent.putExtra("sms", "ici un message ");
                        return true;
                    case R.id.settings:
                        Toast.makeText(com.example.oumaima.lastproject.Achats.this, "aide",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.main:
                        Toast.makeText(com.example.oumaima.lastproject.Achats.this, "",Toast.LENGTH_SHORT).show();
                        Intent inten= new Intent( com.example.oumaima.lastproject.Achats.this, com.example.oumaima.lastproject.MainActivity.class);
                        startActivity(inten);
                        return true;
                    default:
                        return true;
                }

            }

        });











        mSectionsPageAdapter = new com.example.oumaima.lastproject.SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        com.example.oumaima.lastproject.SectionsPageAdapter adapter = new com.example.oumaima.lastproject.SectionsPageAdapter(getSupportFragmentManager());
        //  adapter.addFragment(new Tab1Fragment(), "Contact");
        adapter.addFragment(new com.example.oumaima.lastproject.Tab2Fragment(), "Livraison");
        adapter.addFragment(new com.example.oumaima.lastproject.Tab3Fragment(), "Carte");
        //adapter.addFragment(new Tab4Fragement(), "TAB4");
        viewPager.setAdapter(adapter);
    }
    @Override
    public void sendData(String message) {
        String tag = "android:switcher:" + R.id.container + ":" + 1;
        com.example.oumaima.lastproject.Tab3Fragment f = (com.example.oumaima.lastproject.Tab3Fragment) getSupportFragmentManager().findFragmentByTag(tag);
        f.displayReceivedData(message);
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