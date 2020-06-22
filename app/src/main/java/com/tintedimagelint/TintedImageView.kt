package com.tintedimagelint

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class TintedImageView: AppCompatImageView {

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(
        context: Context,
        attrs: AttributeSet
    ) : super(context, attrs) {
        initialize()
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initialize()
    }


    private var tintedBitmap: Bitmap? = null


    private fun initialize() {
        setWillNotDraw(false)
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (tintedBitmap == null) {
            tintedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
                eraseColor(Color.parseColor("#96FFA500")) // The orange tint
            }
        }

        // Tinted bitmap can not be null here, but compiler!!!
        canvas.drawBitmap(tintedBitmap!!, 0f, 0f, null)

    }
}