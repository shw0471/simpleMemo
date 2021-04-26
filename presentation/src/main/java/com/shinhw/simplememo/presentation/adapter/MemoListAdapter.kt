package com.shinhw.simplememo.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shinhw.domain.entity.Memo
import com.shinhw.simplememo.R
import com.shinhw.simplememo.databinding.ItemMemoBinding

class MemoListAdapter(
    private val onItemClickListener: OnItemClickListener,
    private val onItemLongClickListener: OnItemLongClickListener
) : RecyclerView.Adapter<MemoListAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(v: View, position: Int)
    }

    private var memoList: List<Memo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoListAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_memo, parent, false))
    }

    override fun onBindViewHolder(holder: MemoListAdapter.ViewHolder, position: Int) {
        holder.itemMemoBinding.memo = memoList[position]
    }

    override fun getItemCount(): Int {
        return if (memoList.isEmpty()) 0
        else memoList.size
    }

    fun getMemoList() = memoList

    fun setMemoList(memoList: List<Memo>) {
        this.memoList = memoList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemMemoBinding: ItemMemoBinding = DataBindingUtil.bind(itemView)!!

        init {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(it, adapterPosition)
            }

            itemView.setOnLongClickListener {
                onItemLongClickListener.onItemLongClick(it, adapterPosition)
                return@setOnLongClickListener false
            }
        }
    }
}