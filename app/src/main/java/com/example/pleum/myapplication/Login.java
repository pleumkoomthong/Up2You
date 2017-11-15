package com.example.pleum.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by PLEUM on 13/10/2560.
 */

public class Login extends Activity {
    private LoginButton btnLogin;
    private CallbackManager callbackManager;
    private String user,pass;

    private static final String URL = "http://192.168.43.112/up2you/service/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.login);



        final EditText username = (EditText) findViewById(R.id.editUsername);
        final EditText password = (EditText) findViewById(R.id.editPass);
        final Button login_btn =(Button) findViewById(R.id.login_btn);
        ImageView login_phone =(ImageView) findViewById(R.id.phone_btn);



        login_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
            });


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = username.getText().toString();
                pass =  password.getText().toString();
                //mail = editEmail.getText().toString();

                if(!user.isEmpty() && !pass.isEmpty()&& !pass.isEmpty()){
                    RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                    StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("onResponse",response);
                            username.setText("");
                            password.setText("");


                            if(response.equalsIgnoreCase("เข้าสู่ระบบสำเร็จ")){
                                Toast.makeText(Login.this,response,Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Login.this,MainActivity.class);
                                startActivity(intent);

                            }else {
                                password.setText("");
                                username.setText("");

                                Toast.makeText(Login.this,response,Toast.LENGTH_SHORT).show();

                            }



                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("onError",error.toString());
                            Toast.makeText(Login.this,"เกิดข้อผิดพลาดโปรดลองอีกครั้ง",Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("username",user);
                            params.put("password",pass);


                            return params;
                        }
                    };
                    requestQueue.add(request);
                }




            }
        });

        //facebook btn
        callbackManager = CallbackManager.Factory.create();
        btnLogin = (LoginButton) findViewById(R.id.login_fb);

        btnLogin.setReadPermissions(Arrays.asList("user_photos","email","public_profile"));

        btnLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(Login.this,"Success "+loginResult.getAccessToken().getUserId(),Toast.LENGTH_SHORT).show();

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,last_name,link,email,picture");
                GraphRequest request =  GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        try {
                            String str_email = jsonObject.getString("email");
                            Toast.makeText(Login.this,str_email,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.i("user",jsonObject.toString());
                    }
                });
                request.setParameters(parameters);
                request.executeAsync();



            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {
                Toast.makeText(Login.this,"Error "+e,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);

    }


    }

