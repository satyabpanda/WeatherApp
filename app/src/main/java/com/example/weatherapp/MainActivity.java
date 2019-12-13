package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    JSONArray jArr;
    JSONObject jObj , jObj1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //access
        editText = findViewById(R.id.editText);
        tv = findViewById(R.id.textView2);
    }

    public void buttonPressed(View view)
    {
        String cityName = editText.getText().toString();
        String url = "https://openweathermap.org/data/2.5/weather?q="+cityName+"&appid=b6907d289e10d714a6e88b30761fae22";

        RequestQueue resQ;
        resQ = Volley.newRequestQueue(this);

        JsonObjectRequest objArr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    jArr = response.getJSONArray("weather");
                    jObj1 = jArr.getJSONObject(0);

                    jObj = response.getJSONObject("main");
                    parseJsonArray();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error","something went wrong");
            }
        });
        resQ.add(objArr);

    }

    public void parseJsonArray()
    {
        try {
            String mainweather = jObj1.getString("main");
            String desc = jObj1.getString("description");
            String main = jObj.getString("temp");
            tv.setText(mainweather+"\n"+desc+"\n"+main);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
