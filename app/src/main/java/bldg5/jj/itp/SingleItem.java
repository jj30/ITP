package bldg5.jj.itp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bldg5.jj.itp.common.BaseNavDrawer;
import bldg5.jj.itp.common.Utils;
import bldg5.jj.itp.databinding.ActivitySingleItemBinding;
import bldg5.jj.itp.models.AutoItem;

public class SingleItem extends BaseNavDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_single_item);

        // set the binding for the toolbar
        ActivitySingleItemBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_single_item);
        //setSupportActionBar(binding.toolbar);

        // now run the base's onCreate
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Integer nId = intent.getExtras().getInt("ID");

        String strJson = "";

        try {
            strJson = Utils.getJSONFromRaw(this.getApplicationContext(), R.raw.items);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(strJson);
            JSONArray jArray = json.getJSONArray("items");
            Gson gson = new Gson();

            List<AutoItem> listAutoItems = new ArrayList<AutoItem>();
            listAutoItems = Arrays.asList(gson.fromJson(String.valueOf(jArray), AutoItem[].class));

            Integer nFindItemById = listAutoItems.indexOf(new AutoItem(nId));
            AutoItem autoItem = listAutoItems.get(nFindItemById);

            // databind the autoItem
            binding.included.setAutoItem(autoItem);

            // finally show the image
            ImageView imgView = (ImageView) findViewById(R.id.single_item_thumb);
            Picasso.with(this).load(autoItem.getPhoto()).into(imgView);

            // bind the button
            Button btnWant = binding.included.btnWant;
            btnWant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(getApplicationContext(), "You must be logged in to do that.", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}
