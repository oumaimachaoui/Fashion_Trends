package com.example.oumaima.lastproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class SearchFragment extends Fragment {
    String urladdress="http://192.168.48.2/AndroidProject/json2.php";
    String[] name;
    String[] email;
    String[] imagepath;
    String[] color_product;
    int[] rating_product;
    double[] price_product;
    String[] size_product;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    TextView r1;
    TextView totalprice;
    Context context;
    ProgressDialog pDialog;
    String userid=null;
    String iNO=null;
    String iNO2=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  rootView=inflater.inflate(R.layout.fragment_search, container, false);
        //getFragmentManager().findFragmentById(R.id.fragment_container);
        listView=(ListView)rootView.findViewById(R.id.lview);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
/*        Toast.makeText(context,"HOme fragement says::",
                Toast.LENGTH_LONG).show();*/
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userid = bundle.getString("userid");
        }
/*        Toast.makeText(context,"HOme fragement says::"+userid,
                Toast.LENGTH_LONG).show();*/
        context = getActivity();
        context = getContext();
        HomeAcitivity activity = (HomeAcitivity) getActivity();
        iNO2 = activity.getMyData();
        Bundle b = getActivity().getIntent().getExtras();
        iNO2 = b.getString("userid");
        Toast.makeText(context,"HOme fragement says::"+iNO2+"hani"+iNO,
                Toast.LENGTH_LONG).show();
        //collectData();
        /*context = getActivity();
        context = getContext();

        CustomListView2 customListView=new CustomListView2(context,name,email,imagepath,color_product,rating_product,price_product,size_product);
        listView.setAdapter(customListView);*/
        if (android.os.Build.VERSION.SDK_INT > 9) { }
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

        new SearchFragment.Webservice().execute(urladdress,iNO2);

        return rootView;
    }


    class Webservice extends AsyncTask<String , Void,String> {
        Bundle a;
        @Override
        protected void onPreExecute () {

            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Connexion au serveur ..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
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

        return(result);
        }

        protected void  onPostExecute (String  JSON_Reponse) {
//JSON
            pDialog.dismiss();
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

                for(int i=0;i<=ja.length();i++){
                    jo=ja.getJSONObject(i);
                    name[i]=jo.getString("title");
                    email[i]=jo.getString("description");
                    imagepath[i]=jo.getString("image");
                    color_product[i]=jo.getString("color_product");
                    rating_product[i]=jo.getInt("ratings");
                    price_product[i]=jo.getDouble("price");
                    size_product[i]=jo.getString("size_product");
                }
            }
            catch (Exception ex)
            {

                ex.printStackTrace();
            }
            Toast.makeText(context,"FROM WEBSERVICE CLASS::"+iNO2,
                    Toast.LENGTH_LONG).show();
            context = getContext();
            CustomListView2 customListView=new CustomListView2(context,name,email,imagepath,color_product,rating_product,price_product,size_product,iNO2);
            listView.setAdapter(customListView);
        }
    }
/*
//Connection
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

        for(int i=0;i<=ja.length();i++){
            jo=ja.getJSONObject(i);
            name[i]=jo.getString("title");
            email[i]=jo.getString("description");
            imagepath[i]=jo.getString("image");
            color_product[i]=jo.getString("color_product");
            rating_product[i]=jo.getInt("ratings");
            price_product[i]=jo.getDouble("price");
            size_product[i]=jo.getString("size_product");
        }
    }
    catch (Exception ex)
    {

        ex.printStackTrace();
    }


}*/

    }

