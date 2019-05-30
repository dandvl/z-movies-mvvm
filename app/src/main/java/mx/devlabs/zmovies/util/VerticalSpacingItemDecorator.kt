package mx.devlabs.zmovies.util

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class VerticalSpacingItemDecorator : RecyclerView.ItemDecoration {

    private var verticalSpaceHeight : Int = 0

    constructor(verticalSpaceHeight: Int) : super(){
        this.verticalSpaceHeight = verticalSpaceHeight
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = verticalSpaceHeight
    }
}