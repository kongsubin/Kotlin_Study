package com.kongsub.busancity.data

import com.kongsub.busancity.R
import com.kongsub.busancity.model.Place

object LocalPlaceDataProvider {
    val defaultPlaces = getCafesData()[0]

    fun getCafesData(): List<Place> {
        return listOf(
            Place(
                id = 1,
                categoryId = R.string.cafe,
                titleResourceId = R.string.cafe_in_busan,
                startPoint = 4.1,
                imageResourceId = R.drawable.cafe_in_busan,
                newsDetails = R.string.cafe_detail
            ),
            Place(
                id = 2,
                categoryId = R.string.cafe,
                titleResourceId = R.string.wave_on_coffee,
                startPoint = 4.2,
                imageResourceId = R.drawable.wave_on_coffee,
                newsDetails = R.string.cafe_detail
            ),
            Place(
                id = 3,
                categoryId = R.string.cafe,
                titleResourceId = R.string.doko,
                startPoint = 4.4,
                imageResourceId = R.drawable.doko,
                newsDetails = R.string.cafe_detail
            ),
            Place(
                id = 4,
                categoryId = R.string.cafe,
                titleResourceId = R.string.magnate,
                startPoint = 4.7,
                imageResourceId = R.drawable.magnate,
                newsDetails = R.string.cafe_detail
            ),
            Place(
                id = 5,
                categoryId = R.string.cafe,
                titleResourceId = R.string.black_up_cafe,
                startPoint = 4.4,
                imageResourceId = R.drawable.black_up_cafe,
                newsDetails = R.string.cafe_detail
            ),
            Place(
                id = 6,
                categoryId = R.string.cafe,
                titleResourceId = R.string.cafe_haute,
                startPoint = 4.3,
                imageResourceId = R.drawable.cafe_haute,
                newsDetails = R.string.cafe_detail
            ),
            Place(
                id = 7,
                categoryId = R.string.cafe,
                titleResourceId = R.string.edge993,
                startPoint = 4.3,
                imageResourceId = R.drawable.edge993,
                newsDetails = R.string.cafe_detail
            )
        )
    }


    fun getParksData(): List<Place> {
        return listOf(
            Place(
                id = 1,
                categoryId = R.string.park,
                titleResourceId = R.string.heaundae_beach,
                startPoint = 4.0,
                imageResourceId = R.drawable.heaundae_beach,
                newsDetails = R.string.park_detail
            ),
            Place(
                id = 2,
                categoryId = R.string.park,
                titleResourceId = R.string.gwangalli_beach,
                startPoint = 4.2,
                imageResourceId = R.drawable.gwangalli_beach,
                newsDetails = R.string.park_detail
            ),
            Place(
                id = 3,
                categoryId = R.string.park,
                titleResourceId = R.string.un_memorial_cemetery,
                startPoint = 4.4,
                imageResourceId = R.drawable.un_memorial_cemetery,
                newsDetails = R.string.park_detail
            ),
            Place(
                id = 4,
                categoryId = R.string.park,
                titleResourceId = R.string.songdo_beach,
                startPoint = 4.0,
                imageResourceId = R.drawable.songdo_beach,
                newsDetails = R.string.park_detail
            ),
            Place(
                id = 5,
                categoryId = R.string.park,
                titleResourceId = R.string.yongdusan_park,
                startPoint = 4.0,
                imageResourceId = R.drawable.yongdusan_park,
                newsDetails = R.string.park_detail
            ),
            Place(
                id = 6,
                categoryId = R.string.park,
                titleResourceId = R.string.dongbaekseom,
                startPoint = 4.3,
                imageResourceId = R.drawable.dongbaekseom,
                newsDetails = R.string.park_detail
            ),
            Place(
                id = 7,
                categoryId = R.string.park,
                titleResourceId = R.string.dadaepo_beach,
                startPoint = 4.3,
                imageResourceId = R.drawable.dadaepo_beach,
                newsDetails = R.string.park_detail
            )
        )
    }
}