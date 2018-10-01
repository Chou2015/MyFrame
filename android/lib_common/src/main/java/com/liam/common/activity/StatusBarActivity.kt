package com.liam.common.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.liam.common.utils.LogUtil

abstract class StatusBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        overflowStatusBarSinceLollipop(true);
        setFitsSystemWindows(true)
        lightStatusBar()
    }

    /**
     * 设置为true时，系统会给布局增加一个状态栏高度的top padding, 这样布局背景就显示在系统的status bar下面
     *
     * @param fitSystemWindows
     */
    private fun setFitsSystemWindows(fitSystemWindows: Boolean) {
        val viewGroup = findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT)
        val contentView = viewGroup.getChildAt(0)
        LogUtil.d(this, "setFitsSystemWindows() contentView = " + contentView
                + ", viewGroup.childCount = " + viewGroup.childCount + ", viewGroup = " + viewGroup)
        if (contentView != null) {
            contentView.fitsSystemWindows = fitSystemWindows
            contentView.requestApplyInsets()
        }
    }

    /**
     * 状态栏亮色模式，状态栏字体颜色为黑色.
     */
    protected fun lightStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Fetch the current flags.
            val curFlags = window.decorView.systemUiVisibility
            // Update the SystemUiVisibility dependening on whether we want a Light or Dark theme.
            window.decorView.systemUiVisibility = curFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    /**
     * 状态栏暗色模式，状态栏字体颜色为白色.
     */
    protected fun darkStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Fetch the current flags.
            val curFlags = window.decorView.systemUiVisibility
            // Update the SystemUiVisibility dependening on whether we want a Light or Dark theme.
            window.decorView.systemUiVisibility = curFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

    private fun overflowStatusBarSinceLollipop(overflow: Boolean) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return
        }
        val window = window
        val decorView = window.decorView
        var option = decorView.systemUiVisibility
        when (overflow) {
            true -> option = option or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            false -> option = option and View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN.inv() and View.SYSTEM_UI_FLAG_LAYOUT_STABLE.inv()
        }
        decorView.systemUiVisibility = option
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    /**
     * 隐藏状态栏
     */
    private fun hideActionBar() {
        val actionBar = supportActionBar
        actionBar?.hide()
    }
}
