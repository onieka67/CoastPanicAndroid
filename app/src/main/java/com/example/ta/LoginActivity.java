package com.example.ta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText iduser, idpass;
    public static String user;
    private String pass, loginstatus,output, loginberhasil = "berhasil";
    private Button idbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        iduser = findViewById(R.id.iduser);
        idpass = findViewById(R.id.idpass);
        idbutton = findViewById(R.id.idbutton);

        idbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = iduser.getText().toString();
                pass = idpass.getText().toString();

                if (user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Data Belum Lengkap",Toast.LENGTH_LONG).show();
                }
                else{
                    getdatauser();
                }
            }
        });
    }

    private void getdatauser() {
        String url = "http://komputer-its.com/oni/login.php?user="+user+"&pass="+pass;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                output = object.getString("login_status");
                            }
//                                Toast.makeText(getApplicationContext(),output, Toast.LENGTH_LONG).show();
                            if(output.equals("berhasil")){
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Username/ Password Salah!!",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "gagal ambil data", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"VolleyError",Toast.LENGTH_LONG).show();

                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
