package com.example.playpropowerbutton

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setPadding

class QueuePowerButton(context: Context, var attributeSet: AttributeSet) :
    ConstraintLayout(context, attributeSet) {
    private var view = LayoutInflater.from(context).inflate(R.layout.queue_power_button, this, true)
    private var PowerAnimationManager = PowerAnimationManager()
    var circlePadding = 0F
        set(value) {
            field = value
            val circle = view.findViewById<ImageView>(R.id.mainCircle)
            circle.setPadding(circlePadding.toInt())
        }

    init {
        initAttrs(attributeSet)
    }

    private fun initAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.QueuePowerButton)
        try {
            circlePadding = typedArray.getDimension(R.styleable.QueuePowerButton_circlePadding, 0F)
            val circle = view.findViewById<ImageView>(R.id.mainCircle)
            circle.setPadding(circlePadding.toInt())
        } finally {
            typedArray.recycle()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

    fun startAnimation() {
        val greenLine = view.findViewById<CircleIncreasing>(R.id.greenLine)
        val redLine = view.findViewById<CircleIncreasing>(R.id.redLine)
        val redCenterIcon = view.findViewById<ImageView>(R.id.redIcon_imageView)
        redCenterIcon.fadeIn(
            duration = 1000,
            startOffset = 59000,
            animationListener = object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {
                    blinkAnimation(redLine, redCenterIcon)
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
        PowerAnimationManager.startIncreasingCircle(greenLine, 60000, object : IAnimationDone {
            override fun done() {

            }
        })
    }
}