package bldg5.jj.itp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bldg5.jj.itp.R;
import bldg5.jj.itp.common.BaseNavDrawer;
import bldg5.jj.itp.common.Utils;
import bldg5.jj.itp.models.AutoItem;

public class SingleItem extends BaseNavDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_single_item);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Integer nId = intent.getExtras().getInt("ID");


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

            List<AutoItem> listAutoItems = new ArrayList<AutoItem>();
            listAutoItems = Arrays.asList(gson.fromJson(String.valueOf(jArray), AutoItem[].class));

            Integer nFindItemById = listAutoItems.indexOf(new AutoItem(nId));
            AutoItem autoItem = listAutoItems.get(nFindItemById);

            // finally show the image
            ImageView imgView = (ImageView) findViewById(R.id.single_item_thumb);
            Picasso.with(this).load(autoItem.getPhoto()).into(imgView);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

}
