package com.liam.zhou.frameworkdemo

import android.os.Bundle
import android.widget.TextView
import com.liam.common.activity.BaseActivity

class WelcomeActivity : BaseActivity() {
    var mCount: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        var tv = findViewById<TextView>(R.id.sample_text)
        tv.setText("hello kotlin")
        showLoadingView()
    }

    override fun onViewClick() {
        super.onViewClick()
        when (mCount) {
            0 -> showEmptyView()
            1 -> showErrorView()
            2 -> showContentView()
            3 -> showLoadingView()
        }
        mCount++
        if (mCount > 3) {
            mCount = 0
        }
    }
}
