package com.pawnation.timetracker

import android.graphics.drawable.Drawable
import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("setVisibility")
fun toggleVisibility(userFloatingActionButton: FloatingActionButton, state: Boolean){
    userFloatingActionButton.visibility = when(state){
        true -> View.VISIBLE
        else -> View.INVISIBLE
    }
}

@BindingAdapter("setImage")
fun setImage(userFloatingActionButton: FloatingActionButton, drawable: Drawable){
    userFloatingActionButton.setImageDrawable(drawable)
}