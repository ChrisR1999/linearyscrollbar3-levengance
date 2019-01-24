package com.arturo.almaitu.Utillities;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

public class DisplayUtillities {
    private static double sizeX;
    private static double sizeY;

    public DisplayUtillities(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        sizeX = point.x;
        sizeY = point.y;
    }

    public static double getSizeX() {
        return sizeX;
    }

    public static double getSizeY(){
        return sizeY;
    }
}
