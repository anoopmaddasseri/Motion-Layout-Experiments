@file:kotlin.jvm.JvmMultifileClass
@file:kotlin.jvm.JvmName("Utils")

package com.motionlayout.experiments

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.ContextCompat

fun bitmapDrawableFromVector(
    context: Context,
    drawableId: Int
) = BitmapDrawable(context.resources, getBitmapFromVectorDrawable(context, drawableId))

fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap? {
    val drawable = ContextCompat.getDrawable(context, drawableId)
    val bitmap = Bitmap.createBitmap(
        drawable!!.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}

