package com.example.pleum.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by PLEUM on 10/10/2560.
 */

public class Register extends Activity{

    private EditText editUser,editPass,editEmail;

    private static final String URL = "http://192.168.43.112/up2you/service/register.php";

    private String user,pass,mail;



   // public static final String ROOT_URL="https://up2you.000webhostapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        editUser = (EditText) findViewById(R.id.editUsername);
        editPass = (EditText) findViewById(R.id.editPass);
        editEmail = (EditText) findViewById(R.id.imput_email);
        Button register_btn =(Button) findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = editUser.getText().toString();
                pass =  editPass.getText().toString();
                mail = editEmail.getText().toString();

                if(!user.isEmpty() && !pass.isEmpty()&& !pass.isEmpty()){
                    RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("onResponse",response);
                            editUser.setText("");
                            editPass.setText("");
                            editEmail.setText("");

                            if(response.equalsIgnoreCase("ลงทะเบียนสำเร็จ")){
                                Intent intent = new Intent(Register.this,Login.class);
                                startActivity(intent);
                                Toast.makeText(Register.this,response,Toast.LENGTH_SHORT).show();

                            }else {
                                editUser.setText("");
                                editPass.setText("");
                                editEmail.setText("");

                                Toast.makeText(Register.this,response,Toast.LENGTH_SHORT).show();

                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("onError",error.toString());
                            Toast.makeText(Register.this,"เกิดข้อผิดพลาดโปรดลองอีกครั้ง",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("username",user);
                            params.put("password",pass);
                            params.put("email",mail);

                            return params;
                        }
                    };
                    requestQueue.add(request);
                }



            }
        });


        }


}


















