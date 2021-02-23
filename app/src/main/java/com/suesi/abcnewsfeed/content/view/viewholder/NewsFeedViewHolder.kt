package com.suesi.abcnewsfeed.content.view.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suesi.abcnewsfeed.R
import com.suesi.abcnewsfeed.content.model.FeedDisplayable
import com.suesi.abcnewsfeed.databinding.ItemFeedBinding
import com.suesi.abcnewsfeed.databinding.ItemFeedHeaderBinding
import com.suesi.abcnewsfeed.retrofit.response.FeedResponse

abstract class NewsFeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(feed: FeedDisplayable)
}

class HeaderFeedViewHolder(parent : ViewGroup, inflater: LayoutInflater = LayoutInflater.from(parent.context)) : NewsFeedViewHolder(
    ItemFeedHeaderBinding.inflate(inflater, parent, false).root
) {
    override fun bind(feed: FeedDisplayable) {
        // bind data
    }
}

class ItemFeedViewHolder(parent: ViewGroup, inflater: LayoutInflater = LayoutInflater.from(parent.context)) : NewsFeedViewHolder(
    ItemFeedBinding.inflate(inflater, parent, false).root
) {
    override fun bind(feed: FeedDisplayable) {
    }
}