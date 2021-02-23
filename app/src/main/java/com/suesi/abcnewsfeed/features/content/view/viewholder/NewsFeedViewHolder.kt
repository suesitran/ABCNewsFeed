package com.suesi.abcnewsfeed.features.content.view.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suesi.abcnewsfeed.R
import com.suesi.abcnewsfeed.data.FeedDisplayable
import com.suesi.abcnewsfeed.databinding.ItemFeedBinding
import com.suesi.abcnewsfeed.databinding.ItemFeedHeaderBinding

abstract class NewsFeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(feed: FeedDisplayable, position: Int)
}

class HeaderFeedViewHolder(parent : ViewGroup, val onItemClickListener : (View, Int) -> Unit, private val dataBinding : ItemFeedHeaderBinding = ItemFeedHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    : NewsFeedViewHolder(dataBinding.root) {

    override fun bind(feed: FeedDisplayable, position: Int) {
        dataBinding.feed = feed
        // bind data - image is from items/enclosure/link
        Picasso.get()
            .load(feed.enclosureLink)
            .error(R.drawable.ic_image_error)
            .into(dataBinding.ivThumbnail)

        dataBinding.root.setOnClickListener {
            onItemClickListener(dataBinding.root, position)
        }
    }
}

class ItemFeedViewHolder(parent: ViewGroup, val onItemClickListener : (View, Int) -> Unit, private val dataBinding: ItemFeedBinding = ItemFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    : NewsFeedViewHolder(dataBinding.root) {
    override fun bind(feed: FeedDisplayable, position: Int) {
        dataBinding.feed = feed
        // bind data - image is from items/thumbnail
        Picasso.get()
            .load(feed.thumbnail)
            .error(R.drawable.ic_image_error)
            .into(dataBinding.ivThumbnail)

        dataBinding.root.setOnClickListener {
            onItemClickListener(dataBinding.root, position)
        }
    }
}