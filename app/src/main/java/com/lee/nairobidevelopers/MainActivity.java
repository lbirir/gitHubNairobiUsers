package com.lee.nairobidevelopers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lee.nairobidevelopers.adapters.DevelopersAdapter;
import com.lee.nairobidevelopers.config.AppConfig;
import com.lee.nairobidevelopers.model.Developer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String url = AppConfig.server_url+"?q=location:nairobi";
    ListView lvDevelopers;
    List<Developer> developerList = new ArrayList<Developer>();
    ProgressDialog pDialog;
    DevelopersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDevelopers = (ListView)findViewById(R.id.listViewDevelopers);
        adapter = new DevelopersAdapter(MainActivity.this,developerList);
        lvDevelopers.setAdapter(adapter);

        lvDevelopers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Developer developers = developerList.get(position);
                String userprofile = developers.getProfileURL();
                Intent myIntent = new Intent(MainActivity.this,DeveloperProfile.class);
                myIntent.putExtra("profileUrl",userprofile);
                startActivity(myIntent);
            }
        });

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("API Response",""+response);
                pDialog.dismiss();
                try {
                    JSONObject items = response.getJSONObject("items");
                    JSONArray itemsArray = items.getJSONArray("items");
                    for (int i=0; i<itemsArray.length(); i++){
                        JSONObject objUser = itemsArray.getJSONObject(i);
                        String username = objUser.getString("login");
                        String userids = objUser.getString("id");
                        String userprofile = objUser.getString("url");

                        Developer developer = new Developer();
                        developer.setUsername(username);
                        developer.setId(userids);
                        developer.setProfileURL(userprofile);

                        developerList.add(developer);
                        requestQueue.stop();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
