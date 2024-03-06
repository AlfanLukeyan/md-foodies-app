package com.example.foodiesapp2

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    val name: String,
    val description: String,
    val photo: Int,
    val weight: String,
    val likes: String,
) : Parcelable
