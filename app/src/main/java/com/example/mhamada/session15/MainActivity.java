package com.example.mhamada.session15;

import android.content.DialogInterface;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
      Button btn;
      EditText Name,Password;
      String url="http://192.168.1.4/task1.php";
      AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        Name=findViewById(R.id.txt1);
        Password=findViewById(R.id.txt2);
        builder=new AlertDialog.Builder(MainActivity.this);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=Name.getText().toString();
                final String pasword=Password.getText().toString();
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                     builder.setTitle("server response");
                     builder.setMessage("response:"+response);
                     builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                            Name.setText("");
                            Password.setText("");
                         }
                     });
                     AlertDialog alertDialog=builder.create();
                     alertDialog.show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"some thing error",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params= new HashMap<String, String>();
                        params.put("name",name);
                        params.put("password",pasword);
                        return params;
                    }
                };
                Mysingleton.getMysingleton(MainActivity.this).addtorequestQueue(stringRequest);
            }
        });
    }
}
