package com.inforica.booker.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

import com.inforica.booker.R;


/**
 * Created by Shankar on 27-06-2015.
 */
public class ButtonView extends Button {

    public ButtonView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ButtonView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomTextView);
            String fontName = a.getString(R.styleable.CustomTextView_fontName);
            Typeface myTypeface = null;
            if (fontName != null) {

                myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }

            a.recycle();
        }
    }

}
