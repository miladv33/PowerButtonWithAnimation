package com.example.playpropowerbutton

import android.view.animation.Animation
import android.view.animation.Transformation


class IncreasingAnimation(circle: CircleIncreasing, newAngle: Int) : Animation() {
    private val circle: CircleIncreasing
    private val oldAngle: Float
    private val newAngle: Float
    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        val angle = oldAngle + (newAngle - oldAngle) * interpolatedTime
        circle.angle = angle
        circle.requestLayout()
    }

    init {
        oldAngle = circle.angle
        this.newAngle = newAngle.toFloat()
        this.circle = circle
    }
}