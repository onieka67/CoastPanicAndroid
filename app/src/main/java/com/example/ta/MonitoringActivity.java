package com.example.ta;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MonitoringActivity extends AppCompatActivity {
    //private static final  String url = "http://komputer-its.com/oni/read_tb_coba.php";
    private static String url = "http://komputer-its.com/oni/select_tb_monitoring_kapal.php";
    List<DataMonitoring> dataList;
    RecyclerView recyclerView;
    AdapterMonitoring adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mot_rv);

        recyclerView = findViewById(R.id.idrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();

        adapter = new AdapterMonitoring(MonitoringActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        getdatabase();
    }

    private void getdatabase(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                dataList.add(new DataMonitoring(
                                        object.getString("tipe_kapal"),
                                        object.getString("latitude"),
                                        object.getString("longitude"),
                                        //object.getString("status"),
                                        object.getString("nama_status"),
                                        object.getString("time")
                                ));
                            }
                            adapter = new AdapterMonitoring(MonitoringActivity.this, dataList);
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

