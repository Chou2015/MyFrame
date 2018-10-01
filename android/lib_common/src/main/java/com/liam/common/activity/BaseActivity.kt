package com.liam.common.activity

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.liam.common.R

open class BaseActivity : StatusBarActivity() {
    private var mContainerView: ViewGroup? = null
    private var mEmptyView: View? = null
    private var mErrorView: View? = null
    private var mLoadingView: View? = null
    private var mContentView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        super.setContentView(R.layout.base_activity_layout)
        findViewById<ViewGroup>(R.id.base_root_view).fitsSystemWindows = true
        mContainerView = findViewById(R.id.base_container)

        initView()
    }

    private fun initView() {
        setEmptyView(getInflaterView(R.layout.common_empty_layout))
        setLoadingView(getInflaterView(R.layout.common_loading_layout))
        setErrorView(getInflaterView(R.layout.common_error_layout))
    }

    private fun getInflaterView(@LayoutRes layoutResID: Int): View {
        return LayoutInflater.from(this).inflate(layoutResID, mContainerView, false)
    }

    override fun setContentView(@LayoutRes layoutResID: Int) {
        val view = getInflaterView(layoutResID)
        setContentView(view)
    }

    override fun setContentView(view: View) {
        val params = view.layoutParams
        setContentView(view, params)
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        mContentView = view
        mContainerView!!.addView(view, params)

        mContainerView!!.setOnClickListener(View.OnClickListener {
            onViewClick()
        })
    }

    public fun setEmptyView(view: View) {
        mEmptyView = view

        mEmptyView!!.setOnClickListener(View.OnClickListener {
            onViewClick()
        })
    }

    public fun setErrorView(view: View) {
        mErrorView = view

        mErrorView!!.setOnClickListener(View.OnClickListener {
            onViewClick()
        })
    }

    public fun setLoadingView(view: View) {
        mLoadingView = view
        mLoadingView!!.setOnClickListener(View.OnClickListener {
            onViewClick()
        })
    }

    public fun showEmptyView() {
        mContainerView!!.removeAllViews()
        mContainerView!!.addView(mEmptyView)
    }

    public fun showErrorView() {
        mContainerView!!.removeAllViews()
        mContainerView!!.addView(mErrorView)
    }

    public fun showLoadingView() {
        mContainerView!!.removeAllViews()
        mContainerView!!.addView(mLoadingView)

//        val animatedSvgView = mLoadingView!!.findViewById<AnimatedSvgView>(R.id.animated_svg_view)
//        animatedSvgView.set
//        animatedSvgView!!.start()
    }

    public fun showContentView() {
        mContainerView!!.removeAllViews()
        mContainerView!!.addView(mContentView)
    }

    open fun onViewClick() {

    }
}
