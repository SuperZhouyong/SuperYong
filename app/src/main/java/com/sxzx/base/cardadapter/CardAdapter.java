package com.sxzx.base.cardadapter;

import android.support.v7.widget.CardView;

/**
 * Created by Administrator
 * on 2016/10/31.
 */

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
