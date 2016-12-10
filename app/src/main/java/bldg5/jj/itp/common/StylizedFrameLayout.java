package bldg5.jj.itp.common;

import android.content.Context;
import android.widget.FrameLayout;

import bldg5.jj.itp.R;

public class StylizedFrameLayout extends FrameLayout {
    public StylizedFrameLayout(Context context) {
        super(context, null, R.attr.buttonStyle);
    }
}