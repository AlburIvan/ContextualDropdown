package com.raworkstudio.contextualdropdown

import android.graphics.Bitmap
import android.support.annotation.DrawableRes

/**
 * Created by Ivan Alburquerque on 9/8/2016
 */
data class Option(var icon: Bitmap? = null, @DrawableRes var drawableRes: Int = 0, var title: String = "N/A", var subTitle: String = "N/A")
