package bldg5.jj.itp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import bldg5.jj.itp.common.BaseNavDrawer;

public class MainActivity extends BaseNavDrawer {
       // implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        boolean loggedIn = this.getIntent().getBooleanExtra("logged-in", false);
        Button btnBrowse = (Button) findViewById(R.id.btnBrowse);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnAbout = (Button) findViewById(R.id.btnAbout);

        btnLogin.setVisibility(loggedIn ? View.GONE : View.VISIBLE);

        if (loggedIn) {
            // item 3 is "log in"
            result.removeItem(3);
            // at the bottom, put a drawer item to "log out"
            result.addItem(new DividerDrawerItem());
            PrimaryDrawerItem drawerLogOut = new PrimaryDrawerItem()
                    .withName("Log out")
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                            if (position == -1) { 
                                finish();
                                setResult(-1);
                            }

                            return true;
                        }});
            result.addStickyFooterItem(drawerLogOut);

        }

        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBrowse = new Intent(MainActivity.this, BrowseItemsActivity.class);
                MainActivity.this.startActivity(intentBrowse);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBrowse = new Intent(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intentBrowse);
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentBrowse = new Intent(MainActivity.this, AboutBldg5Activity.class);
                MainActivity.this.startActivity(intentBrowse);
            }
        });
    }
}
