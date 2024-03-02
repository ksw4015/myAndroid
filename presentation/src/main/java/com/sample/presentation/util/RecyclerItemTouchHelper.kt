package com.sample.presentation.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sample.presentation.adapter.MoviesRecyclerAdapter

class RecyclerItemTouchHelper(
    private val recyclerView: RecyclerView
) : ItemTouchHelper.SimpleCallback(
    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
    ItemTouchHelper.LEFT
) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        // onMove는 RecyclerView가 계속 움직이기때문에 AdapterPosition 사용
        (recyclerView.adapter as MoviesRecyclerAdapter).moveItem(
            viewHolder.adapterPosition,
            target.adapterPosition
        )
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        // 선택된 Item Position을 바로 반환
        (recyclerView.adapter as MoviesRecyclerAdapter).removeItem(viewHolder.layoutPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        // Item을 선택해서 움직일때 Alpha값 조절
        when(actionState) {
            ItemTouchHelper.ACTION_STATE_DRAG,
            ItemTouchHelper.ACTION_STATE_SWIPE -> {
                (viewHolder as MoviesRecyclerAdapter.MovieViewHolder).setAlpha(0.5F)
            }
        }
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
        (viewHolder as MoviesRecyclerAdapter.MovieViewHolder).setAlpha(1.0F)
    }
}