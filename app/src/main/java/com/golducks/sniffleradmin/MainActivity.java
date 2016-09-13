package com.golducks.sniffleradmin;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    ArrayList<model> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

    }


    public void search(View v) {

        findViewById(R.id.progressBar).setVisibility(View.VISIBLE);

        Http http = new Http(this, new DownloadListener() {
            @Override
            public void onDownloaded(String data) {
                parseJSON(data);
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getApplicationContext(), arrayList);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onError(String data) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), data + "", Toast.LENGTH_LONG).show();
            }
        });

        http.requestData(((EditText) findViewById(R.id.search)).getText().toString());

    }

    void parseJSON(String data) {
        arrayList = new ArrayList();
        try {
            JSONArray jsonarray = new JSONArray(data);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jobj = jsonarray.getJSONObject(i);

                String name = jobj.getString("name");
                String number = jobj.getString("number");
                String message = jobj.getString("message");
                String time = jobj.getString("time");

                model _model = new model();
                _model.setName(name);
                _model.setNumber(number);
                _model.setMessage(message);
                _model.setTime(time);

                arrayList.add(_model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
