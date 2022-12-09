package com.kongsub.busancity.ui


import android.telecom.Call.Details
import androidx.activity.compose.BackHandler
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kongsub.busancity.R
import com.kongsub.busancity.data.LocalPlaceDataProvider
import com.kongsub.busancity.model.Place
import java.lang.Math.ceil
import java.lang.Math.floor
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun BusanCityApp(
    modifier: Modifier = Modifier,
) {
    val viewModel: PlacesViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold {
        innerPadding ->
        if(uiState.isShowingCategoryListPage){
            CategoryList(
                onClick = {
                    viewModel.updateCurrentCategory(it)
                    viewModel.navigateToCategoryPage()
                }
            )
        }
        else if(uiState.isShowingDetailPage){
            PlaceDetail(
                selectedPlace = uiState.currentPlace,
                onBackPressed = {
                    viewModel.navigateToCategoryListPage()
                }
            )
        }else {
            PlaceList(
                places = uiState.placesList,
                onClick = {
                    viewModel.updateCurrentPlace(it)
                    viewModel.navigateToDetailPage()
                }
            )
        }

    }
}

@Composable
fun BusanCityAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    TopAppBar(
        title = {
            Text(
                if (isShowingDetailPage) {
                    // place title
                    "place title"
                } else {
                    stringResource(R.string.list_fragment_label)
                }
            )
        },
        navigationIcon =
        if (isShowingDetailPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        } else {
            null
        },
        modifier = modifier
    )

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryItem(
    @StringRes categoryId: Int,
    icon: ImageVector,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = 2.dp,
        modifier = modifier,
        onClick = { onClick(categoryId) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp, max = 120.dp)
                .padding(20.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = categoryId),
                modifier = modifier.size(70.dp)
            )
            Text(
                text = stringResource(id = categoryId),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(20.dp))
        }
    }
}

@Composable
fun CategoryList(
    onClick: (Int) -> Unit
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        CategoryItem(
            categoryId = R.string.cafe,
            icon = Icons.Outlined.Coffee,
            onClick = onClick
        )
        CategoryItem(
            categoryId = R.string.restaurant,
            icon = Icons.Outlined.Restaurant,
            onClick = onClick
        )
        CategoryItem(
            categoryId = R.string.park,
            icon = Icons.Outlined.Park,
            onClick = onClick
        )
        CategoryItem(
            categoryId = R.string.child,
            icon = Icons.Outlined.ChildCare,
            onClick = onClick
        )
        CategoryItem(
            categoryId = R.string.shopping_center,
            icon = Icons.Outlined.ShoppingCart,
            onClick = onClick
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListItem(
    place: Place,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = 2.dp,
        modifier = modifier,
        onClick = { onClick(place) }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp, max = 120.dp)

        ) {
            ListImageItem(place = place)
            Text(
                text = stringResource(place.titleResourceId),
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun ListImageItem(place: Place, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(170.dp)
            .heightIn(min = 120.dp, max = 120.dp)
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(place.imageResourceId),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun PlaceList(
    places: List<Place>,
    onClick: (Place) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
    ) {
        items(places, key = { place -> place.id }) { place ->
            ListItem(
                place = place,
                onClick = onClick
            )
        }
    }
}

@Composable
fun PlaceDetail(
    selectedPlace: Place,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
){
    BackHandler {
        onBackPressed()
    }
    Column( modifier = modifier.padding(4.dp)) {
        Image(
            painter = painterResource(selectedPlace.imageResourceId),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = stringResource(id = selectedPlace.titleResourceId),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(8.dp)
        )
        RatingBar(
            rating = selectedPlace.startPoint
        )
        Text(
            text = stringResource(selectedPlace.newsDetails),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(8.dp)
        )
    }

}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) {

    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))

    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }

        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = starsColor
            )
        }

        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}

@Preview
@Composable
fun PlaceDetailPreview(){
    PlaceDetail(
        selectedPlace = LocalPlaceDataProvider.defaultPlaces,
        onBackPressed = {}
    )
}

@Preview
@Composable
fun CategoryListPreview(){
    CategoryList(
        onClick = {}
    )
}

@Preview
@Composable
fun ItemPreview(){
    ListItem(
        place = LocalPlaceDataProvider.defaultPlaces,
        onClick = {}
    )
}

@Preview
@Composable
fun PlaceListPreview(){
    PlaceList(
        places = LocalPlaceDataProvider.getCafesData(),
        onClick = {}
    )
}