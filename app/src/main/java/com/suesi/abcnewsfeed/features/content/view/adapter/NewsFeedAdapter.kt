package com.suesi.abcnewsfeed.features.content.view.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suesi.abcnewsfeed.data.FeedDisplayable
import com.suesi.abcnewsfeed.features.content.view.viewholder.HeaderFeedViewHolder
import com.suesi.abcnewsfeed.features.content.view.viewholder.ItemFeedViewHolder
import com.suesi.abcnewsfeed.features.content.view.viewholder.NewsFeedViewHolder

private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1

class NewsFeedAdapter(private val onItemClickListener : (View, Int) -> Unit) : RecyclerView.Adapter<NewsFeedViewHolder>() {
    private val feeds = arrayListOf<FeedDisplayable>()
    override fun getItemCount(): Int = feeds.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsFeedViewHolder {
        return if (viewType == TYPE_HEADER) HeaderFeedViewHolder(parent, onItemClickListener) else ItemFeedViewHolder(parent, onItemClickListener)
    }

    override fun onBindViewHolder(holder: NewsFeedViewHolder, position: Int) {
        holder.bind(feeds[position], position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else TYPE_ITEM
    }

    fun updateFeeds(newFeeds : List<FeedDisplayable>) {
        feeds.apply {
            clear()
            addAll(newFeeds)
        }
        notifyDataSetChanged()
    }
}