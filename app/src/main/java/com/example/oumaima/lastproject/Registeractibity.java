package com.example.oumaima.lastproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Registeractibity extends AppCompatActivity {
    private Boolean firstTime = null;
    com.example.oumaima.lastproject.DatabaseHelper myDb;
    EditText editName,editEmail,editpass ,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    Button btnviewuser;
    Button btnviewUpdate;
    Button nextactivity;
    TextView tv_no_account;
    TextView mDisplayDate;
    String sex;
    AwesomeValidation awesomeValidation;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.48.2/AndroidProject/insertuser.php";
    private static final String TAG = "Registeractibity";
    public void emptyInputEditText(){
        editEmail.setText(null);
        editpass.setText(null);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractibity);
        SharedPreferences prefs = getSharedPreferences("prefs2", MODE_PRIVATE);
        /*
        boolean firstStart = prefs.getBoolean("firstStart2", true);
        if (firstStart) {
            //AddData(); ;
           // AddData();
        }
        else{
            Intent aa = new Intent(Registeractibity.this,loginActivity.class);
            startActivity(aa);
        }*/
        myDb = new com.example.oumaima.lastproject.DatabaseHelper(this);
        mDisplayDate = (TextView) findViewById(R.id.et_reg_birth);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        com.example.oumaima.lastproject.Registeractibity.this,
                        android.R.style.Theme,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };
        //tv_no_account.setText("Already a member? Login");
        editName = (EditText)findViewById(R.id.et_name);
        editEmail = (EditText)findViewById(R.id.et_reg_email);
        editpass = (EditText)findViewById(R.id.et_reg_password);
        btnAddData = (Button)findViewById(R.id.btn_register);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        //String regexPassword="(?=.*[a-z])-(?=.*[A-Z])";
        String regexPassword2 = "^((19|20)\\\\d\\\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$";
        awesomeValidation.addValidation(this,R.id.et_reg_email,android.util.Patterns.EMAIL_ADDRESS,R.string.emailerr);
        //awesomeValidation.addValidation(this,R.id.et_reg_password,regexPassword,R.string.passerr);
        awesomeValidation.addValidation(this,R.id.et_reg_birth,regexPassword2,R.string.birtherr);
        tv_no_account= (TextView)findViewById(R.id.tv_no_account);
        tv_no_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent accountsIntentdirect = new Intent(com.example.oumaima.lastproject.Registeractibity.this, com.example.oumaima.lastproject.loginActivity.class);
                startActivity(accountsIntentdirect);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        AddData();


    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio1:
                if (checked){
                    sex="Homme";
                    // Toast.makeText(Registeractibity.this, "Data"+sex, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.radio2:
                if (checked)
                    sex="Femme";
                // Toast.makeText(Registeractibity.this, "Data"+sex, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void AddData(){
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(com.example.oumaima.lastproject.Registeractibity.this, "Data Received Succesfully", Toast.LENGTH_SHORT).show();
                        if (!myDb.checkUser(editEmail.getText().toString().trim()
                        )) {
                            boolean isInserted = myDb.insertData(editName.getText().toString(),
                                    editEmail.getText().toString(),
                                    editpass.getText().toString(),mDisplayDate.getText().toString(),sex);

                            if (awesomeValidation.validate()){

                                Toast.makeText(com.example.oumaima.lastproject.Registeractibity.this, "Data Received Succesfully", Toast.LENGTH_SHORT).show();


                                if(isInserted == true){
                                    StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            System.out.println("sa passe");
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            System.out.println("sa passe nicht ");
                                        }
                                    }) {

                                        @Override
                                        protected Map<String, String> getParams() throws AuthFailureError {
                                            Map<String,String> parameters  = new HashMap<String, String>();
                                            parameters.put("username",editName.getText().toString().trim());
                                            parameters.put("useremail",editEmail.getText().toString());
                                            parameters.put("userbirth",mDisplayDate.getText().toString().trim());
                                            parameters.put("userpass",editpass.getText().toString());
                                            parameters.put("usersex", String.valueOf(sex));
                                            return parameters;
                                        }
                                    };
                                    requestQueue.add(request);


                                    Toast.makeText(com.example.oumaima.lastproject.Registeractibity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                                    Intent intentRegister = new Intent(com.example.oumaima.lastproject.Registeractibity.this, loginActivity.class);
                                    intentRegister.putExtra("Name", editName.getText().toString().trim());
                                    intentRegister.putExtra("emaail", editEmail.getText().toString().trim());
                                    intentRegister.putExtra("sex", sex);
                                    intentRegister.putExtra("birthdate", mDisplayDate.getText().toString().trim());
                                    /********************/
                                    SharedPreferences prefs = getSharedPreferences("prefs2", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putBoolean("firstStart2", false);
                                    editor.apply();
                                    /********/
                                    startActivity(intentRegister);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);


                                }

                                else
                                    Toast.makeText(com.example.oumaima.lastproject.Registeractibity.this,"Data not Inserted",Toast.LENGTH_LONG).show();


                            }


                            else

                            {
                                Toast.makeText(com.example.oumaima.lastproject.Registeractibity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            // Snack Bar to show success message that record is wrong
                            Toast.makeText(com.example.oumaima.lastproject.Registeractibity.this, "Email early exist",Toast.LENGTH_LONG).show();
                        }




                    } }
        );
    }


}

