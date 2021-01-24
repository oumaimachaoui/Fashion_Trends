package com.example.oumaima.my_fragements;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.braintreepayments.cardform.view.CardForm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 2/28/2019.
 */

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.56.1/AndroidProject/insertAchat.php";
    private Button btnTEST;
    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;
    // card number:  4000 0012 3456 7899
    //CVV:111
    //postal code: 80000
    //contry code:+212
    //mobile number:677218995
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);
        requestQueue = Volley.newRequestQueue(getContext());
        cardForm = view.findViewById(R.id.card_form);
        buy = view.findViewById(R.id.btnBuy);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS is required on this number")
                .setup(getActivity());
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setTitle("Confirm before purchase");
                alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                        "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                        "Card CVV: " + cardForm.getCvv() + "\n" +
                        "Postal code: " + cardForm.getPostalCode() + "\n" +
                        "Phone number: " + cardForm.getMobileNumber());
                alertBuilder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dialogInterface.dismiss();
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
                                parameters.put("Card_number",cardForm.getCardNumber().toString());
                                parameters.put("Card_expiry_date",cardForm.getExpirationDateEditText().getText().toString());
                                parameters.put("Card_CVV",cardForm.getCvv());
                                parameters.put("Postal_code", cardForm.getPostalCode());
                                parameters.put("Phone_number","+212"+cardForm.getMobileNumber());
                                return parameters;
                            }
                        };
                        requestQueue.add(request);

                        Toast.makeText(getActivity(), "Oumaima thank you for purchase", Toast.LENGTH_LONG).show();
                        Intent in=new Intent(getActivity(),finalActvity.class);
                        startActivity(in);

                    }
                });
                alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();

            }
        });

        return view;
    }
    protected void displayReceivedData(String message)
    {
        Context context;
       // context=getContext();
        context=getActivity();
        Toast.makeText(context, "My Data"+message,Toast.LENGTH_SHORT).show();
       // txtData.setText("Data received: "+message);
    }
}
