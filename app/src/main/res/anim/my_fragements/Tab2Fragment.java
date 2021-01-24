package com.example.oumaima.my_fragements;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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

/**
 * Created by User on 2/28/2019
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";

    private Button btnTEST;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.56.1/AndroidProject/insertAchat.php";
    private Spinner spin;
    private EditText adresse;
    String[] items={"Agadir","Casa Blanca","Fes","Rabat","Tanger","Dakhla","Oujda"};
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    String livraison="";
    //envoyer indice
    SendMessage SM;
    interface SendMessage {
        void sendData(String message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            SM = (SendMessage) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);
        requestQueue = Volley.newRequestQueue(getContext());

        btnTEST = (Button) view.findViewById(R.id.submit);
        radioButton1=(RadioButton) view.findViewById(R.id.rb1);
        radioButton2=(RadioButton) view.findViewById(R.id.rb2);
        radioButton1 .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                         if(isChecked)
                                                             radioButton2.setChecked(false);
                                                         livraison="Express";
                                                         //Toast.makeText(getContext(), livraison,Toast.LENGTH_LONG).show();
                                                     }
                                                 }
        );
        radioButton2 .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                     @Override
                                                     public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                                                         if(isChecked)
                                                             radioButton1.setChecked(false);
                                                         livraison="standard";
                                                       //  Toast.makeText(getContext(), livraison,Toast.LENGTH_LONG).show();
                                                     }
                                                 }
        );

        //spin=(Spinner)view.findViewById(R.id.spinner1);
        btnTEST.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getActivity(), "TESTING BUTTON CLICK 2",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                //builder.setView(getActivity().getLayoutInflater().inflate(R.layout.layout, null));
                ///////////

                LayoutInflater inflater =getActivity().getLayoutInflater();
                view = inflater.inflate(R.layout.boxadress, null);
                builder.setView(view);
                adresse= (EditText) view.findViewById(R.id.editText1);
                spin= (Spinner) view.findViewById(R.id.spinner1);

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item,items);

                adapter.setDropDownViewResource(
                        android.R.layout.simple_spinner_dropdown_item);

                spin.setAdapter(adapter);

                spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



                builder.setPositiveButton("Envoyer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity(), adresse.getText().toString()+" "+spin.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
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
                                parameters.put("livraison",livraison.toString());
                                parameters.put("adress",adresse.getText().toString());
                                parameters.put("ville",spin.getSelectedItem().toString());
                                return parameters;
                            }
                        };
                        requestQueue.add(request);
                        //get indice to send
                            SM.sendData(adresse.getText().toString().trim());

                    }
                });
                builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
                builder.show();

            }


        });
        //Bundle bundle = new Bundle();
        //bundle.putInt("adress", Integer.parseInt(adresse.getText().toString()));
       // Tab2Fragment.getArguments(bundle);

        return view;
    }
}
