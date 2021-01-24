package com.example.oumaima.lastproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserProfile extends AppCompatActivity {

    TextView helloUser;
    FloatingActionButton btnviewuser;
    FloatingActionButton button2;
    SQLiteDatabase db;
    String useremail = null;
    String userid = null;
    String username=null;
    String userpass=null;
    String userbirth=null;
    String usersex=null;
    EditText editText;
    EditText layoutemail;
    EditText layoutpass;
    EditText layoutuser;
    EditText layoutsex;
    EditText layoutbirth;
    TextView helloEmail;
    TextView hellopass;
    TextView hellobirth;
    TextView hellosex;
    String userid2 = null;
    String pass=null;
    String name=null;
    //this is the navigation and the tool bar menu
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //from navigation
        dl = (DrawerLayout)findViewById(R.id.mymain);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.call:
                        Toast.makeText(com.example.oumaima.lastproject.UserProfile.this, "call",Toast.LENGTH_SHORT).show();
                        Intent in = new Intent (Intent.ACTION_DIAL, Uri.parse( "tel:0677218995"));
                        startActivity(in);
                        return true;
                    case R.id.mycart:
                        Toast.makeText(com.example.oumaima.lastproject.UserProfile.this, "mycart",Toast.LENGTH_SHORT).show();
                        Intent in2 = new Intent (com.example.oumaima.lastproject.UserProfile.this, com.example.oumaima.lastproject.ListChoices.class);
                        startActivity(in2);
                        return true;
                    case R.id.msg:
                        Toast.makeText(com.example.oumaima.lastproject.UserProfile.this, "msg",Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent( Intent.ACTION_SENDTO, Uri.parse("sms:0677218995"));
                        startActivity(intent);
                        intent.putExtra("sms", "ici un message ");
                        return true;
                    case R.id.settings:
                       // Toast.makeText(com.example.oumaima.lastproject.UserProfile.this, "aide",Toast.LENGTH_SHORT).show();
                        android.support.v7.app.AlertDialog.Builder builder=new android.support.v7.app.AlertDialog.Builder(UserProfile.this); //Home is name of the activity
                        builder.setMessage("Do you want to exit?");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                finish();
                                Intent i=new Intent();
                                i.putExtra("finish", true);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
                                //startActivity(i);
                                finish();

                            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        android.support.v7.app.AlertDialog alert=builder.create();
        alert.show();
        return true;
        default:
        return true;
    }

            }

        });
        /*
         *
         */

        helloUser = (TextView) findViewById(R.id.textView);
        helloEmail= (TextView) findViewById(R.id.txt_bio2);
        hellopass=(TextView) findViewById(R.id.txt_bio3);
        hellobirth=(TextView) findViewById(R.id.textView4);
        hellosex=(TextView) findViewById(R.id.textView5);

        // editText=(EditText)findViewById(R.id.editText);

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            useremail = extras.getString("EMAIL");
            userid2  = extras.getString("id");

        }


        final com.example.oumaima.lastproject.DatabaseHelper myDb = new com.example.oumaima.lastproject.DatabaseHelper(this);
        Cursor res = myDb.getDatausermail(useremail);
        if(res.getCount() == 0) {
            // show message
            showMessage("Error","Nothing found");
            return;
        }

        // StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            //call the columns of user to show them in activity
            helloUser.setText("Welcome :" + res.getString(1));
            helloEmail.setText( res.getString(2));
            hellopass.setText(res.getString(3));
            hellobirth.setText(res.getString(4));
            hellosex.setText( res.getString(5));
            //call the columns of user to show them in layout
            userid=res.getString(0);
            userpass=res.getString(3);
            useremail= res.getString(2);
            username=res.getString(1);
            userbirth=res.getString(4);
            usersex=res.getString(5);
            Toast.makeText(com.example.oumaima.lastproject.UserProfile.this,"Data Update"+userid,Toast.LENGTH_LONG).show();
        }


        // Show all data
        //showMessage("Data",buffer.toString());
        /***
         *
         *
         **
         *
         ***/

        //viewuser();
        updateuser();
        btnviewuser = (FloatingActionButton) findViewById(R.id.btnviewuser);

        viewuser();
    }




    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }






    public void updateuser(){
        button2=(FloatingActionButton)findViewById(R.id.btnviewuser);
        final com.example.oumaima.lastproject.DatabaseHelper myDb = new com.example.oumaima.lastproject.DatabaseHelper(this);
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(com.example.oumaima.lastproject.UserProfile.this);
                        LayoutInflater inflater = com.example.oumaima.lastproject.UserProfile.this.getLayoutInflater();
                        v = inflater.inflate(R.layout.update_dialog, null);
                        builder1.setView(v);
                        //builder1.setView(getLayoutInflater().inflate(R.layout.dialog, null));
                        //get layout
                        layoutemail=v.findViewById(R.id.email);
                        layoutpass=v.findViewById(R.id.password);
                        layoutuser=v.findViewById(R.id.username);//sex birth
                        layoutsex=v.findViewById(R.id.sex);
                        layoutbirth=v.findViewById(R.id.birth);
                        //get values to show in msg box
                        layoutemail.setText(useremail);
                        layoutpass.setText(userpass);
                        layoutsex.setText(usersex);
                        layoutbirth.setText(userbirth);
                        layoutuser.setText(username);
                        //layoutsex.setText(usersex);
                        builder1.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean isUpdate = myDb.updateData(userid,layoutuser.getText().toString(), layoutemail.getText().toString(), layoutpass.getText().toString(),layoutbirth.getText().toString(),layoutsex.getText().toString());
                                if(isUpdate == true)
                                    Toast.makeText(com.example.oumaima.lastproject.UserProfile.this,"Data Update",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(com.example.oumaima.lastproject.UserProfile.this,"Data not Updated",Toast.LENGTH_LONG).show();
                            }

                        });
                        builder1.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        builder1.show();
                    }
                });
    }






    public void viewuser(){
        Bundle extras = getIntent().getExtras();
        btnviewuser = (FloatingActionButton) findViewById(R.id.button2);
        final com.example.oumaima.lastproject.DatabaseHelper myDb = new com.example.oumaima.lastproject.DatabaseHelper(this);
        if(extras != null){
            useremail = extras.getString("EMAIL");
            //username = extras.getString("NAME");
            //helloUser.setText("Welcome " + useremail);

            // final String finalUsername = username;
            btnviewuser.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(com.example.oumaima.lastproject.UserProfile.this,"FloatingActionButton"+userid,Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    /**/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // cela permit d'ajouter un menu défini en XML
        getMenuInflater().inflate(R.menu.menu3points, menu);
        return true;
    }
}
