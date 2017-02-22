package bldg5.jj.itp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import bldg5.jj.itp.common.BaseNavDrawer;
import bldg5.jj.itp.common.StylizedFrameLayout;
import bldg5.jj.itp.common.Utils;


public class BrowseItemsActivity extends BaseNavDrawer {
    private String strJson = "";
    private static String TAG = "BrowseItemsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_browse_items);
        super.onCreate(savedInstanceState);

        final RelativeLayout overlay = (RelativeLayout) findViewById(R.id.overlay);

        if (isFirstTime()) {
            overlay.setVisibility(View.INVISIBLE);
        }

        try {
            strJson = Utils.getJSONFromRaw(this.getApplicationContext(), R.raw.categories);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json = new JSONObject(strJson);
            JSONArray jArray = json.getJSONArray("categories");
            String strPrevious = "";

            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    JSONObject jsonObject = jArray.getJSONObject(i);
                    String strCategory = jsonObject.getString("category");

                    if (i % 2 == 1 && i != 0) {
                        // even
                        createRow(strPrevious, strCategory);
                    } else {
                        // odd
                        strPrevious = strCategory;

                        if (i == jArray.length() - 1) {
                            // this is the last element, and w no pair
                            createRow(strPrevious, "");
                        }
                    }
                } catch (JSONException e) {
                    // Oops
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // http://www.christianpeeters.com/android-tutorials/android-tutorial-overlay-with-user-instructions/
    private boolean isFirstTime()
    {
        final RelativeLayout overlay = (RelativeLayout) findViewById(R.id.overlay);
        final Button btnOk = (Button) findViewById(R.id.btnOk);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("CoachBrowseShown", false);

        if (!ranBefore) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("CoachBrowseShown", true);
            editor.commit();

            overlay.setVisibility(View.VISIBLE);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    overlay.setVisibility(View.INVISIBLE);
                }
            });
        }
        return ranBefore;
    }

    private void createRow(final String strColumn1, final String strColumn2) {
        final RelativeLayout overlay = (RelativeLayout) findViewById(R.id.overlay);

        int res1ID = getResources().getIdentifier(strColumn1.toLowerCase() , "drawable", getPackageName());
        int res2ID = getResources().getIdentifier(strColumn2.toLowerCase() , "drawable", getPackageName());

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout1);
        TableRow tr = new TableRow(this);

        TableRow.LayoutParams trLayoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        trLayoutParams.gravity = Gravity.CENTER;

        tr.setPadding(5, 5, 5, 5);
        tr.setLayoutParams(trLayoutParams);

        // left cell
        TextView tvLeft = new TextView(this);

        tvLeft.setLayoutParams(trLayoutParams);
        tvLeft.setText(strColumn1);
        setTextAppearance(tvLeft);
        tvLeft.setGravity(Gravity.CENTER);
        tvLeft.setCompoundDrawablesWithIntrinsicBounds(res1ID, 0, 0, 0);

        // add this to a framelayout
        StylizedFrameLayout frameLayoutLeft = new StylizedFrameLayout(this);

        // we pass the overlay because if it's showing, we cancel the event.
        frameLayoutLeft.setOnClickListener(addFrameLayoutListener(overlay, strColumn1));
        frameLayoutLeft.setOnLongClickListener(addLongClickListener(overlay, strColumn1));

        if (strColumn2.length() > 0) {
            // right cell
            TextView tvRight = new TextView(this);

            tvRight.setLayoutParams(trLayoutParams);
            tvRight.setText(strColumn2);
            setTextAppearance(tvRight);
            tvRight.setGravity(Gravity.CENTER);
            tvRight.setCompoundDrawablesWithIntrinsicBounds(res2ID, 0, 0, 0);

            StylizedFrameLayout frameLayoutRight = new StylizedFrameLayout(this);

            // we pass the overlay because if it's showing, we cancel the event.
            frameLayoutRight.setOnClickListener(addFrameLayoutListener(overlay, strColumn2));
            frameLayoutRight.setOnLongClickListener(addLongClickListener(overlay, strColumn2));

            frameLayoutRight.addView(tvRight);
            tr.addView(frameLayoutRight);
        }

        frameLayoutLeft.addView(tvLeft);
        tr.addView(frameLayoutLeft);

        tableLayout.addView(tr, trLayoutParams);
    }

    private View.OnClickListener addFrameLayoutListener(final RelativeLayout overlay, final String strColumn) {
         return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean bOverlayOn = overlay.getVisibility() == View.VISIBLE;

                // cancel the touch if the overlay is on
                if (!bOverlayOn) {
                    Bundle bundlePassVals = new Bundle();
                    bundlePassVals.putString("category", strColumn);

                    Intent intentBrowse = new Intent(BrowseItemsActivity.this, ItemsInCategory.class);
                    intentBrowse.putExtras(bundlePassVals);

                    BrowseItemsActivity.this.startActivity(intentBrowse);
                }
            }
        };
    }

    private View.OnLongClickListener addLongClickListener(final RelativeLayout overlay, final String strColumn) {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                boolean bOverlayOn = overlay.getVisibility() == View.VISIBLE;

                // cancel the touch if the overlay is on
                if (!bOverlayOn) {
                    Toast.makeText(getApplicationContext(), "TODO: REMOVE '" + strColumn + "' FROM DISPLAY TILES.", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        };
    }

    private void setTextAppearance(TextView tv) {
        if (Build.VERSION.SDK_INT < 23) {
            tv.setTextAppearance(this.getApplicationContext(), android.R.style.TextAppearance_Large);
        } else {
            tv.setTextAppearance(android.R.style.TextAppearance_Large);
        }
    }
}




