package com.suesi.abcnewsfeed.features.content.view.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suesi.abcnewsfeed.features.content.model.FeedDisplayable
import com.suesi.abcnewsfeed.databinding.ItemFeedBinding
import com.suesi.abcnewsfeed.databinding.ItemFeedHeaderBinding

abstract class NewsFeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(feed: FeedDisplayable)
}

class HeaderFeedViewHolder(parent : ViewGroup, val dataBinding : ItemFeedHeaderBinding = ItemFeedHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    : NewsFeedViewHolder(dataBinding.root) {
    override fun bind(feed: FeedDisplayable) {
        dataBinding.feed = feed
        // bind data - image is from items/enclosure/link

    }
}

class ItemFeedViewHolder(parent: ViewGroup, val dataBinding: ItemFeedBinding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    : NewsFeedViewHolder(dataBinding.root) {
    override fun bind(feed: FeedDisplayable) {
        dataBinding.feed = feed
        // bind data - image is from items/thumbnail
    }
}