package bldg5.jj.itp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import bldg5.jj.itp.common.BaseNavDrawer;

public class MainActivity extends BaseNavDrawer {
       // implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        Button btnBrowse = (Button) findViewById(R.id.btnBrowse);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnAbout = (Button) findViewById(R.id.btnAbout);

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBrowse = new Intent(MainActivity.this, BrowseItemsActivity.class);
                MainActivity.this.startActivity(intentBrowse);
            }
        });
    }
}
