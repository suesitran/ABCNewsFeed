package com.suesi.abcnewsfeed.features.content.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.suesi.abcnewsfeed.databinding.FragmentFeedListBinding
import com.suesi.abcnewsfeed.features.content.view.adapter.NewsFeedAdapter

class FeedListFragment : Fragment() {
    private lateinit var dataBinding: FragmentFeedListBinding
    private val adapter = NewsFeedAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        android.util.Log.d("SUESI", "started fragment list")
        dataBinding = FragmentFeedListBinding.inflate(inflater, container, false).apply {
            // setup view model
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        setupObserver()
        setupAdapter()

        // load news
    }

    private fun setupAdapter() {
        dataBinding.rvDetailsList.apply {
            layoutManager = LinearLayoutManager(this@FeedListFragment.context)
            adapter = this@FeedListFragment.adapter
        }
    }

}