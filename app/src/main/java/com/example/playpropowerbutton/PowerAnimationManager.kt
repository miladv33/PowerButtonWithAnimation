package com.example.playpropowerbutton

import android.view.animation.Animation

class PowerAnimationManager {

    fun startIncreasingCircle(greenLine:CircleIncreasing,duration:Long = 1000,iAnimationDone: IAnimationDone) {
        val animation = IncreasingAnimation(greenLine, 0)
        animation.duration = duration
        animation.setAnimationListener(object:Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                iAnimationDone.done()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        greenLine.startAnimation(animation)
    }
}