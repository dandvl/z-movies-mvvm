package mx.devlabs.zmovies.activities

import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import mx.devlabs.zmovies.R

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mProgressBar: ProgressBar

    override fun setContentView(layoutResID: Int) {

        val constraintLayout = layoutInflater.inflate(R.layout.activity_base, null) as ConstraintLayout
        val frameLayout = constraintLayout.findViewById<ViewGroup>(R.id.activity_content)
        mProgressBar = constraintLayout.findViewById(R.id.progress_bar)

        layoutInflater.inflate(layoutResID, frameLayout, true)
        super.setContentView(constraintLayout)
    }

    fun showProgressBar(visibility: Boolean) {
        mProgressBar.visibility = if (visibility) View.VISIBLE else View.INVISIBLE
    }

}
