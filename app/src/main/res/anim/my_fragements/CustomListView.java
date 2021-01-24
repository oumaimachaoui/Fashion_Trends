package com.example.oumaima.my_fragements;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**/


public class CustomListView extends ArrayAdapter<String> {
    com.example.oumaima.my_fragements.DatabaseHelper2 myDb;
    com.example.oumaima.my_fragements.DatabaseHelper3 myDb2;
    private int[] id;
    private String[] nameproduct;
    private String[] desc_product;
    private String[] imagepath;
    private String[] color_product;
    private int[] rating_product;
    private double[] price_product;
    private String[] size_product;
    private String[] type_product;
    int i=0;
    //DatabaseHelper myDb;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.48.2/AndroidProject/updateProduit.php";
    String insertUrl2 = "http://192.168.48.2/AndroidProject/updateProduit2.php";
    private Context context;
    Bitmap bitmap;

    public CustomListView(Activity context, int[] id, String[] nameproduct, String[] desc_product, String[] imagepath, String[] color_product, int[] rating_product, double[] price_product, String[] size_product, String[] type_product) {
        super(context, R.layout.layout,nameproduct);
        this.context = context;
        this.nameproduct=nameproduct;
        this.id=id;
        this.desc_product=desc_product;
        this.imagepath=imagepath;
        this.color_product=color_product;
        this.rating_product=rating_product;
        this.price_product=price_product;
        this.size_product=size_product;
        this.type_product=type_product;
        myDb = new com.example.oumaima.my_fragements.DatabaseHelper2(context);
        myDb2 = new com.example.oumaima.my_fragements.DatabaseHelper3(context);
    }

    @NonNull
    @Override

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        ViewHolder viewHolder=null;
        final com.example.oumaima.my_fragements.DatabaseHelper2 myDb = new com.example.oumaima.my_fragements.DatabaseHelper2(context);
        final com.example.oumaima.my_fragements.DatabaseHelper3 myDb2 = new com.example.oumaima.my_fragements.DatabaseHelper3(context);
        if(r==null){
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           //LayoutInflater layoutInflater=context.getLayoutInflater();
             r=layoutInflater.inflate(R.layout.layout,null,true);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder=(ViewHolder)r.getTag();

        }

