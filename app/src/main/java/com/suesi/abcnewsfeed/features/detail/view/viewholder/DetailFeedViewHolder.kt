package com.suesi.abcnewsfeed.features.detail.view.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.suesi.abcnewsfeed.R
import com.suesi.abcnewsfeed.databinding.ItemFeedBinding
import com.suesi.abcnewsfeed.databinding.ItemFeedDetailBinding
import com.suesi.abcnewsfeed.data.FeedDisplayable

abstract class DetailFeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(feed: FeedDisplayable)
}

class ItemFeedViewHolder(
    parent: ViewGroup,
    private val dataBinding: ItemFeedBinding = ItemFeedBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )
) : DetailFeedViewHolder(dataBinding.root) {
    override fun bind(feed: FeedDisplayable) {
        dataBinding.feed = feed
        // bind data - image is from items/thumbnail
        Picasso.get()
            .load(feed.thumbnail)
            .error(R.drawable.ic_image_error)
            .into(dataBinding.ivThumbnail)
    }
}

class ItemDetailFeedViewHolder(
    parent: ViewGroup,
    private val dataBinding: ItemFeedDetailBinding = ItemFeedDetailBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    )
) : DetailFeedViewHolder(dataBinding.root) {
    override fun bind(feed: FeedDisplayable) {
        dataBinding.feed = feed

        // bind data - image is from items/enclosure/link for large image use
        Picasso.get()
            .load(feed.enclosureLink)
            .error(R.drawable.ic_image_error)
            .into(dataBinding.ivThumbnail)

    }
}