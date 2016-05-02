package com.inforica.booker.views;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.inforica.booker.R;

/**
 * Created by user on 4/18/2016.
 */
public class MyCustomProgressDialog extends ProgressDialog {
    private AnimationDrawable animation;
    private static MyCustomProgressDialog dialog;
    public TextView textView;

    public static ProgressDialog ctor(Context context) {
        dialog = new MyCustomProgressDialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }

    public MyCustomProgressDialog(Context context) {
        super(context);
    }

    public MyCustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_custom_progress_dialog);
        ImageView la = (ImageView) findViewById(R.id.animation);
        textView = (TextView) findViewById(R.id.customprogressbar_text);
        la.setBackgroundResource(R.drawable.custom_progress_animation);
        animation = (AnimationDrawable) la.getBackground();
    }

    @Override
    public void show() {
        super.show();
        animation.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        animation.stop();
    }
}
