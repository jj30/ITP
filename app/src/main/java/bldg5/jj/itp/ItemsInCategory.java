package bldg5.jj.itp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import bldg5.jj.itp.adapters.RecyclerViewAdapter;
import bldg5.jj.itp.common.BaseNavDrawer;
import bldg5.jj.itp.common.OnItemClickListener;
import bldg5.jj.itp.common.Utils;
import bldg5.jj.itp.models.Item;

// http://stacktips.com/tutorials/android/android-recyclerview-example
public class ItemsInCategory extends BaseNavDrawer {
    private static final String TAG = "RecyclerViewExample";
    private List<Item> feedsList;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_items_in_category);
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String strCategory = intent.getStringExtra("category").toLowerCase();

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        String strJson = "";

        try {
            strJson = Utils.getJSONFromRaw(this.getApplicationContext(), R.raw.items);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(strJson);
            JSONArray jArray = json.getJSONArray(strCategory + "_items");
            Gson gson = new Gson();

            feedsList = Arrays.asList(gson.fromJson(String.valueOf(jArray), Item[].class));

            adapter = new RecyclerViewAdapter(ItemsInCategory.this, feedsList);
            mRecyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(Item item) {
                    // Toast.makeText(ItemsInCategory.this, item.getTitle(), Toast.LENGTH_LONG).show();
                    // Integer nId = item.getId();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("item", item);

                    Intent intentSingleItem = new Intent(getApplicationContext(), SingleItem.class);
                    intentSingleItem.putExtras(bundle);

                    ItemsInCategory.this.startActivity(intentSingleItem);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}