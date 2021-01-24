package com.example.oumaima.lastproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.TextView;

import java.io.InputStream;

/*
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
*/

/**
 *  on 13-02-2018.
 */

public class CustomListView2 extends  ArrayAdapter<String> {

    private String[] nameproduct;
    private String[] desc_product;
    private String[] imagepath;
    private String[] color_product;
    private int[] rating_product;
    private double[] price_product;
    private String[] size_product;
    private String iNO2;
    int i=0;
    //DatabaseHelper myDb;
   // RequestQueue requestQueue;
    String insertUrl = "http://192.168.56.1/AndroidProject/insertProduit.php";

    private Context context;
    Bitmap bitmap;

    public CustomListView2(Context context, String[] nameproduct, String[] desc_product, String[] imagepath, String[] color_product, int[] rating_product, double[] price_product, String[] size_product, String iNO2) {
        super(context, R.layout.layout2,nameproduct);
        this.context = context;
        this.nameproduct=nameproduct;
        this.desc_product=desc_product;
        this.imagepath=imagepath;
        this.color_product=color_product;
        this.rating_product=rating_product;
        this.price_product=price_product;
        this.size_product=size_product;
        this.iNO2=iNO2;
    }

    @NonNull
    @Override

    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View r=convertView;

        ViewHolder viewHolder=null;

        if(r==null){
            //r= LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
           //  r=((Activity)getContext()).getLayoutInflater().inflate(R.layout.layout,null,true);
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
        /*
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent= new Intent(mContext,FeedActivitySingle.class);
                intent.putExtra(FeedActivitySingle.EXTRA_DESIGN, notifications.getString(ParseConstants.KEY_SENDER_FEED_OBJECT_ID));
                mContext.startActivity(intent);
                Toast.makeText(MainActivity.class,
                        "Hello",
                        Toast.LENGTH_LONG).show();*/
               // Toast.makeText(v.getContext(), "Hello", Toast.LENGTH_SHORT).show();
        /*
            }

        });*/

        viewHolder.tv_nameproduct.setText(nameproduct[position]);
        viewHolder.tv_descproduct.setText(desc_product[position]);
        viewHolder.tv_colorproduct.setText(color_product[position]);
        viewHolder.tv_ratingproduct.setText(String.valueOf( rating_product[position]));
        viewHolder.tv_priceproduct.setText(String.valueOf(price_product[position]));
        viewHolder.tv_sizeproduct.setText(size_product[position]);

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.r1.setTag(position);
        viewHolder.r2.setTag(position);
        final ViewHolder finalViewHolder1 = viewHolder;
        final ViewHolder finalViewHolder2 = viewHolder;
        viewHolder.r1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                i++;
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                            //finalViewHolder2.r1.setBackgroundResource(R.drawable.ic_add_card);
                        } else {
                           // finalViewHolder2.r1.setBackgroundResource(R.drawable.ic_add_full_card);

                        }
                        i=0;
                    }
                },500);

            }

        });
        viewHolder.r2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                i++;
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (i == 1) {
                           // finalViewHolder2.r2.setBackgroundResource(R.drawable.ic_full_favoris);
                        } else {
                            //finalViewHolder2.r2.setBackgroundResource(R.drawable.ic_favoris);

                        }
                        i=0;
                    }
                },500);

            }

        });
        new GetImageFromURL(viewHolder.ivw).execute(imagepath[position]);

        return r;
    }

    static class ViewHolder{

        TextView tv_nameproduct;
        TextView tv_descproduct;
        TextView tv_colorproduct;
        TextView tv_sizeproduct;
        TextView tv_ratingproduct;
        TextView tv_priceproduct;
        ImageView ivw;

        ImageButton r1;
        ImageButton r2;
        ViewHolder(View v){
            tv_nameproduct=(TextView)v.findViewById(R.id.tvnameproduct);
            tv_descproduct=(TextView)v.findViewById(R.id.tvdesc_product);
            tv_colorproduct=(TextView)v.findViewById(R.id.tvcolor_product);
            tv_sizeproduct=(TextView)v.findViewById(R.id.tvsize_product);
            tv_ratingproduct=(TextView)v.findViewById(R.id.tvrating_product);
            tv_priceproduct=(TextView)v.findViewById(R.id.tvprice_product);

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





