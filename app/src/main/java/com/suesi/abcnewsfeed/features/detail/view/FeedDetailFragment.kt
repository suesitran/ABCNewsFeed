package com.suesi.abcnewsfeed.features.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.suesi.abcnewsfeed.NewsApplication
import com.suesi.abcnewsfeed.databinding.FragmentFeedDetailBinding
import com.suesi.abcnewsfeed.features.detail._di.component.DaggerFeedDetailComponent
import com.suesi.abcnewsfeed.features.detail._di.module.FeedDetailModule
import com.suesi.abcnewsfeed.features.detail.model.FeedDetailModel
import com.suesi.abcnewsfeed.features.detail.view.adapter.DetailFeedAdapter
import com.suesi.abcnewsfeed.usecase.retrievefeedsusecase._di.module.RetrieveNewsFeedFromServerUseCaseModule
import javax.inject.Inject

class FeedDetailFragment : Fragment() {

    @Inject
    lateinit var viewModel : FeedDetailModel

    private lateinit var dataBinding : FragmentFeedDetailBinding

    private lateinit var adapter : DetailFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fail-safe: when failed to get position from sender, take default value as first item
        val position = arguments?.getInt("position") ?: 0

        DaggerFeedDetailComponent.builder()
            .applicationComponent((activity?.application as NewsApplication).component)
            .feedDetailModule(FeedDetailModule(position))
            .retrieveNewsFeedFromServerUseCaseModule(RetrieveNewsFeedFromServerUseCaseModule())
            .build()
            .inject(this)

        dataBinding = FragmentFeedDetailBinding.inflate(inflater, container, false).apply {
            viewmodel = this@FeedDetailFragment.viewModel
        }

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupObserver()
        setupAdapter()
    }

    private fun setupObserver() {
        dataBinding.viewmodel?.feeds?.observe(viewLifecycleOwner, Observer { feeds ->
            feeds?.let {
                adapter.updateFeeds(it)
            }
        })
    }

    private fun setupAdapter() {
        adapter = DetailFeedAdapter()

        dataBinding.rvDetailsList.apply {
            adapter = this@FeedDetailFragment.adapter
            layoutManager = LinearLayoutManager(this@FeedDetailFragment.context)
        }
    }
}