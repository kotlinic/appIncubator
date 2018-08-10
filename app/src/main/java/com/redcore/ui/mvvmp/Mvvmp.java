package com.redcore.ui.mvvmp;

import android.content.Context;
import android.content.Intent;

public class Mvvmp {
    private String desc;

    public static Intent createIntent(Context context, String desc) {
        Intent intent = new Intent(context, Mvvmp.class);
        intent.putExtra("desc", desc);
        return intent;
    }

    public void initIntentParams() {
    }
}
