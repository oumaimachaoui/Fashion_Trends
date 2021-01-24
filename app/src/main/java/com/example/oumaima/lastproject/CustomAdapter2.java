
package com.example.oumaima.lastproject;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class CustomAdapter2 extends BaseAdapter {
    private Context mContext;
    com.example.oumaima.lastproject.DatabaseHelper2 DatabaseHelper2;
    com.example.oumaima.lastproject.DatabaseHelper2 myDb;
    SQLiteDatabase db;
    int a=0;
    SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Integer> Id = new ArrayList<Integer>();
    private ArrayList<String> Title = new ArrayList<String>();
    private ArrayList<String> Description = new ArrayList<String>();
    private ArrayList<String> Size_product = new ArrayList<String>();
    private ArrayList<String> Image = new ArrayList<String>();
     private ArrayList<Integer> qnt = new ArrayList<Integer>();
    private ArrayList<Integer> Ratings = new ArrayList<Integer>();
    private ArrayList<String> Color_product = new ArrayList<String>();
    private ArrayList<Float> Price = new ArrayList<Float>();
    public CustomAdapter2(Activity context, ArrayList<Integer> Id, ArrayList<String> Title, ArrayList<String> Description, ArrayList<String> Size_product
            , ArrayList<String> Image, ArrayList<Integer> Ratings, ArrayList<String> Color_product, ArrayList<Float> Price, SwipeRefreshLayout sp, ArrayList<Integer> qnt)
    {
        this.mContext = context;
        this.Id = Id;
        this.Title = Title;
        this.Description = Description;
        this.Size_product=Size_product;
        this.Image=Image;
        this.Color_product=Color_product;
        this.Price=Price;
        this.Ratings=Ratings;
        //this.swipeRefreshLayout = swipeRefreshLayout;
        this.qnt=qnt;
        myDb = new DatabaseHelper2(context);
    }
    @Override
    public int getCount() {
        return Id.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       // final DatabaseHelper2 myDb = new DatabaseHelper2(context);
        final viewHolder holder;
        DatabaseHelper2 =new DatabaseHelper2((FragmentActivity) mContext);
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout3, null);
            holder = new viewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.tvsize_product);
            holder.title = (TextView) convertView.findViewById(R.id.tvnameproduct);
            holder.description = (TextView) convertView.findViewById(R.id.tvdesc_product);
            holder.Size_product = (TextView) convertView.findViewById(R.id.tvsize_product);
            holder.color_product = (TextView) convertView.findViewById(R.id.tvcolor_product);
            // holder.ratings = (TextView) convertView.findViewById(R.id.tvrating_product);

            holder.price = (TextView) convertView.findViewById(R.id.tvprice_product);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView);
            holder.r3 = (ImageButton) convertView.findViewById(R.id.r3);
            holder.r2 = (ImageButton) convertView.findViewById(R.id.r2);
            holder.r1 = (ImageButton) convertView.findViewById(R.id.r1);
            holder.compt = (TextView) convertView.findViewById(R.id.compt);
            holder.ratings=(RatingBar)convertView.findViewById(R.id.tvrating_product);


            // holder.sp=(SwipeRefreshLayout)convertView.findViewById(R.id.swipeLayout);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        //String.valueOf()

        LayerDrawable stars = (LayerDrawable)  holder.ratings.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);  /* */

        holder.id.setText(String.valueOf(Id.get(position)));
        holder.title.setText(Title.get(position));
        holder.description.setText(Description.get(position));
        holder.Size_product.setText(Size_product.get(position));
        //holder.ratings.setText(String.valueOf(Ratings.get(position)));
        holder.ratings.setRating(Float.parseFloat(String.valueOf(Ratings.get(position))));
        holder.color_product.setText(Color_product.get(position));
        holder.price.setText(String.valueOf(Price.get(position)));
       // holder.compt.setText(String.valueOf(qnt.get(position)));
        //First Step: convert ArrayList to an Object array.
        //Object[] objNames = Color_product.toArray();
        //Second Step: convert Object array to String array
        //Integer[] strNames = Arrays.copyOf(objNames, objNames.length, String[].class);
        final Object[] myArray = qnt.toArray();
        //int[] intArray = ArrayUtils.toPrimitive(qnt.toArray(new Integer[0]));
        holder.compt.setText(String.valueOf(myArray[position]));
        //afficher les images d'un url a une image bitmap en utilisant library glide
        Glide.with(mContext).load(Image.get(position)).into(holder.image);
        final View finalConvertView1 = convertView;

        final View finalConvertView = convertView;
        holder.r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                myDb.deleteData(Id.get(position));
                Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
                //holder.sp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                //  @Override
                // public void onRefresh() {

                // Toast.makeText(v.getContext(), "Hello", Toast.LENGTH_SHORT).show();
                //final View finalConvertView = finalConvertView;


                // }
                //else{
                //Toast.makeText(v.getContext(), "Error", Toast.LENGTH_SHORT).show();
                //}
                //  }
                //});
            }
        });
        holder.r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
               // myDb.deleteData(Id.get(position));
                Toast.makeText(v.getContext(), "plus", Toast.LENGTH_SHORT).show();
                myArray[position] =Integer.parseInt(String.valueOf(myArray[position]))+ 1;
                       // Integer.toString();
                holder.compt.setText(String.valueOf(myArray[position]));
                boolean isUpdate = myDb.updateCard(String.valueOf(myArray[position]),String.valueOf(Id.get(position)));
                if(isUpdate == true)
                    Toast.makeText(v.getContext(),"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(v.getContext(),"Data not Updated",Toast.LENGTH_LONG).show();


              //  position++;
              //  qnt.add(a);
              //  holder.compt.setText(String.valueOf(qnt.add(a)));
               /* qnt[position]++;
                final String j = Integer.toString(qnt[position]);
                holder.compt.setText(j);/**/

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
                requestQueue.add(request);*/
/*

 */}
        });
        holder.r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                // myDb.deleteData(Id.get(position));
                Toast.makeText(v.getContext(), "moins", Toast.LENGTH_SHORT).show();
                if(Integer.parseInt(String.valueOf(myArray[position]))>1){
                myArray[position] =Integer.parseInt(String.valueOf(myArray[position]))- 1;
                //a--;
                holder.compt.setText(String.valueOf(myArray[position]));
                boolean isUpdate = myDb.updateCard(String.valueOf(myArray[position]),String.valueOf(Id.get(position)));
                if(isUpdate == true)
                    Toast.makeText(v.getContext(),"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(v.getContext(),"Data not Updated",Toast.LENGTH_LONG).show();
                }
                else{
                    holder.compt.setText(String.valueOf(myArray[position]));
                }
              //  a--;
              //  qnt.add(a);
              //  holder.compt.setText(String.valueOf(qnt.add(a)));
               /*int i = 0;
                while (i < 10){
                    String j = String.valueOf( i );
                    holder.compt.setText(j);
                    i++;
                }*/
               /* if(qnt[position]>1) {
                    qnt[position]--;
                    final String j = Integer.toString(qnt[position]);
                    holder.compt.setText(j);*/

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
                    requestQueue.add(request);*/
            /*    }
                else{
                    holder.compt.setText("1");
                }
*/
            }
        });
        return convertView;
    }
    public class viewHolder {
        TextView id;
        TextView title;
        TextView description;
        TextView Size_product;
        //TextView ratings;
        RatingBar ratings;
        TextView color_product;
        TextView price;
        ImageView image;
        ImageButton r3;
        ImageButton r2;
        ImageButton r1;
        TextView compt;
        //SwipeRefreshLayout sp;
    }
    public class GetImageFromURL extends AsyncTask<String,Void,Bitmap>
    {

        ImageView imageView;
        public GetImageFromURL(ImageView imgv)
        {
            this.imageView=imgv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            Bitmap bitmap = null;

            try{

                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return bitmap;
        }}
}

