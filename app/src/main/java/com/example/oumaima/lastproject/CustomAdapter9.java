package com.example.oumaima.lastproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter9 extends BaseAdapter  {
    com.example.oumaima.lastproject.DatabaseHelper3 myDb;
    /*
    private String[] nameproduct;
    private int[] qnt;
   // private String[] desc_product;
    private String[] imagepath;
    private String[] color_product;
    private double[] price_product;
    private String[] size_product;*/
    private ArrayList<Integer> Id = new ArrayList<Integer>();
    private ArrayList<String> nameproduct = new ArrayList<String>();
    private ArrayList<String> desc_product = new ArrayList<String>();
    private ArrayList<String> size_product = new ArrayList<String>();
    private ArrayList<String> imagepath = new ArrayList<String>();
    // private ArrayList<String> size_product = new ArrayList<String>();
    private ArrayList<Integer> qnt_product = new ArrayList<>();
    private ArrayList<String> color_product = new ArrayList<String>();
    private ArrayList<Double> price_product = new ArrayList<Double>();
    int a=1;
    int b=1;
    int i=1;
    String pst_qnt=null;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.56.1/AndroidProject/updateProduit3.php";
    String insertUrl2 = "http://192.168.56.1/AndroidProject/updateProduit.php";
    private Context context;
    Bitmap bitmap;

    private static LayoutInflater inflater=null;

    public CustomAdapter9(Context context, ArrayList<Integer> Id, ArrayList<String> nameproduct, ArrayList<Double> price_product, ArrayList<String> imagepath, ArrayList<String> color_product, ArrayList<String> size_product, ArrayList<Integer> qnt_product) {
        this.context = context;
        this.nameproduct=nameproduct;
        this.price_product=price_product;
        this.imagepath=imagepath;
        this.color_product=color_product;
        this.size_product=size_product;
        this.qnt_product=qnt_product;
        this.Id=Id;
        //this.qnt= this.qnt;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myDb = new DatabaseHelper3(context);

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return(imagepath.size());
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
        TextView tv_idproduct;
        ImageButton r1;
       /* ImageButton r2;
        ImageButton r3;
        TextView compt;*/

    }
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        final View rowView;
        //requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        rowView = inflater.inflate(R.layout.gritdlayout, null);
        holder.tv_nameproduct =(TextView) rowView.findViewById(R.id.tvnameproduct);
        //holder.tv_price_product =(TextView) rowView.findViewById(R.id.tvdesc_product);
        holder.tv_colorproduct=(TextView)rowView.findViewById(R.id.tvcolor_product);
        holder.tv_idproduct=(TextView)rowView.findViewById(R.id.tvid_product);
        holder.tv_sizeproduct=(TextView)rowView.findViewById(R.id.tvsize_product);
        holder.tv_price_product=(TextView)rowView.findViewById(R.id.tvprice_product);
        /*holder.compt=(TextView)rowView.findViewById(R.id.compt);*/
        holder.ivw=(ImageView)rowView.findViewById(R.id.imageView);

        holder.r1=(ImageButton)rowView.findViewById(R.id.r3);
      /*  holder.r2=(ImageButton)rowView.findViewById(R.id.r2);
        holder.r3=(ImageButton)rowView.findViewById(R.id.r3);*/
        pst_qnt=String.valueOf(qnt_product.get(position));
      //  holder.compt.setText(String.valueOf(qnt_product.get(position)));
       // a=Integer.parseInt(pst_qnt);
        holder.tv_nameproduct.setText(nameproduct.get(position));
        holder.tv_sizeproduct.setText(size_product.get(position));
        Glide.with(context).load(imagepath.get(position)).into(holder.ivw);
        //holder.tv_price_product.setText(String.valueOf(price_product.get(position)));
       holder.tv_colorproduct.setText(color_product.get(position));
      // holder.tv_colorproduct.setBackgroundColor(Color.parseColor(String.valueOf(color_product.get(position))));
        holder.tv_idproduct.setText(String.valueOf(Id.get(position)));
        rowView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              // Toast.makeText(convertView.getContext(), "You Clicked "+nameproduct[position]+qnt[position], Toast.LENGTH_SHORT).show();

            }

        });
       // a= Integer.parseInt(qnt_product.get(position));
       // new GetImageFromURL(holder.ivw).execute(imagepath.get(position));
/*        holder.r1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //value of quantity in layout
               a=qnt_product.get(position);
               a++;
                final String j = Integer.toString(a);*/
             //   holder.compt.setText(j);
               /*a++;
                holder.compt.setText(String.valueOf(a));*/
                /*
                boolean isUpdate = myDb.updateDataqt(Id.get(position).toString(),a);
                if(isUpdate == true)
                    Toast.makeText(v.getContext(),"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(v.getContext(),"Data not Updated",Toast.LENGTH_LONG).show();



                  /*   a= Integer.parseInt(String.valueOf(qnt_product.get(position)));
                    a++;
                    final String j = Integer.toString(a);
                    holder.compt.setText(j);
                  /*  StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
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

            *//*}
        });*/
      /*  holder.r2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(a>1) {
                        a=qnt_product.get(position);
                        a--;*/
                       // holder.compt.setText(String.valueOf(a));
                        final String j = Integer.toString(a);
                     //   holder.compt.setText(j);
                   /*   StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
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
                        requestQueue.add(request);*/
           /* }
                    else{
                     //   holder.compt.setText("1");
                    }

           }
        });*/
        /*
        holder.r2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
             //   a= Integer.parseInt(qnt_product.get(position));
           // int a= Integer.parseInt(String.valueOf(qnt_product.get(position)));
                if(a>1) {
                    a--;
                    holder.compt.setText(String.valueOf(a));
                    /*StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
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
        });*/
        holder.r1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                myDb.deleteData(Id.get(position));
                Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                /*
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
          */  }
        });

        return rowView;
    }
    /*
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
    */

}