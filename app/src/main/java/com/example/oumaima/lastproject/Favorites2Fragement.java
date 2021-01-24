package com.example.oumaima.lastproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Thread.sleep;

/*
@Nullable
@Override
public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment2_favorites, container, false);
        }*/
public class Favorites2Fragement extends Fragment {
    //int[] rating_product;
    //double[] price_product;
    //String[] size_product;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    TextView r1;
    TextView totalprice;
    Context context;
    SwipeRefreshLayout sp;
    TextView tt;
    ProgressDialog pDialog;
    ArrayList<HashMap<String,String>> list=new ArrayList<>();
    SQLiteDatabase myDb;
    DatabaseHelper2 controllerd = new DatabaseHelper2(getActivity());
    SQLiteDatabase db;
    private ArrayList<String> size_product = new ArrayList<String>();
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<Integer> Id = new ArrayList<Integer>();
    private ArrayList<String> Image = new ArrayList<String>();
    private ArrayList<String> color_product = new ArrayList<String>();
    private ArrayList<Integer> ratings = new ArrayList<Integer>();
    private ArrayList<Float> price = new ArrayList<Float>();
    private ArrayList<Integer> qnt = new ArrayList<Integer>();
    private ArrayList<Float> total = new ArrayList<Float>();
    String userid=null;
    String iNO=null;
    String iNO2=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  rootView=inflater.inflate(R.layout.fragment2_favorites, container, false);
        listView=(ListView)rootView.findViewById(R.id.lview);
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
        tt=(TextView)rootView.findViewById(R.id.tt);
        tt.setVisibility(View.GONE);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        totalprice=(TextView)rootView.findViewById(R.id.totalprice);
        totalprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Achats.class);
                ///// Toast.makeText(LoginActivity.this, "valid username and password", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        sp = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeLayout);
        final DatabaseHelper2 myDb3 = new DatabaseHelper2(getActivity());
        //final DatabaseHelper3 myDb2 = new DatabaseHelper3(getActivity());

               Cursor res = myDb3.gettotal();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                   // buffer.append("totalPrix :"+ res.getString(0)+"\n");
                    totalprice.setText("TotalPrix :"+res.getString(0));
                }

                // Show all data
                //showMessage("Data",buffer.toString());
               // Toast.makeText(getContext(), "totalprice", Toast.LENGTH_SHORT).show();


        //star display
        Cursor cursor = myDb3.getAllData();
        ///Cursor cursor = myDb2.getAllData();
        if(cursor.getCount() == 0) {
            // show message
            //tt.setVisibility(View.VISIBLE);
            tt.setVisibility(View.VISIBLE);
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
                qnt.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("Quantity"))));
            } while (cursor.moveToNext());
            //Toast.makeText(getContext(), "onRefresh2", Toast.LENGTH_SHORT).show();
        }
        CustomAdapter2 ca = new CustomAdapter2(getActivity(),Id,title,description,size_product,Image,ratings,color_product,price,sp,qnt);
        listView.setAdapter(ca);
        cursor.close();
        myDb3.close();
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
                    color_product.clear();
                    qnt.clear();
                    totalprice.setText("");
                    /**/
                    Toast.makeText(getContext(), "onRefresh", Toast.LENGTH_SHORT).show();
                    //displayData();
                    final DatabaseHelper2 myDb3 = new DatabaseHelper2(getActivity());
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
                            qnt.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("Quantity"))));
                        } while (cursor.moveToNext());
                        //Toast.makeText(getContext(), "onRefresh2", Toast.LENGTH_SHORT).show();
                    }
                    CustomAdapter2 ca = new CustomAdapter2(getActivity(),Id,title,description,size_product,Image,ratings,color_product,price,sp,qnt);
                    listView.setAdapter(ca);
                    cursor.close();
                    myDb3.close();
                    //total_price_refresh
                    Cursor res = myDb3.gettotal();
                    if(res.getCount() == 0) {
                        // show message
                        showMessage("Error","Nothing found");
                    }

                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()) {
                        // buffer.append("totalPrix :"+ res.getString(0)+"\n");
                        totalprice.setText("TotalPrix :"+res.getString(0));
                    }
                    res.close();
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
        /**/
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
