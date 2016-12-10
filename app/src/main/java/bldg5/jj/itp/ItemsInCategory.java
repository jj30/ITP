package bldg5.jj.itp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import bldg5.jj.itp.common.BaseDrawerActivity;

public class ItemsInCategory extends BaseDrawerActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_in_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent getExtras = this.getIntent();
        // String strCategory = getExtras.getStringExtra("category").toLowerCase();
        String strCategory = "auto";

        TextView txtView = (TextView) findViewById(R.id.textView2);
        txtView.setText(strCategory);
    }
}