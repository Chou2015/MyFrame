package com.liam.common.utils

import android.util.Log

object LogUtil {
    private val COMMON_TAG = "LIAM"

//    var DEBUG_STATE = BuildConfig.DEBUG
    private var DEBUG_STATE = true

    fun v(any: Any, msg: String) {
        if (DEBUG_STATE) {
            Log.v(COMMON_TAG, "[" + getPreString(any) + "] "
                    + msg)
        }
    }

    fun d(any: Any, msg: String) {
        if (DEBUG_STATE) {
            Log.d(COMMON_TAG, "[" + getPreString(any) + "] "
                    + msg)
        }
    }

    fun i(any: Any, msg: String) {
        if (DEBUG_STATE) {
            Log.i(COMMON_TAG, "[" + getPreString(any) + "] "
                    + msg)
        }
    }

    fun w(any: Any, msg: String) {
        Log.w(COMMON_TAG, "[" + getPreString(any) + "] " + msg)
    }

    fun e(any: Any, msg: String) {
        Log.e(COMMON_TAG, "[" + getPreString(any) + "] " + msg)
    }

    fun v(any: Any, msg: String, tr: Throwable) {
        if (DEBUG_STATE) {
            Log.v(COMMON_TAG, "[" + getPreString(any) + "] "
                    + msg, tr)
        }
    }

    fun d(any: Any, msg: String, tr: Throwable) {
        if (DEBUG_STATE) {
            Log.d(COMMON_TAG, "[" + getPreString(any) + "] "
                    + msg, tr)
        }
    }

    fun i(any: Any, msg: String, tr: Throwable) {
        if (DEBUG_STATE) {
            Log.i(COMMON_TAG, "[" + getPreString(any) + "] "
                    + msg, tr)
        }
    }

    fun w(any: Any, msg: String, tr: Throwable) {
        Log.w(COMMON_TAG, "[" + getPreString(any) + "] " + msg,
                tr)
    }

    fun e(any: Any, msg: String, tr: Throwable) {
        Log.e(COMMON_TAG, "[" + getPreString(any) + "] " + msg,
                tr)
    }

    private fun getPreString(any: Any): String {
        var result: String
        when (any) {
            is Class<*> -> result =  any.simpleName
            else -> result = any.javaClass.simpleName
        }
        return result
    }
}