package com.gurkanaytekin.jetpackcomposeexample.ui.heroesinturkishhistory


import android.app.AlertDialog
import android.content.Intent
import android.view.View
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class BackToNativeModule (reactContext: ReactApplicationContext, rootView: View) : ReactContextBaseJavaModule(reactContext) {

    private var context: ReactApplicationContext? = reactContext
    private var rootView: View? = rootView

    override fun getName(): String {
      return  "BackToNativeModule"
    }


}