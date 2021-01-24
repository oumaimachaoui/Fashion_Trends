package com.example.oumaima.lastproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class HomeAcitivity extends AppCompatActivity {
    String prodctname=null;
    private DrawerLayout dl;
    private int count = 0;
    EditText theFilter;
    Button increaseButton;
    SessionManager SessionManager;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    String namerr=null;
    String userid=null;
    //GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivity);
        SessionManager =new SessionManager(this);
        // increaseButton = (Button) findViewById(R.id.increaseButton);
        //gridview = (GridView)findViewById(R.id.customgrid);
/*        increaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doIncrease();
                Intent i = getIntent();
                namerr = i.getStringExtra("NAME_KEY");
                Toast.makeText(HomeAcitivity.this, "Total number of Items are:" +namerr , Toast.LENGTH_LONG).show();




            }
        });*/
        theFilter = (EditText) findViewById(R.id.searchFilter);
        theFilter.setVisibility(View.GONE);
        //////getting id of the activity activity_list_choice
        dl = (DrawerLayout)findViewById(R.id.list_produit);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_HOME_AS_UP
                | ActionBar.DISPLAY_SHOW_HOME
                | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setCustomView(R.layout.txtview_layout);



        nv = (NavigationView)findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.action_settings:
                        Toast.makeText(com.example.oumaima.lastproject.HomeAcitivity.this, "call",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent (Intent.ACTION_DIAL, Uri.parse( "tel:0677218995"));
                        startActivity(in);
                        return true;
                    case R.id.mycart:
                        Toast.makeText(com.example.oumaima.lastproject.HomeAcitivity.this, "mycart",Toast.LENGTH_SHORT).show();
                        Intent in2 = new Intent (com.example.oumaima.lastproject.HomeAcitivity.this, com.example.oumaima.lastproject.ListChoices.class);
                        startActivity(in2);
                        return true;
                    case R.id.msg:
                        Toast.makeText(com.example.oumaima.lastproject.HomeAcitivity.this, "msg",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent( Intent.ACTION_SENDTO, Uri.parse("sms:0677218995"));
                        startActivity(intent);
                        intent.putExtra("sms", "ici un message ");
                        return true;
                    case R.id.settings:
                        // Toast.makeText(com.example.oumaima.lastproject.UserProfile.this, "aide",Toast.LENGTH_SHORT).show();
                        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(HomeAcitivity.this); //Home is name of the activity
                        builder.setMessage("Do you want to exit?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                finish();
                                Intent i=new Intent();
                                i.putExtra("finish", true);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
                                //startActivity(i);
                                SessionManager.logout();
                                finish();

                            }
                        });

                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        android.support.v7.app.AlertDialog alert=builder.create();
                        alert.show();
                        return true;
                    default:
                        return true;
                }

            }

        });
        //Fragement navigation view
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Bundle extras2 = getIntent().getExtras();
        /*
        if(extras2 != null){
            userid = extras2.getString("userid");
            // editText.setText(username);
         //   Toast.makeText(getApplicationContext(),"userid        "+userid,Toast.LENGTH_LONG).show();
        }
*/
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            prodctname = extras.getString("Name");
            // editText.setText(username);
        }
        String m1="tricot";
        String m2="robe";
        String m3="pantalon";
        String m4="chaussure";
        String m5="accessoire";
        String n1="top";
        String n2="dress";
        String n3="pants";
        String n4="shoes";
        String n5="accessories";
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            //tricot fragement to getback
            if((prodctname.trim().equals(m1.trim()))||(prodctname.trim().equals(n1.trim()))){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment3()).commit();
                //Bundle bundle3 = new Bundle();
                // bundle3.putString("userid", userid);
                // new HomeFragment3().setArguments(bundle3);
            }
            //robe fragement to getback
            else if((prodctname.trim().equals(m2.trim()))||(prodctname.trim().equals(n2.trim()))){

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment2()).commit();
                Bundle bundl = new Bundle();
                bundl.putString("userid", userid);
                new HomeFragment2().setArguments(bundl);
            }
            //pantalon fragement to getback
            else if((prodctname.trim().equals(m3.trim()))||(prodctname.trim().equals(n3.trim()))){

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment4()).commit();
                Bundle bund = new Bundle();
                bund.putString("userid", userid);
                new HomeFragment4().setArguments(bund);
            }
            //chaussure fragement to getback
            else if((prodctname.trim().equals(m4.trim()))||(prodctname.trim().equals(n4.trim()))){

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment6()).commit();
                Bundle bun2 = new Bundle();
                bun2.putString("userid", userid);
                new HomeFragment6().setArguments(bun2);
            }
            //accessoire fragement to getback
            else if((prodctname.trim().equals(m5.trim()))||(prodctname.trim().equals(n5.trim()))){

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment5()).commit();
                Bundle bun = new Bundle();
                bun.putString("userid", userid);
                new HomeFragment5().setArguments(bun);
            }
            else{
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                Bundle bu = new Bundle();
                bu.putString("userid", userid);
                new HomeFragment().setArguments(bu);
            }


        }
        String totalPrix=null;
        Intent in=getIntent();
        Bundle bundle = getIntent().getExtras();
        String value = bundle.getString("totalPrix");
        // Log.v("in mainactivity",""+value);
        // Toast.makeText(getApplicationContext(),"totalPrix        "+totalPrix,Toast.LENGTH_LONG).show();
        Bundle b = this.getIntent().getExtras();
        totalPrix = b.getString("totalPrix");
        /* Bundle extras3 = getIntent().getExtras();
        String totalPrix=null;
        if(extras3 != null){
            totalPrix = extras2.getString("totalPrix");
            // editText.setText(username);
            //Toast.makeText(getApplicationContext(),"totalPrix        "+totalPrix,Toast.LENGTH_LONG).show();
        }*/

    }
    public String getMyData() {
        return userid;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    Bundle extras = getIntent().getExtras();
                    if(extras != null){
                        prodctname = extras.getString("Name");
                        // editText.setText(username);
                    }
                    String m1="tricot";
                    String m2="robe";
                    String m3="pantalon";
                    String m4="chaussure";
                    String m5="accessoire";
                    String n1="top";
                    String n2="dress";
                    String n3="pants";
                    String n4="shoes";
                    String n5="accessories";
                    ///TRICOT ACTIVITY
                    if((prodctname.trim().equals(m1.trim()))||(prodctname.trim().equals(n1.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragment3();
                                //  selectedFragment.setArguments(bundle);
                                break;
                            case R.id.nav_search:
                                selectedFragment = new Favorites2Fragement();
                                break;
                            case R.id.nav_favorites:
                                selectedFragment = new FavoritesFragment();
                                break;
                          /*  case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                break;*/

                        }
                    }
                    ///robe ACTIVITY
                    else if((prodctname.trim().equals(m2.trim()))||(prodctname.trim().equals(n2.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragment2();
                                break;
                            case R.id.nav_search:
                                selectedFragment = new Favorites2Fragement();
                                break;
                            case R.id.nav_favorites:
                                selectedFragment = new FavoritesFragment();
                                break;
                           /* case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                break;*/
                        }
                    }
                    ///PANTS ACTIVITY
                    else if((prodctname.trim().equals(m3.trim()))||(prodctname.trim().equals(n3.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragment4();
                                break;
                            case R.id.nav_search:
                                selectedFragment = new Favorites2Fragement();
                                break;
                            case R.id.nav_favorites:
                                selectedFragment = new FavoritesFragment();
                                break;
                         /*   case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                break;*/

                        }
                    }
                    ///sHOSE ACTIVITY
                    else if((prodctname.trim().equals(m4.trim()))||(prodctname.trim().equals(n4.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragment6();
                                break;
                            case R.id.nav_search:
                                selectedFragment = new FavoritesFragment();
                                break;
                            case R.id.nav_favorites:
                                selectedFragment = new Favorites2Fragement();
                                break;
                          /*  case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                break;*/

                        }}
                    ///accessoire ACTIVITY
                    else if((prodctname.trim().equals(m5.trim()))||(prodctname.trim().equals(n5.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragment5();
                                break;
                            case R.id.nav_search:
                                selectedFragment = new FavoritesFragment();
                                break;
                            case R.id.nav_favorites:
                                selectedFragment = new FavoritesFragment();
                                break;
                         /*   case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                break;*/

                        }}
                    else{

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new HomeFragment();
                                break;
                            case R.id.nav_search:
                                selectedFragment = new Favorites2Fragement();
                                break;
                            case R.id.nav_favorites:
                                selectedFragment = new FavoritesFragment();
                                break;

                        }
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item)){}


        return super.onOptionsItemSelected(item);
    }
    /**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // cela permit d'ajouter un menu défini en XML
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.testAction);
        //set counter function to icone
       menuItem.setIcon(buildCounterDrawable(count, R.drawable.ic_add_card));
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
               // Toast.makeText(getApplicationContext(), "copied to clipboard", Toast.LENGTH_LONG).show();
               // doIncrease();
                if( SessionManager.logout()){
                    Intent in2 = new Intent (com.example.oumaima.lastproject.HomeAcitivity.this, com.example.oumaima.lastproject.loginActivity.class);
                    startActivity(in2);
                }


                return true;
            }
        });
/* */
//////SEARCH
        SearchView search=(SearchView)menu.findItem(R.id.action_search).getActionView();
        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //    (ListChoices.this).adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                theFilter.setText(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                theFilter.setText(newText);
                return true;
            }
        });
        return true;
    }

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        //get id  counter in menu
        View view = inflater.inflate(R.layout.counter_menuitem_layout, null);
        view.setBackgroundResource(backgroundImageId);
        //image view will deseapera if no count
        if (count == 0) {
            View counterTextPanel = view.findViewById(R.id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        }
        //image view will get  count
        else {
            TextView textView = (TextView) view.findViewById(R.id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private void doIncrease() {


        count++;
        invalidateOptionsMenu();
    }


}



