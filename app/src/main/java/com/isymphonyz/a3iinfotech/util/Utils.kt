package com.isymphonyz.a3iinfotech.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.TypedValue
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.math.BigInteger
import java.math.RoundingMode
import java.security.MessageDigest
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class Utils {

    companion object {
        @JvmStatic
        fun roundToDecimals(price: Double): String {
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.HALF_UP

            return df.format(price)
        }
    }
}