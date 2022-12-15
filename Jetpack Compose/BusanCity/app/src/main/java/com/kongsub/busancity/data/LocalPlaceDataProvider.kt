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

    fun getRestaurantsData(): List<Place> {
        return listOf(
            Place(
                id = 1,
                categoryId = R.string.restaurant,
                titleResourceId = R.string.jang_su_san,
                startPoint = 5.0,
                imageResourceId = R.drawable.jang_su_san,
                newsDetails = R.string.restaurant_detail
            ),
            Place(
                id = 2,
                categoryId = R.string.restaurant,
                titleResourceId = R.string.the_party_haeundae,
                startPoint = 4.2,
                imageResourceId = R.drawable.the_party_haeundae,
                newsDetails = R.string.restaurant_detail
            ),
            Place(
                id = 3,
                categoryId = R.string.restaurant,
                titleResourceId = R.string.anga,
                startPoint = 4.4,
                imageResourceId = R.drawable.anga,
                newsDetails = R.string.restaurant_detail
            ),
            Place(
                id = 4,
                categoryId = R.string.restaurant,
                titleResourceId = R.string.songjeong_samdae_gukbop,
                startPoint = 4.0,
                imageResourceId = R.drawable.songjeong_samdae_gukbop,
                newsDetails = R.string.restaurant_detail
            ),
            Place(
                id = 5,
                categoryId = R.string.restaurant,
                titleResourceId = R.string.gaemijip,
                startPoint = 4.0,
                imageResourceId = R.drawable.gaemijip,
                newsDetails = R.string.restaurant_detail
            ),
            Place(
                id = 6,
                categoryId = R.string.restaurant,
                titleResourceId = R.string.geumsu_bokkuk_main,
                startPoint = 4.0,
                imageResourceId = R.drawable.geumsu_bokkuk_main,
                newsDetails = R.string.restaurant_detail
            ),
            Place(
                id = 7,
                categoryId = R.string.restaurant,
                titleResourceId = R.string.halmae_gaya_milmyeon,
                startPoint = 4.0,
                imageResourceId = R.drawable.halmae_gaya_milmyeon,
                newsDetails = R.string.restaurant_detail
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

    fun getShoppingCentersData(): List<Place> {
        return listOf(
            Place(
                id = 1,
                categoryId = R.string.shopping_center,
                titleResourceId = R.string.shinsegae_dept_store_centum_city,
                startPoint = 4.0,
                imageResourceId = R.drawable.shinsegae_dept_store_centum_city,
                newsDetails = R.string.shopping_center_detail
            ),
            Place(
                id = 2,
                categoryId = R.string.shopping_center,
                titleResourceId = R.string.nc_department_store,
                startPoint = 4.0,
                imageResourceId = R.drawable.nc_department_store,
                newsDetails = R.string.shopping_center_detail
            ),
            Place(
                id = 3,
                categoryId = R.string.shopping_center,
                titleResourceId = R.string.lotte_department_store,
                startPoint = 4.4,
                imageResourceId = R.drawable.lotte_department_store,
                newsDetails = R.string.shopping_center_detail
            ),
            Place(
                id = 4,
                categoryId = R.string.shopping_center,
                titleResourceId = R.string.gupo_market,
                startPoint = 4.5,
                imageResourceId = R.drawable.gupo_market,
                newsDetails = R.string.shopping_center_detail
            ),
            Place(
                id = 5,
                categoryId = R.string.shopping_center,
                titleResourceId = R.string.seomyeon_underground_shopping_center,
                startPoint = 4.4,
                imageResourceId = R.drawable.seomyeon_underground_shopping_center,
                newsDetails = R.string.shopping_center_detail
            ),
            Place(
                id = 6,
                categoryId = R.string.shopping_center,
                titleResourceId = R.string.lotte_mall_dong_busan,
                startPoint = 4.0,
                imageResourceId = R.drawable.lotte_mall_dong_busan,
                newsDetails = R.string.shopping_center_detail
            ),
            Place(
                id = 7,
                categoryId = R.string.shopping_center,
                titleResourceId = R.string.nampo_underground_shopping_center,
                startPoint = 3.5,
                imageResourceId = R.drawable.nampo_underground_shopping_center,
                newsDetails = R.string.shopping_center_detail
            )
        )
    }

}