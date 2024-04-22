package com.ifs21046.myrecycleview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Group(
    var name: String,
    var icon: Int,
    var description: String,
) : Parcelable