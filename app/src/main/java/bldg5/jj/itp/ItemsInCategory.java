package bldg5.jj.itp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import bldg5.jj.itp.common.BaseNavDrawer;

public class ItemsInCategory extends BaseNavDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_items_in_category);
        super.onCreate(savedInstanceState);

        Intent getExtras = this.getIntent();
        // String strCategory = getExtras.getStringExtra("category").toLowerCase();
        String strCategory = "auto";

        TextView txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText(strCategory);


    }
}