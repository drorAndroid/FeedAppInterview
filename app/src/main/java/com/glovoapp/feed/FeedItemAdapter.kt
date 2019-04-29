package com.glovoapp.feed

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glovoapp.feed.data.FeedItem
import kotlinx.android.synthetic.main.item.view.*


class FeedItemAdapter(var items: List<FeedItem> = arrayListOf()) :
    RecyclerView.Adapter<FeedItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: FeedItem) = with(itemView) {
            title.text = item.title
        }
    }
}