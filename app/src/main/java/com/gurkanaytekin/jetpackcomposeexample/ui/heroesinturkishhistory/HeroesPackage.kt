package com.gurkanaytekin.jetpackcomposeexample.ui.heroesinturkishhistory

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

class HeroesPackage (rootView: View?): ReactPackage {

    private var mRootView: View? = rootView

    override fun createViewManagers(
        reactContext: ReactApplicationContext,
    ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

    override fun createNativeModules(
        reactContext: ReactApplicationContext,
    ): MutableList<NativeModule> = listOf(BackToNativeModule(reactContext,mRootView!!) , SessionModule(reactContext,mRootView!!)).toMutableList()

}