     LayerDrawable stars = (LayerDrawable)  viewHolder.tv_ratingproduct.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);  /* */

        viewHolder.tv_nameproduct.setText(nameproduct[position]);
        viewHolder.tv_descproduct.setText(desc_product[position]);
        viewHolder.tv_sizeproduct.setText(size_product[position]);
        ////////viewHolder.tv_ratingproduct.setText(String.valueOf( rating_product[position]));
        viewHolder.tv_ratingproduct.setRating(rating_product[position]);
        viewHolder.tv_priceproduct.setText(String.valueOf(price_product[position]));
        //viewHolder.tv_priceproduct.setText(String.valueOf(price_product[position]));
       // viewHolder.tv_typeproduct.setText(type_product[position]);
                //if(color_product[position]=="rose"){
        viewHolder.tv_colorproduct.setBackgroundColor(Color.parseColor(color_product[position]));
       // viewHolder.tv_colorproduct.setText(color_product[position]);
                    //viewHolder.tv_colorproduct.setBackgroundResource(R.color.colorAccent);
       // viewHolder.tv_colorproduct.setBackgroundColor((Color.parseColor(color_product[position])));
               // }
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.r1.setTag(position);
        viewHolder.r2.setTag(position);
        final ViewHolder finalViewHolder1 = viewHolder;
        final ViewHolder finalViewHolder2 = viewHolder;
        viewHolder.r1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                i++;
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            finalViewHolder2.r1.setBackgroundResource(R.drawable.ic_add_card);
                            boolean isInserted = myDb2.insertData(nameproduct[position].toString(),
                                    desc_product[position].toString(),(imagepath[position]),
                                    color_product[position].toString(),size_product[position],"1","shoes",price_product[position],rating_product[position],"2");
                            if(isInserted == true){
                               // Toast.makeText(view.getContext(), "onResponse", Toast.LENGTH_SHORT).show();
                                Toast.makeText(view.getContext(), "ajouter au panier", Toast.LENGTH_SHORT).show();
                                Intent intt = new Intent(view.getContext(), com.example.oumaima.my_fragements.myService.class);
                                intt.putExtra("nameproduct",nameproduct[position].toString());
                                context.startService(intt);
                            }
                            else{
                                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                           /* StringRequest requestadd = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                   // Toast.makeText(view.getContext(), "onResponse", Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(view.getContext(), "id is :  "+id[position], Toast.LENGTH_SHORT).show();
                                    System.out.println(response.toString());
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(view.getContext(), "onErrorResponse", Toast.LENGTH_SHORT).show();
                                }
                            }) {

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> parameters  = new HashMap<String, String>();
                                    parameters.put("nameproduct",nameproduct[position].toString());
                                    parameters.put("add_card","1");
                                    parameters.put("Quantity","1");

                                    return parameters;
                                }
                            };
                            requestQueue.add(requestadd);*/

                           Toast.makeText(view.getContext(), "ajouter au panier", Toast.LENGTH_SHORT).show();
                         Intent intt = new Intent(view.getContext(), com.example.oumaima.my_fragements.myService.class);
                            intt.putExtra("nameproduct",nameproduct[position].toString());
                            context.startService(intt); /**/
                            /*Intent intt2 = new Intent(view.getContext(),loginActivity.class);
                            intt2.putExtra("Name",type_product[position].toString());
                            context.startActivity(intt2);*/
                           // Toast.makeText(view.getContext(), "TYPE:"+type_product[position].toString(), Toast.LENGTH_SHORT).show();

                        } else {
                            finalViewHolder2.r1.setBackgroundResource(R.drawable.ic_add_full_card);
                            StringRequest requestadd = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    System.out.println(response.toString());
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> parameters  = new HashMap<String, String>();
                                    parameters.put("nameproduct",nameproduct[position].toString());
                                    parameters.put("add_card","0");
                                    parameters.put("Quantity","0");
                                  //  Toast.makeText(view.getContext(), "supprimer de panier", Toast.LENGTH_SHORT).show();
                                    return parameters;
                                }
                            };
                            requestQueue.add(requestadd);
                           // Toast.makeText(view.getContext(), "supprimer de panier", Toast.LENGTH_SHORT).show();

                        }
                        i=0;
                    }
                },500);

            }

        });
        viewHolder.r2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View view) {
                i++;
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            finalViewHolder2.r2.setBackgroundResource(R.drawable.ic_full_favoris);
                            boolean isInserted = myDb.insertData(nameproduct[position].toString(),
                                    desc_product[position].toString(),(imagepath[position]),
                                    color_product[position].toString(),size_product[position],"1","shoes",price_product[position],rating_product[position],"2");
                            if(isInserted == true){
                                Toast.makeText(view.getContext(), "onResponse", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(view.getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                            StringRequest request = new StringRequest(Request.Method.POST, insertUrl2, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    System.out.println(response.toString());
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> parameters  = new HashMap<String, String>();
                                    parameters.put("nameproduct",nameproduct[position].toString());
                                    parameters.put("like_product","1");
                                    return parameters;
                                }
                            };
                            requestQueue.add(request);
                            Toast.makeText(view.getContext(), "ajouter au favoris", Toast.LENGTH_SHORT).show();
                           /*Intent intt2 = new Intent(view.getContext(),myService.class);
                            intt2.putExtra("nameproduct",nameproduct[position].toString());
                            context.startService(intt2);*/

                        } else {
                            finalViewHolder2.r2.setBackgroundResource(R.drawable.ic_favoris);
                            StringRequest request = new StringRequest(Request.Method.POST, insertUrl2, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {

                                    System.out.println(response.toString());
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            }) {

                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {
                                    Map<String,String> parameters  = new HashMap<String, String>();
                                    parameters.put("nameproduct",nameproduct[position].toString());
                                    parameters.put("like_product","0");
                                    return parameters;
                                }
                            };
                            requestQueue.add(request);
                            Toast.makeText(view.getContext(), "supprimer de favoris", Toast.LENGTH_SHORT).show();

                        }
                        i=0;
                    }
                },500);

            }

        });
        new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);

        return r;
    }

    class ViewHolder{

        TextView tv_nameproduct;
        TextView tv_descproduct;
        TextView tv_colorproduct;
        TextView tv_sizeproduct;
       // TextView tv_ratingproduct;
        RatingBar tv_ratingproduct;
        TextView tv_priceproduct;
       // TextView tv_typeproduct;
        ImageView ivw;

        ImageButton r1;
        ImageButton r2;
        ViewHolder(View v){
            tv_nameproduct=(TextView)v.findViewById(R.id.tvnameproduct);
            tv_descproduct=(TextView)v.findViewById(R.id.tvdesc_product);
            tv_colorproduct=(TextView)v.findViewById(R.id.tvcolor_product);
            tv_sizeproduct=(TextView)v.findViewById(R.id.tvsize_product);
           // tv_ratingproduct=(TextView)v.findViewById(R.id.tvrating_product);
            tv_ratingproduct=(RatingBar)v.findViewById(R.id.tvrating_product);
            tv_priceproduct=(TextView)v.findViewById(R.id.tvprice_product);
          //  tv_typeproduct=(TextView)v.findViewById(R.id.tvtype_product);
            ivw=(ImageView)v.findViewById(R.id.imageView);


            r1=(ImageButton)v.findViewById(R.id.r1);
            r2=(ImageButton)v.findViewById(R.id.r2);
        }

    }

    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>
    {

        ImageView imgView;
        public GetImageFromURL(ImageView imgv)
        {
            this.imgView=imgv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            bitmap=null;

            try{

                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){

            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }
    }





}
