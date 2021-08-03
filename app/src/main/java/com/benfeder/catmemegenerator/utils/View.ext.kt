package com.benfeder.catmemegenerator.utils.View

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import com.benfeder.catmemegenerator.ui.viewmodel.CatMakerViewModel
import com.bumptech.glide.Glide

fun ImageView.loadImage(url : String){
    Glide.with(context).load(url).into(this)
}

fun CatMakerViewModel.MediaType.firstCapital(): String{
    return this.name.substring(0,1) + this.name.substring(1, this.name.length)
}

inline fun com.google.android.material.textfield.TextInputEditText.onTextViewChanged(crossinline listener: (String) -> Unit){
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {
            listener.invoke(s.toString())
        }

    })
}