package com.example.oumaima.my_fragements;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class Card2Fragement extends Fragment {
    ListView listView;
    GridView gridView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    TextView r1;
    TextView totalprice;
    Context context;
    SwipeRefreshLayout sp;
    TextView tt;
    ArrayList<HashMap<String,String>> list=new ArrayList<>();
    private ArrayList<String> size_product = new ArrayList<String>();
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<Integer> Id = new ArrayList<Integer>();
    private ArrayList<String> Image = new ArrayList<String>();
    private ArrayList<String> color_product = new ArrayList<String>();
    private ArrayList<Integer> ratings = new ArrayList<Integer>();
    private ArrayList<Float> price = new ArrayList<Float>();
    String userid=null;
    String iNO=null;
    String iNO2=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  rootView=inflater.inflate(R.layout.fragment_favorites, container, false);
        listView=(ListView)rootView.findViewById(R.id.lview);
        gridView=(GridView)rootView.findViewById(R.id.customgrid);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userid = bundle.getString("userid");
        }

     /*   Toast.makeText(context,"HOme fragement says::"+userid,
                Toast.LENGTH_LONG).show();*/
        Bundle b = getActivity().getIntent().getExtras();
       // iNO = b.getString("userid");
        context = getActivity();
        context = getContext();
        iNO2 = b.getString("userid");
      /*  Toast.makeText(context,"HOme fragement says::"+iNO2+"hani"+iNO,
                Toast.LENGTH_LONG).show();*/
  //      tt=(TextView)rootView.findViewById(R.id.tt);
//        tt.setVisibility(View.GONE);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        sp = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeLayout);
        //final DatabaseHelper2 myDb3 = new DatabaseHelper2(getActivity());
        final com.example.oumaima.my_fragements.DatabaseHelper3 myDb2 = new com.example.oumaima.my_fragements.DatabaseHelper3(getActivity());
        //star display
        //Cursor cursor = myDb3.getAllData();
        Cursor cursor = myDb2.getAllData();
        if(cursor.getCount() == 0) {
            // show message
            //tt.setVisibility(View.VISIBLE);
       //     tt.setVisibility(View.VISIBLE);
           // showMessage("Error","Nothing found");
        }
        if (cursor.moveToFirst()) {
            //tt.setVisibility(View.GONE);
            do {
                Id.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("ID"))));
                title.add(cursor.getString(cursor.getColumnIndex("title")));
                description.add(cursor.getString(cursor.getColumnIndex("description")));
                Image.add(cursor.getString(cursor.getColumnIndex("image")));
                size_product.add(cursor.getString(cursor.getColumnIndex("size_product")));
                ratings.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("ratings"))));
                price.add(Float.valueOf(cursor.getString(cursor.getColumnIndex("price"))));
                color_product.add(cursor.getString(cursor.getColumnIndex("color_product")));
            } while (cursor.moveToNext());
            //Toast.makeText(getContext(), "onRefresh2", Toast.LENGTH_SHORT).show();
        }
        com.example.oumaima.my_fragements.CustomAdapter4 ca = new com.example.oumaima.my_fragements.CustomAdapter4(getActivity(),Id,title,description,size_product,Image,ratings,color_product,price,sp);
        gridView.setAdapter(ca);
        cursor.close();
        myDb2.close();
        sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            //@Override
            public void onRefresh(){
                try {
                   Id.clear();
                    title.clear();
                    description.clear();
                    Image.clear();
                    size_product.clear();
                    ratings.clear();
                    price.clear();
                    color_product.clear(); /**/
                    Toast.makeText(getContext(), "onRefresh", Toast.LENGTH_SHORT).show();
                    //displayData();
                    final com.example.oumaima.my_fragements.DatabaseHelper2 myDb3 = new com.example.oumaima.my_fragements.DatabaseHelper2(getActivity());
                    //star display
                    Cursor cursor = myDb3.getAllData();
                    //tt.setVisibility(View.GONE);
                    if(cursor.getCount() == 0) {
                        // show message
                        //tt.setVisibility(View.VISIBLE);
                        showMessage("Error","Nothing found");
                    }
                    if (cursor.moveToFirst()) {
                        //tt.setVisibility(View.GONE);
                        do {
                            Id.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("ID"))));
                            title.add(cursor.getString(cursor.getColumnIndex("title")));
                            description.add(cursor.getString(cursor.getColumnIndex("description")));
                            Image.add(cursor.getString(cursor.getColumnIndex("image")));
                            size_product.add(cursor.getString(cursor.getColumnIndex("size_product")));
                            ratings.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("ratings"))));
                            price.add(Float.valueOf(cursor.getString(cursor.getColumnIndex("price"))));
                            color_product.add(cursor.getString(cursor.getColumnIndex("color_product")));
                        } while (cursor.moveToNext());
                        //Toast.makeText(getContext(), "onRefresh2", Toast.LENGTH_SHORT).show();
                    }
                    com.example.oumaima.my_fragements.CustomAdapter2 ca = new com.example.oumaima.my_fragements.CustomAdapter2(getActivity(),Id,title,description,size_product,Image,ratings,color_product,price,sp);
                    gridView.setAdapter(ca);
                    cursor.close();
                     myDb3.close();
                    //code to set adapter to populate list
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sp.setRefreshing(false);
              /*  new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                      //  sp.setRefreshing(false);
                    }
                }, 2000);*/
            }
        });



 /**/
/*
        listView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState)
            {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount)
            {
                int topRowVerticalPosition = (listView == null || listView.getChildCount() == 0) ? 0 : listView.getChildAt(0).getTop();
                sp.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
            }
        });
*/
        return rootView;
    }

public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


}
