package com.gurkanaytekin.jetpackcomposeexample.ui.heroesinturkishhistory

import android.view.View
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class SessionModule (reactContext: ReactApplicationContext , mRootView: View) : ReactContextBaseJavaModule(reactContext)  {


    override fun getName(): String {
        return  "SessionModule"
    }
}