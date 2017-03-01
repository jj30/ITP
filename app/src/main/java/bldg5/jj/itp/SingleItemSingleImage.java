package bldg5.jj.itp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import bldg5.jj.itp.common.BaseNavDrawer;

public class SingleItemSingleImage extends BaseNavDrawer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // http://stackoverflow.com/questions/2868047/fullscreen-activity-in-android
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_single_item_single_image);

        // now run the base's onCreate
        super.onCreate(savedInstanceState);


        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("url");

        ImageView img = (ImageView) findViewById(R.id.imageView);

        //Download image using picasso library .. if it's not cached (it should be)
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(this)
                    .load(url)
                    .error(R.drawable.loading)
                    .placeholder(R.drawable.loading)
                    .into(img);
        }
    }
}
