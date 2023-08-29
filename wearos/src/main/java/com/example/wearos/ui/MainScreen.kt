package com.example.wearos.ui

import android.content.Context
import android.util.Log
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Card
import androidx.wear.compose.material.HorizontalPageIndicator
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PageIndicatorState
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.edgeSwipeToDismiss
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.wear.compose.material.rememberSwipeToDismissBoxState
import com.example.commoncore.helper.FileStorageHelper
import com.example.commoncore.helper.SharedPreferenceHelper
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.horologist.compose.layout.ScalingLazyColumn
import com.google.android.horologist.compose.navscaffold.ExperimentalHorologistComposeLayoutApi
import com.google.android.horologist.compose.rotaryinput.rotaryWithScroll

const val PAGE_COUNT = 3
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(context: Context){

    val pagerState = rememberPagerState()
    val animatedSelectedPage by animateFloatAsState(
        targetValue = pagerState.currentPage.toFloat(),
        animationSpec = TweenSpec(durationMillis = 500),
        label = "Selected page",
    )
    val pageIndicatorState: PageIndicatorState = remember {
        object : PageIndicatorState {
            override val pageOffset: Float
                get() = animatedSelectedPage - pagerState.currentPage
            override val selectedPage: Int
                get() = pagerState.currentPage
            override val pageCount: Int
                get() = PAGE_COUNT
        }
    }
    val edgeSwipeToDismissBoxState = rememberSwipeToDismissBoxState()
    var edgeSwipeWidth by remember { mutableStateOf(0.dp) }
    Scaffold(
        pageIndicator = {
            HorizontalPageIndicator(
                pageIndicatorState = pageIndicatorState,
                modifier = Modifier.padding(10.dp),
            )
        },
    ){
        HorizontalPager(
            count = PAGE_COUNT,
            modifier = Modifier.edgeSwipeToDismiss(
                edgeSwipeToDismissBoxState,
                edgeSwipeWidth,
            ), state = pagerState){ page ->
            when(page){
                0 -> AddRecord()
                1 -> ScrollableColumn(context)
                2 -> ProgressBar()
            }

        }
    }

}

