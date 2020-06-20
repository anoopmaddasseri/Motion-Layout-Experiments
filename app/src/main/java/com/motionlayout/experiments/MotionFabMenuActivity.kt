package com.motionlayout.experiments

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_motion_fab_menu.*

class MotionFabMenuActivity : AppCompatActivity(R.layout.activity_motion_fab_menu) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMotionListener()
    }

    private fun setMotionListener() {
        val transitionDrawable = TransitionDrawable(
            arrayOf(
                bitmapDrawableFromVector(this, R.drawable.ic_add),
                bitmapDrawableFromVector(this, R.drawable.ic_remove)
            )
        )
        transitionDrawable.isCrossFadeEnabled = true
        menu_anchor_fab.setImageDrawable(transitionDrawable)

        motionLayout.setTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
            }

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                transitionDrawable.apply {
                    if (p0?.currentState == p0?.endState)
                        startTransition(0)
                    else
                        reverseTransition(200)
                }
            }
        })
    }

}
