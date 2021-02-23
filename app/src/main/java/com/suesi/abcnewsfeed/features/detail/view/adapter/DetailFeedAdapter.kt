package com.suesi.abcnewsfeed.features.detail.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suesi.abcnewsfeed.data.FeedDisplayable
import com.suesi.abcnewsfeed.features.detail.view.viewholder.DetailFeedViewHolder
import com.suesi.abcnewsfeed.features.detail.view.viewholder.ItemDetailFeedViewHolder
import com.suesi.abcnewsfeed.features.detail.view.viewholder.ItemFeedViewHolder

private const val DETAILED_ITEM = 0
private const val NON_DETAILED_ITEM = 1

class DetailFeedAdapter : RecyclerView.Adapter<DetailFeedViewHolder>() {
    private val feeds = arrayListOf<FeedDisplayable>()

    override fun getItemCount(): Int = feeds.size

    override fun getItemViewType(position: Int): Int = if (position == 0) DETAILED_ITEM else NON_DETAILED_ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailFeedViewHolder {
        return if (viewType == DETAILED_ITEM) ItemDetailFeedViewHolder(parent) else ItemFeedViewHolder(parent)
    }

    override fun onBindViewHolder(holder: DetailFeedViewHolder, position: Int) {
        holder.bind(feeds[position])
    }

    fun updateFeeds(newFeeds : List<FeedDisplayable>) {
        with(feeds) {
            clear()
            addAll(newFeeds)
        }

        notifyDataSetChanged()
    }
}