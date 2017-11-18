package com.example.pleum.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PLEUM on 19/10/2560.
 */

public class Songdisplay extends Activity {

    private String songID;
   private static final String URL = "http://192.168.43.112/up2you/service/imageChords.php";
    private JSONArray obj;

  private TextView songn,art,video;

private String song,artist,link;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songdisplay);


        songn = (TextView) findViewById(R.id.songName_text);
        art = (TextView) findViewById(R.id.artist_text);
        video = (TextView) findViewById(R.id.text_link);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            songID = bundle.getString("songID");

        }

        getdata();



    }

    //Get display song by songID

        private void getdata() {
            RequestQueue requestQueue = Volley.newRequestQueue(Songdisplay.this);
            Request request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d("onResponse",response);

                    try {

                        obj = new JSONArray(response);

                        Log.d("My App", obj.toString());

                        for(int i=0;i<obj.length();i++){


                                JSONObject json_data = obj.getJSONObject(i);

                                song = json_data.getString("songName");
                                artist = json_data.getString("artist");
                                link = json_data.getString("video");
                                Log.i("GET", "songName :"+ song + " artist: "+ artist+" video: "+ link);





                        }

                    } catch (Throwable t) {
                        Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                    }




                    Toast.makeText(Songdisplay.this,song,Toast.LENGTH_SHORT).show();

                    songn.setText(song);
                    art.setText(artist);
                    video.setText(link);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("onError",error.toString());
                    Toast.makeText(Songdisplay.this,"เกิดข้อผิดพลาดโปรดลองอีกครั้ง",Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("songID",songID);

                    return params;
                }
            };
            requestQueue.add(request);


        }









    }










