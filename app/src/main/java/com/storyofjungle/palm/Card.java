package com.storyofjungle.palm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatDrawableManager;

@SuppressLint("ViewConstructor")
public class Card extends AppCompatButton {

    private static final String LOG_TAG = Card.class.getSimpleName();

    private boolean Flipped = false;
    private boolean Flippable = true;
    private Drawable Front;
    private Drawable Back;
    private int FrontRes = 0;

    @SuppressLint("RestrictedApi")
    public Card(Context context, int id){
        super(context);
        setId(id);
        Back = AppCompatDrawableManager.get().getDrawable(context,R.drawable.e10);
        Front = AppCompatDrawableManager.get().getDrawable(context, FrontRes);
        setBackground(Back);

        ViewGroup.LayoutParams params = getLayoutParams();
        if (params != null){
            params.width = 250;
            params.height = 250;
        } else {
            params = new ViewGroup.LayoutParams(250,250);
        }
        setLayoutParams(params);
    }

    public void setId(int id){
        super.setId(id);
        if (id % 8 == 0)
            FrontRes = R.drawable.e1;
        if (id % 8 == 1)
            FrontRes = R.drawable.e2;
        if (id % 8 == 2)
            FrontRes = R.drawable.e3;
        if (id % 8 == 3)
            FrontRes = R.drawable.e4;
        if (id % 8 == 4)
            FrontRes = R.drawable.e5;
        if (id % 8 == 5)
            FrontRes = R.drawable.e6;
        if (id % 8 == 6)
            FrontRes = R.drawable.e7;
        if (id % 8 == 7)
            FrontRes = R.drawable.e8;
    }

    public int getFrontRes(){
        return FrontRes;
    }

    public boolean isFlipped(){
        return Flipped;
    }

    public boolean isFlippable(){
        return Flippable;
    }

    public void setFlippable(boolean flippable){
        Flippable = flippable;
    }

    public void flip(){
        if (!Flippable)
            return;
        if (!Flipped){
            setBackground(Front);
            Flipped = true;
        } else {
            setBackground(Back);
            Flipped = false;
        }
    }
}

