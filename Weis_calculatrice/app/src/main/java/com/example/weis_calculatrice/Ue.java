package com.example.weis_calculatrice;

import android.content.Context;
import android.widget.Button;

public class Ue {
    Button button;
    String category;
    int credits;
    int backgroundColor;

    public Ue(Context ctx, Button button, String category, int credits) {
        this.button = button;
        this.category = category;
        this.credits = credits;

        switch (category) {
            case "CS":
                backgroundColor = ctx.getResources().getColor(R.color.Peru, ctx.getTheme());
                break;
            case "TM":
                backgroundColor = ctx.getResources().getColor(R.color.CadetBlue, ctx.getTheme());
                break;
            case "ST":
                backgroundColor = ctx.getResources().getColor(R.color.YellowGreen, ctx.getTheme());
                break;
            default:
                break;
        }
    }
}
