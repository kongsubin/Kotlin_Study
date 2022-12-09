package com.kongsub.busancity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    val id: Int,
    val startPoint: Double,
    @StringRes val categoryId: Int,
    @StringRes val titleResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    @StringRes val newsDetails: Int
)
