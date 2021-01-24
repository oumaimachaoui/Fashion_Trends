package com.example.oumaima.my_fragements;

import android.app.Activity;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HomeFragment6 extends Fragment {
    String urladdress="http://192.168.48.2/AndroidProject/Selectshoes.php";
    //String urladdress="//10.0.3.2/AndroidProject/Selectshoes.php";
    int[] id;
    String[] name;
    String[] type_product;
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
    Context context;
    ProgressDialog pDialog;
    String userid=null;
    String iNO=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  rootView = inflater.inflate(R.layout.fragment_home, container, false);
        listView = (ListView) rootView.findViewById(R.id.lview);

            StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
           // collectData();
            context = getActivity();
            context = getContext();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userid = bundle.getString("userid");
        }
      /*  Toast.makeText(context,"HOme fragement says::"+userid,
                Toast.LENGTH_LONG).show();*/
        Bundle b = getActivity().getIntent().getExtras();
        iNO = b.getString("userid");
      /*  Toast.makeText(context,"HOme fragement says::"+userid+"hani"+iNO,
                Toast.LENGTH_LONG).show();*/
          //  CustomListView customListView=new CustomListView((Activity) this.context,name,email,imagepath,color_product,rating_product,price_product,size_product);
           // listView.setAdapter(customListView);   /**/

        new com.example.oumaima.my_fragements.HomeFragment6.Webservice().execute(urladdress);
            return rootView;
        }

    class Webservice extends AsyncTask<String , Void,String> {
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
                id=new int[ja.length()];
                name=new String[ja.length()];
                email=new String[ja.length()];
                imagepath=new String[ja.length()];
                color_product=new String[ja.length()];
                rating_product=new int[ja.length()];
                price_product=new double[ja.length()];
                size_product=new String[ja.length()];
                type_product=new String[ja.length()];

                for(int i=0;i<=ja.length();i++){
                    jo=ja.getJSONObject(i);
                    id[i]=jo.getInt("id_product");
                    name[i]=jo.getString("title");
                    email[i]=jo.getString("description");
                    imagepath[i]=jo.getString("image");
                    color_product[i]=jo.getString("color_product");
                    rating_product[i]=jo.getInt("ratings");
                    price_product[i]=jo.getDouble("price");
                    size_product[i]=jo.getString("size_product");
                    type_product[i]=jo.getString("type_product");
                }

            }
            catch (Exception ex)
            {

                ex.printStackTrace();
            }
            context = getActivity();
            context = getContext();
            com.example.oumaima.my_fragements.CustomListView CustomListView=new com.example.oumaima.my_fragements.CustomListView((Activity) context,id,name,email,imagepath,color_product,rating_product,price_product,size_product, type_product);
            listView.setAdapter(CustomListView);
            pDialog.dismiss();
        }

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


    }
*/
}