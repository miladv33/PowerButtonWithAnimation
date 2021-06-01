package com.example.playpropowerbutton

import android.util.Log
import android.view.animation.Animation
import android.view.animation.Transformation


class IncreasingAnimation(circle: CircleIncreasing, newAngle: Int) : Animation() {
    private val circle: CircleIncreasing
    private val oldAngle: Float
    private val newAngle: Float
    private  val TAG = "Animation"
    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
        val angle = oldAngle + (newAngle - oldAngle) * interpolatedTime
        circle.angle = angle
        circle.requestLayout()
        Log.i(TAG, "applyTransformation: ")
    }

    init {
        oldAngle = circle.angle
        this.newAngle = newAngle.toFloat()
        this.circle = circle
        Log.i(TAG, "applyTransformation: INIT ")
    }
}