package com.example.astontask01.data

import androidx.annotation.DrawableRes

data class Model(
    var name: String,
    var description: String,
    var country: String,
    var genre: String,
    @DrawableRes
    var image: Int) {

    companion object {

       var modelList = mutableListOf<Model>()

    }
}
