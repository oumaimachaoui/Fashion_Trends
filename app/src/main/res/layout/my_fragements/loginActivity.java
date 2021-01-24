package com.example.oumaima.my_fragements;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    EditText editName,editEmail,editpass ,editTextId;
    Button btnlogin;
    Button btnviewuser;
    TextView tv_no_account;
    String username=null;
    String sex=null;
    String birthdate=null;
    CheckBox checkBox;
    String userid=null;
    String useremail2=null;
    String Name;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;


    public void emptyInputEditText(){
        editEmail.setText(null);
        editpass.setText(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bundle extras = getIntent().getExtras();
        editEmail = (EditText)findViewById(R.id.et_reg_email);
        editpass = (EditText)findViewById(R.id.et_reg_password);
        btnlogin = (Button)findViewById(R.id.btn_register);


        checkBox=(CheckBox)findViewById(R.id.checkBox);
/*
        sp=getSharedPreferences("testPrefs",MODE_PRIVATE);
        editor=sp.edit();
        */
////getting the data persisted (select data)
        sp=getSharedPreferences("testPrefs3",MODE_PRIVATE);
        editor=sp.edit();
        if((sp.contains("name3"))&&(sp.contains("pass3"))){
            if(checkBox.isChecked()== true){
                editEmail.setText(sp.getString("name3","null"));
                editpass.setText(sp.getString("pass3","null"));
            }
            else if(checkBox.isChecked()== false){
                editEmail.setText("");
                editpass.setText("");
            }
        }


        if(extras != null){
            username = extras.getString("Name");
            sex=extras.getString("sex");
            birthdate=extras.getString("birthdate");
            useremail2=extras.getString("emaail");

        }
        Bundle extras2 = getIntent().getExtras();
        if(extras2 != null){
            Name = extras.getString("Name");
            // editText.setText(username);
           // Toast.makeText(getApplicationContext(),"Name        "+Name,Toast.LENGTH_LONG).show();
        }

       // Toast.makeText(getApplicationContext(),"emaail        "+useremail2,Toast.LENGTH_LONG).show();
        myDb = new DatabaseHelper(this);

        userexist();



    }
    public void buttonAction(View view) {
        //((view.getId()==R.id.btn_register))||
        if(view.getId()==R.id.tv_no_account){
            if(checkBox.isChecked()==true){
                Toast.makeText(getApplicationContext()," is checked",Toast.LENGTH_LONG).show();
                /*     Toast.makeText(getApplicationContext(),editName.getText().toString(),Toast.LENGTH_LONG).show();*/
                editor.putString("name3",editEmail.getText().toString());
                editor.putString("pass3",editpass.getText().toString());
                editor.commit();
                Toast.makeText(getApplicationContext(),"Preferences Saved",Toast.LENGTH_LONG).show();
                Intent intentRegister = new Intent(com.example.oumaima.my_fragements.loginActivity.this, com.example.oumaima.my_fragements.Registeractibity.class);
                startActivity(intentRegister);
            }else if(checkBox.isChecked()==false){
              /*  editor.clear();
                editor.commit();*/
                Toast.makeText(getApplicationContext(),"Preferences Removed",Toast.LENGTH_LONG).show();
                Intent intentRegister = new Intent(com.example.oumaima.my_fragements.loginActivity.this, com.example.oumaima.my_fragements.Registeractibity.class);
                startActivity(intentRegister);
            } }
    }
    public void userexist(){
        //editName = (EditText)findViewById(R.id.et_name);
        editEmail = (EditText)findViewById(R.id.et_reg_email);
        editpass = (EditText)findViewById(R.id.et_reg_password);
        btnlogin = (Button)findViewById(R.id.btn_register);




        btnlogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getApplicationContext(),"nameproduct        "+Name,Toast.LENGTH_LONG).show();
                        //Toast.makeText(loginActivity.this,"hahahahaha",Toast.LENGTH_LONG).show();

                       // if (myDb.checkUser(editEmail.getText().toString().trim()
                      //  )) {
                            if(checkBox.isChecked()==true){
                                //Getting values from edit texts
                                final String email = editEmail.getText().toString().trim();
                                final String password = editpass.getText().toString().trim();

                                //Creating a string request
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.LOGIN_URL,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                //If we are getting success from server
                                                if(response.equalsIgnoreCase(Config.LOGIN_SUCCESS)){
                                                    //Creating a shared preference
                                                    ///////// SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                                                    //Creating editor to store values to shared preferences
                                                    ///////  SharedPreferences.Editor editor = sharedPreferences.edit();

                                                    //Adding values to editor
                                                    /////////editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                                                    /////////editor.putString(Config.EMAIL_SHARED_PREF, email);

                                                    //Saving values to editor
                                                    ///////// editor.commit();

                                                    //Starting profile activity
                                                    Intent intent = new Intent(com.example.oumaima.my_fragements.loginActivity.this, com.example.oumaima.my_fragements.UserProfile.class);
                                                    intent.putExtra("EMAIL",email);
                                                    intent.putExtra("PASSWORD",password);
                                                    ///// Toast.makeText(LoginActivity.this, "valid username and password", Toast.LENGTH_LONG).show();
                                                    startActivity(intent);
                                                }else{
                                                    //If the server response is not success
                                                    //Displaying an error message on toast
                                                    Toast.makeText(com.example.oumaima.my_fragements.loginActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        },
                                        new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                //You can handle error here if you want
                                            }
                                        }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String,String> params = new HashMap<>();
                                        //Adding parameters to request
                                        params.put(Config.KEY_EMAIL, email);
                                        params.put(Config.KEY_PASSWORD, password);

                                        //returning parameter
                                        return params;
                                    }
                                };

                                //Adding the string request to the queue
                                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                                requestQueue.add(stringRequest);
                                ////the sqlite login
                                //Toast.makeText(getApplicationContext()," is checked",Toast.LENGTH_LONG).show();
                                editor.putString("name3",editEmail.getText().toString());
                                editor.putString("pass3",editpass.getText().toString());
                                editor.commit();
                                ///looking for this user id
                             /*   myDb = new DatabaseHelper(getApplicationContext());
                                Cursor res = myDb.getDatausermail(editEmail.getText().toString());
                                while (res.moveToNext()) {
                                    userid=res.getString(0);

                                }
                                Toast.makeText(getApplicationContext(),"ID       : "+userid,Toast.LENGTH_LONG).show();

                                //Go to intent
                                Intent accountsIntent = new Intent(loginActivity.this, UserProfile.class);
                                accountsIntent.putExtra("EMAIL", editEmail.getText().toString().trim());
                                accountsIntent.putExtra("PASSWORD", editpass.getText().toString().trim());
                                accountsIntent.putExtra("NAME", username);
                                accountsIntent.putExtra("birthdate",birthdate);
                                accountsIntent.putExtra("sex", sex);
                                accountsIntent.putExtra("userid", userid);
                                accountsIntent.putExtra("Name",Name);
                                emptyInputEditText();
                                startActivity(accountsIntent);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                            */
                            }
                        //}
                        else if(checkBox.isChecked()==false){
                            editor.clear();
                            editor.commit();
                            Toast.makeText(getApplicationContext(),"Preferences Removed",Toast.LENGTH_LONG).show();  }
                            else {
                                // Snack Bar to show success message that record is wrong
                                Toast.makeText(com.example.oumaima.my_fragements.loginActivity.this, getString(R.string.error_valid_email_password),Toast.LENGTH_LONG).show();
                            }

                    }

                }
        );
    }

}
