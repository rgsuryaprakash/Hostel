package com.example.surya.integration1r2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class searchData extends AppCompatActivity {

    Button find;
    TextView result;
    CardView cardView;
    Spinner spinner_hostel;
    EditText editText;
    static  String url;
    static  String text;
    static  String searchData;
    //SearchView searchView;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_data);

        find = findViewById(R.id.searchbtn);
        //searchView = findViewById(R.id.searchbox);
        editText = findViewById(R.id.searchbox);
        result = findViewById(R.id.fetched_data);
        cardView = findViewById(R.id.card_loop);
        spinner_hostel = findViewById(R.id.spinner);
        requestQueue = Volley.newRequestQueue(this);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,R.array.Hostels,android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_hostel.setAdapter(arrayAdapter);

        spinner_hostel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getSelectedItem().toString().equals("Amaravathi")){
                    url = "https://api.myjson.com/bins/1fx7n8";
                    text = "Amaravathi";
                }
                else if(parent.getSelectedItem().equals("Kaveri"))
                {
                    url = "https://api.myjson.com/bins/yu2l0";
                    text = "Kaveri";
                }
                else if(parent.getSelectedItem().toString().equals("Vaigai")){
                    url = "https://api.myjson.com/bins/9rq0k";
                    text = "Vaigai";
                }
                else if (parent.getSelectedItem().toString().equals("Bhavani"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals("Dheeran"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals("Bharathi"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals("Kamban"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals("Valluvar"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals("Elango"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals("Ponnar"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else if (parent.getSelectedItem().toString().equals("Sankar"))
                {
                    Toast.makeText(searchData.this, "Under Construction", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    text="";
                    Toast.makeText(searchData.this, "Select Which Hostel You Want to Search", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText("");
                if(text.isEmpty())
                {
                    Toast.makeText(searchData.this, "Select Which Hostel You Want to Search", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    jsonParse(text,url);
                }
            }
        });
    }

    void jsonParse(String text,String url)
    {
        final String cpy_text = text;
        searchData = editText.getText().toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int flag = 0;
                    JSONArray jsonArray = response.getJSONArray(cpy_text);
                    for (int i = 0; i< jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        if (searchData.equalsIgnoreCase(jsonObject.getString("Room_No"))||searchData.equalsIgnoreCase(jsonObject.getString("Name"))||searchData.equalsIgnoreCase(jsonObject.getString("Roll_No"))||searchData.equalsIgnoreCase(jsonObject.getString("Dept")))
                        {
                            flag = 1;
                            String roomno = jsonObject.getString("Room_No");
                            String rollno = jsonObject.getString("Roll_No");
                            String name = jsonObject.getString("Name");
                            String dept = jsonObject.getString("Dept");

                            result.append("ROOM NO : " + String.valueOf(roomno) + "\nROLL NO : " + rollno + "\nNAME : " + name + "\nDEPARTMENT : "+dept+"\n\n");

                        }

                    }
                    if(flag == 0)
                    {
                        Toast.makeText(searchData.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                        flag = 0;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}
