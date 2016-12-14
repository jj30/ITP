package bldg5.jj.itp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import bldg5.jj.itp.common.BaseNavDrawer;
import bldg5.jj.itp.common.OnItemClickListener;
import bldg5.jj.itp.common.Utils;
import bldg5.jj.itp.models.AutoItem;
import android.widget.Toast;

import com.google.gson.Gson;

// http://stacktips.com/tutorials/android/android-recyclerview-example
public class ItemsInCategory extends BaseNavDrawer {
    private static final String TAG = "RecyclerViewExample";
    private List<AutoItem> feedsList;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_items_in_category);
        super.onCreate(savedInstanceState);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String strJson = "";

        try {
            strJson = Utils.getJSONFromRaw(this.getApplicationContext(), R.raw.auto_items);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(strJson);
            JSONArray jArray = json.getJSONArray("auto_items");
            Gson gson = new Gson();

            // List<AutoItem> listAutoItems = new ArrayList<AutoItem>();
            feedsList = Arrays.asList(gson.fromJson(String.valueOf(jArray), AutoItem[].class));

            adapter = new RecyclerViewAdapter(ItemsInCategory.this, feedsList);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AutoItem item) {
                    Toast.makeText(ItemsInCategory.this, item.getTitle(), Toast.LENGTH_LONG).show();

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}

/*
    private void parseResult(String result) {
        try {
            JSONObject response = new JSONObject(result);
            JSONArray posts = response.optJSONArray("posts");
            feedsList = new ArrayList<>();

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                AutoItem item = new AutoItem();
                item.setTitle(post.optString("title"));
                item.setPhoto(post.optString("thumbnail"));
                feedsList.add(item);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class DownloadTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }
                    parseResult(response.toString());
                    result = 1; // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }
            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return result; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setVisibility(View.GONE);

            if (result == 1) {
                adapter = new RecyclerViewAdapter(ItemsInCategory.this, feedsList);
                mRecyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AutoItem item) {
                        Toast.makeText(ItemsInCategory.this, item.getTitle(), Toast.LENGTH_LONG).show();

                    }
                });

            } else {
                Toast.makeText(ItemsInCategory.this, "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }*/