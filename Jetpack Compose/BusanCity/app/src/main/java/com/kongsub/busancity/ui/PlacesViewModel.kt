package com.kongsub.busancity.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.kongsub.busancity.R
import com.kongsub.busancity.data.LocalPlaceDataProvider
import com.kongsub.busancity.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlacesViewModel : ViewModel() {
    /** UI state exposed to the UI **/
    private val _uiState = MutableStateFlow(
        PlacesUiState(
            placesList = LocalPlaceDataProvider.getCafesData(),
            currentPlace = LocalPlaceDataProvider.getCafesData().getOrElse(0) {
                LocalPlaceDataProvider.defaultPlaces
            }
        )
    )
    val uiState: StateFlow<PlacesUiState> = _uiState

    fun updateCurrentCategory(@StringRes selectCategory: Int){
        var placesList: List<Place> = emptyList()
        when (selectCategory) {
            R.string.cafe -> placesList = LocalPlaceDataProvider.getCafesData()
            R.string.restaurant -> placesList = LocalPlaceDataProvider.getCafesData()
            R.string.park -> placesList = LocalPlaceDataProvider.getCafesData()
            R.string.shopping_center -> placesList = LocalPlaceDataProvider.getCafesData()
            else -> placesList = LocalPlaceDataProvider.getCafesData()
        }
        _uiState.update {
            it.copy(
                placesList = placesList,
                currentCategory = selectCategory)
        }
    }
    fun updateCurrentPlace(selectedPlace: Place) {
        _uiState.update {
            it.copy(currentPlace = selectedPlace)
        }
    }

    fun navigateToCategoryListPage() {
        _uiState.update {
            it.copy(
                isShowingCategoryListPage = true,
                isShowingDetailPage = false)
        }
    }

    fun navigateToCategoryPage() {

        _uiState.update {
            it.copy(
                isShowingCategoryListPage = false,
                isShowingDetailPage = false)
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(
                isShowingCategoryListPage = false,
                isShowingDetailPage = true)
        }
    }


}


data class PlacesUiState(
    val placesList: List<Place> = emptyList(),
    val currentCategory: Int = R.string.cafe,
    val currentPlace: Place = LocalPlaceDataProvider.defaultPlaces,
    val isShowingCategoryListPage: Boolean = true,
    val isShowingDetailPage: Boolean = false
)