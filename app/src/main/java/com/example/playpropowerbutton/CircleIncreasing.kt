package com.example.playpropowerbutton

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat


class CircleIncreasing(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var angle: Float
    var defaultSize = dipToPx()
    var strokeWidth = 10
    private val START_ANGLE_POINT = 127F
    var strokeShader = 5F
    private var attrss =
        context?.theme?.obtainStyledAttributes(attrs, R.styleable.CircleIncreasing, 0, 0)
    private var hasShadow: Boolean = false
    private var padding  = 0F

    init {
        attrss?.apply {
            paint.color = getColor(
                R.styleable.CircleIncreasing_lineColor,
                ContextCompat.getColor(context!!, R.color.green)
            )
            hasShadow = getBoolean(R.styleable.CircleIncreasing_hasShadow, false)

            paint.strokeCap = Paint.Cap.ROUND
        }
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        angle = 286f
       
    }

   

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (hasShadow)
            strokeShader = ((defaultSize) * 0.01).toFloat()
        paint.strokeWidth = strokeWidth.toFloat()
        setShadowColor()


        val rect = RectF(
            (strokeWidth / 2).toFloat() + (strokeShader / 2),
            (strokeWidth / 2).toFloat() + (strokeShader / 2),
            defaultSize - (strokeWidth / 2).toFloat() - (strokeShader / 2),
            defaultSize - (strokeWidth / 2).toFloat() - (strokeShader / 2)
        )
        canvas.drawArc(rect, START_ANGLE_POINT, angle, false, paint)
        layoutParams.width
        layoutDirection = LAYOUT_DIRECTION_LTR
    }

    private fun setShadowColor() {
        attrss?.apply {
            val shadowColor = getColor(R.styleable.CircleIncreasing_shadowColor, 0)
            paint.setShadowLayer(strokeShader, 0F, 0F, shadowColor);
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            measureView(widthMeasureSpec, defaultSize.toInt()),
            measureView(heightMeasureSpec, defaultSize.toInt())
        )
    }

    private fun measureView(measureSpec: Int, defaultSize: Int): Int {
        var result = defaultSize
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize)
        }
        return result
    }

    private fun dipToPx(dip: Float = 200F): Float {
        val density = resources.displayMetrics.density

        return (dip * density) + (0.5f * (if (dip >= 0) 1 else -1))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        defaultSize = w.toFloat()
        strokeWidth = ((defaultSize) * 0.02).toInt()
    }
}