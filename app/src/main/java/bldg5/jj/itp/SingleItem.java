package bldg5.jj.itp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import bldg5.jj.itp.common.BaseNavDrawer;
import bldg5.jj.itp.databinding.ActivitySingleItemBinding;
import bldg5.jj.itp.models.Item;

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
        Item auto_item = (Item) intent.getExtras().getSerializable("item");

        // databind the autoItem
        binding.included.setAutoItem(auto_item);

        // finally show the image
        ImageView imgView = (ImageView) findViewById(R.id.single_item_thumb);
        Picasso.with(this).load(auto_item.getPhoto()).into(imgView);

        // bind the button
        Button btnWant = binding.included.btnWant;
        btnWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "You must be logged in to do that.", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
}
