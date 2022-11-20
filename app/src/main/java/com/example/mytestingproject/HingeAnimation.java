package com.example.mytestingproject;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

/*
public class ViewPagerAnim implements ViewPager2.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {

        page.setTranslationX(-position * page.getWidth());
        page.setPivotX(0);
        page.setPivotY(0);

        if (position < -1) {
            page.setAlpha(0);

        } else if (position <= 0) {
            page.setRotation(90 * Math.abs(position));
            page.setAlpha(1 - Math.abs(position));

        } else if (position <= 1) {
            page.setRotation(0);
            page.setAlpha(1);

        } else {
            page.setAlpha(0);
        }

    }
}
*/


import androidx.annotation.NonNull;

public class HingeAnimation implements ViewPager2.PageTransformer {
    /*@Override
    public void transformPage(@NonNull View page, float position) {
        if (position >= -1.0F || position <= 1.0F) {
            float height = (float)page.getHeight();
            float scaleFactor = Math.max(0.85F, 1.0F - Math.abs(position));
            float vertMargin = height * (1.0F - scaleFactor) / 2.0F;
            float horzMargin = (float)page.getWidth() * (1.0F - scaleFactor) / 2.0F;
            page.setPivotY(0.5F * height);
            if (position < 0.0F) {
                page.setTranslationX(horzMargin - vertMargin / 2.0F);
            } else {
                page.setTranslationX(-horzMargin + vertMargin / 2.0F);
            }

            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setAlpha(0.5F + (scaleFactor - 0.85F) / 0.14999998F * 0.5F);
        }
    }*/


    /*private static final float MIN_SCALE = 0.65f;
    private static final float MIN_ALPHA = 0.3f;

    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position <-1){  // [-Infinity,-1)
            // This page is way off-screen to the left.
            page.setAlpha(0);
        }
        else if (position <=1){ // [-1,1]
            page.setScaleX(Math.max(MIN_SCALE,1-Math.abs(position)));
            page.setScaleY(Math.max(MIN_SCALE,1-Math.abs(position)));
            page.setAlpha(Math.max(MIN_ALPHA,1-Math.abs(position)));
        }
        else {  // (1,+Infinity]
            // This page is way off-screen to the right.
            page.setAlpha(0);
        }
    }*/


    /*@Override
    public void transformPage(@NonNull View page, float position) {
        float height = (float)page.getHeight();
        float width = (float)page.getWidth();
        float scale = min(position > 0.0F ? 1.0F : Math.abs(1.0F + position), 0.5F);
        page.setScaleX(scale);
        page.setScaleY(scale);
        page.setPivotX(width * 0.5F);
        page.setPivotY(height * 0.5F);
        page.setTranslationX(position > 0.0F ? width * position : -width * position * 0.25F);
    }

    private static float min(float val, float min) {
        return Math.max(val, min);
    }*/


    /*@Override
    public void transformPage(@NonNull View page, float position) {
        float scale = position < 0.0F ? position + 1.0F : Math.abs(1.0F - position);
        page.setScaleX(scale);
        page.setScaleY(scale);
        page.setPivotX((float)page.getWidth() * 0.5F);
        page.setPivotY((float)page.getHeight() * 0.5F);
        page.setAlpha(position >= -1.0F && position <= 1.0F ? 1.0F - (scale - 1.0F) : 0.0F);
    }*/

//    @Override
//    public void transformPage(View page, float position) {
//
//        page.setTranslationX(-position*page.getWidth());
//        page.setPivotX(0);
//        page.setPivotY(0);
//
//
//        if (position < -1){    // [-Infinity,-1)
//            // This page is way off-screen to the left.
//            page.setAlpha(0);
//
//        }
//        else if (position <= 0){    // [-1,0]
//            page.setRotation(90*Math.abs(position));
//            page.setAlpha(1-Math.abs(position));
//
//        }
//        else if (position <= 1){    // (0,1]
//            page.setRotation(0);
//            page.setAlpha(1);
//
//        }
//        else {    // (1,+Infinity]
//            // This page is way off-screen to the right.
//            page.setAlpha(0);
//
//        }
//
//
//    }

    /*@Override
    public void transformPage(@NonNull View page, float position) {
        float width = (float)page.getWidth();
        float rotation = -15.0F * position;
        page.setPivotX(width * 0.5F);
        page.setPivotY(0.0F);
        page.setTranslationX(0.0F);
        page.setRotation(rotation);
    }*/


    @Override
    public void transformPage(@NonNull View page, float position) {
        float width = (float)page.getWidth();
        float height = (float)page.getHeight();
        float rotation = -15.0F * position * -1.25F;
        page.setPivotX(width * 0.5F);
        page.setPivotY(height);
        page.setRotation(rotation);
    }

}