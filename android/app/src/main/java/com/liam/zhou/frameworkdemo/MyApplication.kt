package com.liam.zhou.frameworkdemo

import android.app.Application
import com.liam.common.font.FontSwitcherUtils

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //切换对应字体
        FontSwitcherUtils.switchFont(this)
    }
}