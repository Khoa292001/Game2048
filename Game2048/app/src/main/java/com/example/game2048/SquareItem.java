package com.example.game2048;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SquareItem extends androidx.appcompat.widget.AppCompatTextView {
    public SquareItem(Context context) {
        super(context);
    }

    public SquareItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width,width);
    }
    public void setTextt(int num){
        if ( num <= 512){
            setTextSize(35);
        }else{
            setTextSize(25);
        }
        if ( num >= 8){
            setTextColor(Color.WHITE);
        }else{
            setTextColor(Color.BLACK);
        }

        GradientDrawable drawable = (GradientDrawable) this.getBackground();
        drawable.setColor(DataGame.getDataGame().ColorNum(num));
        setBackground(drawable);

        if ( num == 0){
            setText(" ");
        }else{
            setText("" + num);
        }

    }
}
