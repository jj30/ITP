package bldg5.jj.itp.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import bldg5.jj.itp.AboutBldg5Activity;
import bldg5.jj.itp.BrowseItemsActivity;
import bldg5.jj.itp.LoginActivity;
import bldg5.jj.itp.MainActivity;
import bldg5.jj.itp.R;

public abstract class BaseNavDrawer
        extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // https://github.com/mikepenz/MaterialDrawer
        PrimaryDrawerItem primaryDrawerItem = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home);
        SecondaryDrawerItem browseDrawerItem = new SecondaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_2);
        SecondaryDrawerItem logInDrawerItem = new SecondaryDrawerItem().withIdentifier(3).withName(R.string.drawer_item_3);
        SecondaryDrawerItem aboutDrawerItem = new SecondaryDrawerItem().withIdentifier(4).withName(R.string.drawer_item_4);

        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
            .withActivity(this)
            .withToolbar(toolbar)
            .addDrawerItems(
                    primaryDrawerItem.withIcon(FontAwesome.Icon.faw_home),
                    new DividerDrawerItem(),
                    browseDrawerItem.withIcon(FontAwesome.Icon.faw_folder_open_o),
                    logInDrawerItem.withIcon(FontAwesome.Icon.faw_sign_in),
                    aboutDrawerItem.withIcon(FontAwesome.Icon.faw_address_card_o)
            )
            .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                @Override
                public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                    // Handle navigation view item clicks here.
                    Intent intentBrowse = null;

                    if (position == 0) {
                        intentBrowse = new Intent(view.getContext(), MainActivity.class);
                        // drawer divider item is position 1
                        // } else if (position == 1) {
                        //    intentBrowse = new Intent(view.getContext(), MainActivity.class);
                    } else if (position == 2) {
                        intentBrowse = new Intent(view.getContext(), BrowseItemsActivity.class);
                    } else if (position == 3) {
                        intentBrowse = new Intent(view.getContext(), LoginActivity.class);
                    } else if (position == 4) {
                        intentBrowse = new Intent(view.getContext(), AboutBldg5Activity.class);
                    }

                    startActivity(intentBrowse);

                    return true;
                }
            })
            .build();
    }
}
