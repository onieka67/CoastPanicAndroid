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

public class KapalActivity extends AppCompatActivity {
    public static String[] getidkapal = {"20", "30"};
    List<DataKapal> dataList;
    RecyclerView recyclerView;
    AdapterKapal adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kapal_rv);

        recyclerView = findViewById(R.id.idrecyclerviewkap);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataList = new ArrayList<>();

        adapter = new AdapterKapal(KapalActivity.this, dataList);
        recyclerView.setAdapter(adapter);

        getdatabase();
    }

    private void getdatabase() {
        for (int i = 0; i < getidkapal.length; i++) {
            String url = "http://komputer-its.com/oni/select_kapal.php?id_kapal=" + getidkapal[i];
            final String urlfoto = "http://komputer-its.com/oni/foto_kapal/" + getidkapal[i]+ ".jpg";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray array = new JSONArray(response);
                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject object = array.getJSONObject(i);
                                    dataList.add(new DataKapal(
                                            object.getString("id_kapal"),
                                            object.getString("tipe_kapal"),
                                            object.getString("panjang"),
                                            object.getString("lebar"),
                                            urlfoto
                                    ));
                                }
                                adapter = new AdapterKapal(KapalActivity.this, dataList);
                                recyclerView.setAdapter(adapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }

            );
            Volley.newRequestQueue(this).add(stringRequest);
        }
    }
}
