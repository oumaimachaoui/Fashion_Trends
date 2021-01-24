package com.example.oumaima.my_fragements;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

public class HomeAcitivity extends AppCompatActivity {
    String prodctname=null;
    private DrawerLayout dl;
    private int count = 0;
    EditText theFilter;
    Button increaseButton;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    String namerr=null;
    String userid=null;
    //GridView gridview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_acitivity);
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
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {  /*
             int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                    case R.id.settings:
                        Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                    case R.id.mycart:
                        Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }

           */ return true;}

        });
        //Fragement navigation view
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Bundle extras2 = getIntent().getExtras();
        if(extras2 != null){
            userid = extras2.getString("userid");
            // editText.setText(username);
         //   Toast.makeText(getApplicationContext(),"userid        "+userid,Toast.LENGTH_LONG).show();
        }

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
                    new com.example.oumaima.my_fragements.HomeFragment3()).commit();
            Bundle bundle3 = new Bundle();
            bundle3.putString("userid", userid);
            new com.example.oumaima.my_fragements.HomeFragment3().setArguments(bundle3);
        }
        //robe fragement to getback
        else if((prodctname.trim().equals(m2.trim()))||(prodctname.trim().equals(n2.trim()))){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.example.oumaima.my_fragements.HomeFragment2()).commit();
            Bundle bundl = new Bundle();
            bundl.putString("userid", userid);
            new com.example.oumaima.my_fragements.HomeFragment2().setArguments(bundl);
        }
        //pantalon fragement to getback
        else if((prodctname.trim().equals(m3.trim()))||(prodctname.trim().equals(n3.trim()))){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.example.oumaima.my_fragements.HomeFragment4()).commit();
            Bundle bund = new Bundle();
            bund.putString("userid", userid);
            new com.example.oumaima.my_fragements.HomeFragment4().setArguments(bund);
        }
        //chaussure fragement to getback
        else if((prodctname.trim().equals(m4.trim()))||(prodctname.trim().equals(n4.trim()))){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.example.oumaima.my_fragements.HomeFragment6()).commit();
            Bundle bun2 = new Bundle();
            bun2.putString("userid", userid);
            new com.example.oumaima.my_fragements.HomeFragment6().setArguments(bun2);
        }
        //accessoire fragement to getback
        else if((prodctname.trim().equals(m5.trim()))||(prodctname.trim().equals(n5.trim()))){

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.example.oumaima.my_fragements.HomeFragment5()).commit();
            Bundle bun = new Bundle();
            bun.putString("userid", userid);
            new com.example.oumaima.my_fragements.HomeFragment5().setArguments(bun);
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.example.oumaima.my_fragements.HomeFragment()).commit();
            Bundle bu = new Bundle();
            bu.putString("userid", userid);
            new com.example.oumaima.my_fragements.HomeFragment().setArguments(bu);
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
                                Bundle bundle = new Bundle();
                                bundle.putString("userid", userid);
                                selectedFragment = new com.example.oumaima.my_fragements.HomeFragment3();
                                selectedFragment.setArguments(bundle);
                                break;
                           case R.id.nav_search:
                                Bundle bundle2 = new Bundle();
                                bundle2.putString("userid", userid);
                                selectedFragment = new com.example.oumaima.my_fragements.FavoritesFragment();
                                selectedFragment.setArguments(bundle2);
                                break;  /**/
                           case R.id.nav_favorites:

                                Bundle bundle3 = new Bundle();
                                bundle3.putString("userid", userid);
                                selectedFragment = new com.example.oumaima.my_fragements.Favorites2Fragement();
                                selectedFragment.setArguments(bundle3);
                                break;
                         /*   case R.id.nav_favorites2:
                                Bundle bundle4 = new Bundle();
                                bundle4.putString("userid", userid);
                                selectedFragment = new SearchFragment();
                                selectedFragment.setArguments(bundle4);
                                break;*/

                        }
                    }
                    ///robe ACTIVITY
                    else if((prodctname.trim().equals(m2.trim()))||(prodctname.trim().equals(n2.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new com.example.oumaima.my_fragements.HomeFragment2();
                                Bundle bundle4 = new Bundle();
                                bundle4.putString("userid", userid);
                                selectedFragment.setArguments(bundle4);
                                break;
                             case R.id.nav_search:
                                selectedFragment = new com.example.oumaima.my_fragements.FavoritesFragment();
                                Bundle bundle45 = new Bundle();
                                bundle45.putString("userid", userid);
                                selectedFragment.setArguments(bundle45);
                                break; /**/
                          case R.id.nav_favorites:
                                selectedFragment = new com.example.oumaima.my_fragements.Favorites2Fragement();
                                Bundle bundle9 = new Bundle();
                                bundle9.putString("userid", userid);
                                selectedFragment.setArguments(bundle9);

                                break;
                          /*  case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                Bundle po = new Bundle();
                                po.putString("userid", userid);
                                selectedFragment.setArguments(po);
                                Toast.makeText(getApplicationContext(),
                                        "\n*******:"+userid,
                                        Toast.LENGTH_LONG).show();

                                break;*/
                                 }
                            }
                    ///PANTS ACTIVITY
                        else if((prodctname.trim().equals(m3.trim()))||(prodctname.trim().equals(n3.trim()))){

                            switch (item.getItemId()) {
                                case R.id.nav_home:
                                    selectedFragment = new com.example.oumaima.my_fragements.HomeFragment4();
                                    Bundle bundle4 = new Bundle();
                                    bundle4.putString("userid", userid);
                                    selectedFragment.setArguments(bundle4);

                                    break;
                                case R.id.nav_search:
                                    selectedFragment = new com.example.oumaima.my_fragements.FavoritesFragment();
                                    Bundle mm2 = new Bundle();
                                    mm2.putString("userid", userid);
                                    selectedFragment.setArguments(mm2);

                                    break; /* */
                              case R.id.nav_favorites:
                                    selectedFragment = new com.example.oumaima.my_fragements.Favorites2Fragement();
                                    Bundle mm = new Bundle();
                                    mm.putString("userid", userid);
                                    selectedFragment.setArguments(mm);
                                    break;
                              /*  case R.id.nav_favorites2:
                                    selectedFragment = new SearchFragment();
                                    Bundle bundlep2 = new Bundle();
                                    bundlep2.putString("userid", userid);
                                    selectedFragment.setArguments(bundlep2);
                                    break;*/

                            }
                    }
                    ///sHOSE ACTIVITY
                    else if((prodctname.trim().equals(m4.trim()))||(prodctname.trim().equals(n4.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new com.example.oumaima.my_fragements.HomeFragment6();
                                Bundle bundle4 = new Bundle();
                                bundle4.putString("userid", userid);
                                selectedFragment.setArguments(bundle4);
                                break;
                           case R.id.nav_search:
                                selectedFragment = new com.example.oumaima.my_fragements.FavoritesFragment();
                                Bundle bundlew = new Bundle();
                                bundlew.putString("userid", userid);
                                selectedFragment.setArguments(bundlew);
                                break; /**/
                            case R.id.nav_favorites:
                                selectedFragment = new com.example.oumaima.my_fragements.Favorites2Fragement();
                                Bundle bundleo = new Bundle();
                                bundleo.putString("userid", userid);
                                selectedFragment.setArguments(bundleo);
                                break;
                          /*  case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                Bundle bundlem = new Bundle();
                                bundlem.putString("userid", userid);
                                selectedFragment.setArguments(bundlem);

                                break;*/

                        }}
                    ///accessoire ACTIVITY
                    else if((prodctname.trim().equals(m5.trim()))||(prodctname.trim().equals(n5.trim()))){

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new com.example.oumaima.my_fragements.HomeFragment5();
                                Bundle bundlet = new Bundle();
                                bundlet.putString("userid", userid);
                                selectedFragment.setArguments(bundlet);

                                break;
                           case R.id.nav_search:
                                selectedFragment = new com.example.oumaima.my_fragements.FavoritesFragment();
                                Bundle bundler = new Bundle();
                                bundler.putString("userid", userid);
                                selectedFragment.setArguments(bundler);

                                break; /**/
                            case R.id.nav_favorites:
                                selectedFragment = new com.example.oumaima.my_fragements.Favorites2Fragement();
                                Bundle bundlery = new Bundle();
                                bundlery.putString("userid", userid);
                                selectedFragment.setArguments(bundlery);

                                break;
                         /*   case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                Bundle bundleru = new Bundle();
                                bundleru.putString("userid", userid);
                                selectedFragment.setArguments(bundleru);


                                break;*/

                        }}
                    else{

                        switch (item.getItemId()) {
                            case R.id.nav_home:
                                selectedFragment = new com.example.oumaima.my_fragements.HomeFragment();
                                Bundle bundlee = new Bundle();
                                bundlee.putString("userid", userid);
                                selectedFragment.setArguments(bundlee);

                                break;
                          case R.id.nav_search:
                                selectedFragment = new com.example.oumaima.my_fragements.FavoritesFragment();
                                Bundle bundleep = new Bundle();
                                bundleep.putString("userid", userid);
                                selectedFragment.setArguments(bundleep);

                                break;  /**/
                            case R.id.nav_favorites:
                                selectedFragment = new com.example.oumaima.my_fragements.Favorites2Fragement();
                                Bundle bundlez = new Bundle();
                                bundlez.putString("userid", userid);
                                selectedFragment.setArguments(bundlez);

                                break;
                          /*  case R.id.nav_favorites2:
                                selectedFragment = new SearchFragment();
                                Bundle bundlea = new Bundle();
                                bundlea.putString("userid", userid);
                                selectedFragment.setArguments(bundlea);


                                break;*/

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
                doIncrease();
                return true;
            }
        });

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



