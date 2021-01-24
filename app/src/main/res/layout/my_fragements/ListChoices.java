package com.example.oumaima.my_fragements;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListChoices extends AppCompatActivity {
    ArrayList<Map<String, String>> listitem;
    private DrawerLayout dl;
    private int count = 0;
    EditText theFilter;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    Button increaseButton;
    SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_choices);

        theFilter = (EditText) findViewById(R.id.searchFilter);
        theFilter.setVisibility(View.GONE);
        //////getting id of the activity activity_list_choice
        dl = (DrawerLayout)findViewById(R.id.list_choix);
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


        //Récupération de la listview créée dans le fichier depuis main.xml
        ListView lv=(ListView)findViewById(R.id.listview);

        //déclaration de la liste des items
        listitem= buildData();

        //les éléments de chaque item avec leurs références dans item.xml
        String[] from = { "description","img" };
        int[] to = {R.id.txt_desc,R.id.logo };

        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
         adapter = new SimpleAdapter (this.getBaseContext(), listitem, R.layout.itemchoice,from,to);

        //On attribut à notre listView l'adapter que l'on vient de créer
        lv.setAdapter(adapter);

        //on met un écouteur d'évènement sur notre listView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) listitem.get(position);
                String desc=map.get("description");
                String name=map.get("nom");
                //on affiche un Toast avec le titre de l'élément sélectionné
                Toast.makeText(getApplicationContext(),"Stocke de :"+name,
                        Toast.LENGTH_LONG).show();
                Intent intentRegister = new Intent(com.example.oumaima.my_fragements.ListChoices.this,HomeAcitivity.class);
                intentRegister.putExtra("Name", name);
                startActivity(intentRegister);
               // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });


    }

    private ArrayList<Map<String, String>> buildData() {
        ArrayList<Map<String, String>> list = new ArrayList<Map<String, String>>();

        //on ajoute les hashMaps dans la ArrayList
        list.add(putData(getString(R.string.tricotstring),
                String.valueOf(R.drawable.tops2),"tricot"));
        list.add(putData(
                getString(R.string.robestring),
                String.valueOf(R.drawable.dress2),"robe"));

        list.add(putData(  getString(R.string.shoesstring),
                String.valueOf(R.drawable.pants2),"pantalon"));

        list.add(putData(getString(R.string.shoesstring),
                String.valueOf(R.drawable.shoes2),"chaussure"));

        list.add(putData(getString(R.string.shoesstring),
                String.valueOf(R.drawable.access2),"accessoire"));
        return list;
    }

    private HashMap<String, String> putData( String desc,String img,String nom) {
        //Création d'une HashMap pour insérer les informations du premier item de notre listView
        HashMap<String, String> map = new HashMap<String, String>();

        //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
        map.put("img", img);

        map.put("nom", nom);
        //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
        map.put("description", desc);


        return map;
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.testAction);
        //set counter function to icone
        menuItem.setIcon(buildCounterDrawable(count, R.drawable.ic_add_card));
//////SEARCH
        SearchView search=(SearchView)menu.findItem(R.id.action_search).getActionView();
        theFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                (com.example.oumaima.my_fragements.ListChoices.this).adapter.getFilter().filter(charSequence);
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
    //keep showing the icone wwhile incrementing the value
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
        //Accomplir les images de bitmap
      view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH); /* */
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    private void doIncrease() {
        count++;
        invalidateOptionsMenu();
    }


}
