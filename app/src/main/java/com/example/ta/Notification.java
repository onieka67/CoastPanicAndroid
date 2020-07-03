package com.example.ta;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {
    //private static final  String url = "http://komputer-its.com/oni/select_data_akhir.php";
    private static final  String url = "http://komputer-its.com/oni/read_tb_motkapal.php";
    List<DataNotif> dataList;
    RecyclerView recyclerView;
    AdapterNotif adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        recyclerView = findViewById(R.id.idrecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();

        adapter = new AdapterNotif(Notification.this, dataList);
        recyclerView.setAdapter(adapter);

        getdatabase();
    }

    private void getdatabase(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                dataList.add(new DataNotif(
                                        object.getString("tipe_kapal"),
                                        object.getString("latitude"),
                                        object.getString("longitude"),
                                        //object.getString("status"),
                                        object.getString("nama_status"),
                                        object.getString("time")
                                ));
                            }
                            adapter = new AdapterNotif(Notification.this, dataList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        Volley.newRequestQueue(this).add(stringRequest);
    }

}
