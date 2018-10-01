package com.liam.common.font

import android.content.Context
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import com.liam.common.R

object FontSwitcherUtils {
    private val TAG = "FontSwitcherUtils"

    private val FONT_MEDIUM = "fonts/Fontyou-Booster_Next_FY_Medium.otf"
    private val FONT_BOLD = "fonts/Fontyou-Booster_Next_FY_Bold.otf"
    private val FONT_BLACK = "fonts/Fontyou-Booster_Next_FY_Black.ttf"
    private var sBoldTypeface: Typeface? = null
    private var sBlackTypeface: Typeface? = null
    private var sT1 = 0f
    private var sT2 = 0f
    /**
     * 要被替换掉的字体类型在[Typeface]中的成员变量名
     */
    private val SANS_FIELD_NAME_IN_TYPEFACE_REPLACED = "SANS_SERIF"
    private val MONOSPACE_FIELD_NAME_IN_TYPEFACE_REPLACED = "MONOSPACE"
    private val SERIF_FIELD_NAME_IN_TYPEFACE_REPLACED = "SERIF"

    fun switchFont(context: Context) {
        // 将sans字体替换成FONT_BLACK
        switchFont(context, SANS_FIELD_NAME_IN_TYPEFACE_REPLACED, FONT_BLACK)
        // 将monospace字体替换成FONT_BOLD
        switchFont(context, MONOSPACE_FIELD_NAME_IN_TYPEFACE_REPLACED, FONT_BOLD)
        // 将serif字体替换成FONT_MEDIUM
        switchFont(context, SERIF_FIELD_NAME_IN_TYPEFACE_REPLACED, FONT_MEDIUM)

        sT1 = context.resources.getDimension(R.dimen.common_primary_header_t1)
        sT2 = context.resources.getDimension(R.dimen.common_secondary_header_t2)
        sBoldTypeface = Typeface.createFromAsset(context.assets, FONT_BOLD)
        sBlackTypeface = Typeface.createFromAsset(context.assets, FONT_BLACK)
    }

    fun switchFont(context: Context?, originalFont: String, targetFont: String) {
        if (context == null) {
            //            LogUtil.e(TAG, "switchFont(context, targetFont) find context is null");
            throw IllegalArgumentException("context is null")
        }
        try {
            val field = Typeface::class.java.getDeclaredField(originalFont)
            field.setAccessible(true)
            field.set(null, Typeface.createFromAsset(context.assets, targetFont))
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    fun setTypeFace(textView: TextView) {
        try {
            val textSize = textView.textSize
            if (Math.abs(textSize - sT1) < 1f) {
                textView.typeface = sBlackTypeface
            } else if (Math.abs(textSize - sT2) < 1f) {
                textView.typeface = sBoldTypeface
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * @param textContent
     * @param keyText
     * @param color
     * @param textSize
     */
    fun getSpannableStringBuilder(textContent: String, keyText: String, color: Int, textSize: Int): SpannableStringBuilder {
        if (TextUtils.isEmpty(textContent)) {
            return SpannableStringBuilder("")
        }

        if (TextUtils.isEmpty(keyText)) {
            return SpannableStringBuilder(textContent)
        }

        val position = textContent.indexOf(keyText)
        val builder = SpannableStringBuilder(textContent)

        // 字体大小
        val sizeSpan = AbsoluteSizeSpan(textSize)
        builder.setSpan(sizeSpan, position, position + keyText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 字体颜色
        val colorSpan = ForegroundColorSpan(color)
        builder.setSpan(colorSpan, position, position + keyText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        return builder
    }

}