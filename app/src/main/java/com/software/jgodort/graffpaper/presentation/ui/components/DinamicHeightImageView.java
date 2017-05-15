package com.software.jgodort.graffpaper.presentation.ui.components;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by jgodinoo on 14/5/17.
 */

public class DinamicHeightImageView extends AppCompatImageView {


    private float mAspectRatio = 1.5f;

    public DinamicHeightImageView(Context context) {
        super(context);
    }

    public DinamicHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DinamicHeightImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setAspectRatio(float aspectRatio) {
        mAspectRatio = aspectRatio;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int threeTwoHeight = MeasureSpec.getSize(widthMeasureSpec) * 2 / 3;

        int threeTwoHeightSpec = MeasureSpec.makeMeasureSpec(threeTwoHeight, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, threeTwoHeightSpec);

    }
}

