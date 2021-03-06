package com.example.oumaima.my_fragements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

public class CustomAdapter3 extends BaseAdapter  {

    private String[] nameproduct;
    private int[] qnt;
   // private String[] desc_product;
    private String[] imagepath;
    private String[] color_product;
    private double[] price_product;
    private String[] size_product;
    int i=1;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.56.1/AndroidProject/updateProduit3.php";
    String insertUrl2 = "http://192.168.56.1/AndroidProject/updateProduit.php";
    private Context context;
    Bitmap bitmap;

    private static LayoutInflater inflater=null;

    public CustomAdapter3(Context context, String[] nameproduct, double[] price_product, String[] imagepath, String[] color_product, String[] size_product, int[] qnt) {
        this.context = context;
        this.nameproduct=nameproduct;
        this.price_product=price_product;
        this.imagepath=imagepath;
        this.color_product=color_product;
        this.size_product=size_product;
        this.qnt=qnt;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return(imagepath.length);
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }



    public class Holder
    {
        TextView tv_nameproduct;
        TextView tv_price_product;
        TextView tv_colorproduct;
        TextView tv_sizeproduct;
        ImageView ivw;

        ImageButton r1;
        ImageButton r2;
        ImageButton r3;
        TextView compt;

    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        final View rowView;
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        rowView = inflater.inflate(R.layout.gritdlayout, null);
        holder.tv_nameproduct =(TextView) rowView.findViewById(R.id.tvnameproduct);
        holder.tv_price_product =(TextView) rowView.findViewById(R.id.tvdesc_product);
        holder.tv_colorproduct=(TextView)rowView.findViewById(R.id.tvcolor_product);
        holder.tv_sizeproduct=(TextView)rowView.findViewById(R.id.tvsize_product);
       // holder.compt=(TextView)rowView.findViewById(R.id.compt);
        holder.ivw=(ImageView)rowView.findViewById(R.id.imageView);

        holder.r1=(ImageButton)rowView.findViewById(R.id.r1);
        holder.r2=(ImageButton)rowView.findViewById(R.id.r2);
        holder.r3=(ImageButton)rowView.findViewById(R.id.r3);
        holder.compt.setText(String.valueOf(qnt[position]));
        holder.tv_nameproduct.setText(nameproduct[position]);
       // holder.ivw.setImageResource(Integer.parseInt(imagepath[position]));
        holder.tv_price_product.setText(String.valueOf(price_product[position]));
        holder.tv_colorproduct.setText(color_product[position]);
        holder.tv_sizeproduct.setText(size_product[position]);
        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              // Toast.makeText(convertView.getContext(), "You Clicked "+nameproduct[position]+qnt[position], Toast.LENGTH_SHORT).show();

            }

        });
        new GetImageFromURL(holder.ivw).execute(imagepath[position]);
        holder.r1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                qnt[position]++;
                    final String j = Integer.toString(qnt[position]);
                    holder.compt.setText(j);
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
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
                        parameters.put("Quantity",j);
                        return parameters;
                    }
                };
                requestQueue.add(request);

            }
        });
        holder.r2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(qnt[position]>1) {
                        qnt[position]--;
                        final String j = Integer.toString(qnt[position]);
                        holder.compt.setText(j);
                        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
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
                                parameters.put("Quantity",j);
                                return parameters;
                            }
                        };
                        requestQueue.add(request);
                    }
                    else{
                        holder.compt.setText("1");
                    }

            }
        });
        holder.r2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if(qnt[position]>1) {
                    qnt[position]--;
                    final String j = Integer.toString(qnt[position]);
                    holder.compt.setText(j);
                    StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
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
                            parameters.put("Quantity",j);
                            return parameters;
                        }
                    };
                    requestQueue.add(request);
                }
                else{
                    holder.compt.setText("1");
                }

            }
        });
        holder.r3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("nameproduct", nameproduct[position].toString());
                        parameters.put("add_card", "0");
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }
        });

        return rowView;
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