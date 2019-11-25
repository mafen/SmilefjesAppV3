package com.usn.smilefjes.ui.bindingAdapters;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.usn.smilefjes.R;

@SuppressWarnings("WeakerAccess")
public class ImageBindingAdapter {

    // Dette er et adapter som setter de rette bildinge til
    // de rette smilefjesne basert på karakteren som kommer inn

    @BindingAdapter("image")
    public static void setImageResource(ImageView i, int value){

        if ( value <= 1 ) {
            i.setImageResource(R.drawable.ic_satisfied_24dp);
        }
        else if (value == 2){
            i.setImageResource(R.drawable.ic_neutral_24dp);
        }
        else if (value == 3)
            i.setImageResource(R.drawable.ic_dissatisfied_24dp);


    }

}
