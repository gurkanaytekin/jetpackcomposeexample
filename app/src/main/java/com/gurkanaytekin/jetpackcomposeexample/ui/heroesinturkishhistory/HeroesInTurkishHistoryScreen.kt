package com.gurkanaytekin.jetpackcomposeexample.ui.heroesinturkishhistory

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.facebook.soloader.SoLoader
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState

@Composable
fun HeroesInTurkishHistoryScreen(
    viewModel: HeroesInTurkishHistoryViewModel,
    navController: NavController
) {
    viewModel.setNavigation(navController)
    Log.d("HeroesInTurkishHistory", "enter")
    lateinit var reactInstanceManager: ReactInstanceManager
    SoLoader.init(LocalContext.current, false)
    var reactRootView = ReactRootView(LocalContext.current)
    val packages: List<ReactPackage> =
        PackageList(Application()).packages.apply { add(HeroesPackage(reactRootView)) }
    reactInstanceManager = ReactInstanceManager.builder()
        .setApplication(Activity().application)
        .setCurrentActivity(LocalContext.current as Activity)
        .setBundleAssetName("index.android.bundle")
        .setJSMainModulePath("index")
        .addPackages(packages)
        .setUseDeveloperSupport(false)
        .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
        .build()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login")
        Row(
            modifier = Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
        }
    }
    val lifecycleEvent = rememberLifecycleEvent()
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            reactInstanceManager.onHostResume(Activity())
        }
    }
}

@Composable
fun rememberLifecycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return state
}


@Preview(showBackground = true)
@Composable
fun HeroesInTurkishHistoryScreenPreview() {
    val viewModel = hiltViewModel<HeroesInTurkishHistoryViewModel>()
    HeroesInTurkishHistoryScreen(viewModel, rememberNavController())
}