package com.example.oumaima.my_fragements;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class FavoritesFragment extends Fragment {
    String urladdress="http://192.168.48.2/AndroidProject/json2.php";
    String urladdress2="http://192.168.48.2/AndroidProject/json3.php";
    String urladdress3="http://192.168.48.2/AndroidProject/showQtty.php";
    ProgressDialog pDialog;
    ArrayList<HashMap<String,String>> list=new ArrayList<>();
    ListView lv;
    /* ArrayList<String> name;
    int[] qnt;
   String[] totalprice;
    String[] email;
    String[] imagepath;
    String[] color_product;
    int[] rating_product;
    double[] price_product;
    String[] size_product;*/
    ListView listView;
    BufferedInputStream is;
    BufferedInputStream is2;
    String line=null;
    String result=null;
    String nbitems=null;
    String line2=null;
    String result2=null;
    TextView r1;
    TextView total_price;
    Context context;
    GridView gridview;
    SwipeRefreshLayout sp;
    String count;
    String iNO=null;
    String iNO2=null;
    TextView textView;
    private ArrayList<String> Image = new ArrayList<String>();
    private ArrayList<String> size_product = new ArrayList<String>();
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<Integer> Id = new ArrayList<Integer>();
    private ArrayList<String> color_product = new ArrayList<String>();
    private ArrayList<Integer> ratings = new ArrayList<Integer>();
    private ArrayList<Double> price = new ArrayList<Double>();
    private ArrayList<Integer> qnt_product = new ArrayList<Integer>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView= inflater.inflate(R.layout.fragment_favorites, container, false);
        gridview = (GridView)rootView.findViewById(R.id.customgrid);
        sp=(SwipeRefreshLayout)rootView.findViewById(R.id.swipeLayout);
        context = getActivity();
        context = getContext();
        com.example.oumaima.my_fragements.HomeAcitivity activity = (com.example.oumaima.my_fragements.HomeAcitivity) getActivity();
        //iNO2 = activity.getMyData();
        Bundle b = getActivity().getIntent().getExtras();
        iNO2 = b.getString("userid");
       /* Toast.makeText(context,"HOme fragement says::"+iNO2+"hani"+iNO,
                Toast.LENGTH_LONG).show();*/
       textView=(TextView) rootView.findViewById(R.id.totalprice);


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
                    qnt_product.clear();
                    color_product.clear(); /**/
                    Toast.makeText(getContext(), "onRefresh", Toast.LENGTH_SHORT).show();
                    //displayData();
                    final com.example.oumaima.my_fragements.DatabaseHelper3 myDb2 = new com.example.oumaima.my_fragements.DatabaseHelper3(getActivity());
                    //star display
                    //Cursor cursor = myDb3.getAllData();
                    Cursor cursor = myDb2.getAllData();
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
                            price.add(Double.valueOf(cursor.getString(cursor.getColumnIndex("price"))));
                            color_product.add(cursor.getString(cursor.getColumnIndex("color_product")));
                            qnt_product.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("Quantity"))));
                        } while (cursor.moveToNext());
                        //Toast.makeText(getContext(), "onRefresh2", Toast.LENGTH_SHORT).show();
                    }

                    com.example.oumaima.my_fragements.CustomAdapter custo=new com.example.oumaima.my_fragements.CustomAdapter(getActivity(),Id,title,price,Image,color_product,size_product, qnt_product);
                    gridview.setAdapter(custo);
                    cursor.close();
                    myDb2.close();
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


        /**
                //collectData();
                context = getActivity();
                context = getContext();
                CustomAdapter custom=new CustomAdapter(getActivity(),title,price,Image,color_product,size_product);
                gridview.setAdapter(custom);***/



                lv=(ListView)rootView.findViewById(R.id.listView);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       // Toast.makeText(getActivity(),list.get(i).get("totalPrix").toString(),Toast.LENGTH_SHORT ).show();
                      Toast.makeText(getActivity(),"Quantity: ",Toast.LENGTH_SHORT ).show();
                    }
                });
                ListAdapter adapter = new SimpleAdapter(
                        getActivity().getApplicationContext(), list,
                        R.layout.item, new String[] { "totalPrix"
                },new int[] { R.id.nom});
                lv.setAdapter(adapter);
              // new Webservice().execute(urladdress2);

     // new Webservice().execute(urladdress2);

      //  collectData();
        context = getActivity();
        context = getContext();
      /*  CustomAdapter custom=new CustomAdapter(getActivity(),name,price,Image,color_product,size_product);
        gridview.setAdapter(custom);*/
        ///total in chariot
       lv=(ListView)rootView.findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



           }
        });
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
                price.add(Double.valueOf(cursor.getString(cursor.getColumnIndex("price"))));
                color_product.add(cursor.getString(cursor.getColumnIndex("color_product")));
                qnt_product.add(Integer.valueOf(cursor.getString(cursor.getColumnIndex("Quantity"))));
            } while (cursor.moveToNext());
            //Toast.makeText(getContext(), "onRefresh2", Toast.LENGTH_SHORT).show();
        }
       /* CustomAdapter ca = new CustomAdapter(getActivity(),Id,title,description,size_product,Image,ratings,color_product,price,sp);
        gridview.setAdapter(ca);*/
        com.example.oumaima.my_fragements.CustomAdapter custo=new com.example.oumaima.my_fragements.CustomAdapter(getActivity(),Id,title,price,Image,color_product,size_product,qnt_product);
        gridview.setAdapter(custo);
        cursor.close();
        myDb2.close();



        return(rootView);
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
/*
    private void collectData()
    {
//Connection
        try{

            URL url=new URL(urladdress);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        //content
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();

        }

//JSON
        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            name=new String[ja.length()];
            email=new String[ja.length()];
            imagepath=new String[ja.length()];
            color_product=new String[ja.length()];
            rating_product=new int[ja.length()];
            price_product=new double[ja.length()];
            size_product=new String[ja.length()];
            qnt=new int[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                name[i]=jo.getString("title");
                email[i]=jo.getString("description");
                imagepath[i]=jo.getString("image");
                color_product[i]=jo.getString("color_product");
                rating_product[i]=jo.getInt("ratings");
                price_product[i]=jo.getDouble("price");
                size_product[i]=jo.getString("size_product");
                qnt[i]=jo.getInt("Quantity");
            }
        }
        catch (Exception ex)
        {

            ex.printStackTrace();
        }

    }


    class Webservice extends AsyncTask<String , Void,String> {

        @Override
        protected void onPreExecute () {

            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage("Connexion au serveur ..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            try {

                HttpURLConnection conn;

                URL url = new URL(strings[0]);
                conn = (HttpURLConnection) url.openConnection();

                conn.setDoInput(true);
                conn.setRequestMethod("POST");

                conn.setRequestProperty("Accept-Charset", "UTF-8");

                conn.setConnectTimeout(1000);

                conn.connect();

                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                conn.disconnect();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return  result.toString();
        }

        @Override
        protected void  onPostExecute (String  JSON_Reponse ) {
            pDialog.dismiss();
           // Toast.makeText(getActivity(), "Total number of Items are:" +String.valueOf(gridview.getNumColumns()) , Toast.LENGTH_LONG).show();
            nbitems=String.valueOf(gridview.getNumColumns());
            count=String.valueOf(gridview.getNumColumns());
            JSONObject jObj=null ;
            JSONArray jArr =null;

            // try parse the string to a JSON object
            try {
                jArr = new JSONArray(JSON_Reponse.toString());

                for (int x=0 ;x<jArr.length();x++ ) {

                    jObj =jArr.getJSONObject(x) ;
                    String totalPrix=jObj.getString("totalPrix") ;
                    HashMap<String,String> map =new HashMap<>();
                    map.put("totalPrix",totalPrix);
                    list.add(map) ;
                }
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            ListAdapter adapter = new SimpleAdapter(
                    getActivity().getApplicationContext(), list,
                    R.layout.item, new String[] { "totalPrix"
            },new int[] { R.id.nom});
            lv.setAdapter(adapter);
           // textView.setText((CharSequence) total_price);


        }

    }
*/
  }