package com.suesi.abcnewsfeed.features.content.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.suesi.abcnewsfeed.NewsApplication
import com.suesi.abcnewsfeed.R
import com.suesi.abcnewsfeed.databinding.FragmentFeedListBinding
import com.suesi.abcnewsfeed.features.content._di.component.DaggerFeedListComponent
import com.suesi.abcnewsfeed.features.content._di.module.FeedListModule
import com.suesi.abcnewsfeed.features.content.model.FeedListViewModel
import com.suesi.abcnewsfeed.features.content.view.adapter.NewsFeedAdapter
import javax.inject.Inject

class FeedListFragment : Fragment() {
    private lateinit var dataBinding: FragmentFeedListBinding

    private val onItemClickListener : (View, Int) -> Unit = { view : View, position : Int ->
        val navController = Navigation.findNavController(requireView())

        // avoid Destination not found issue when click action happens too fast and causing a conflict in currentDestination
        if (navController.currentDestination?.id == R.id.feedListFragment) {
            navController.navigate(R.id.action_feedListFragment_to_feedDetailFragment, bundleOf("position" to position))
        }

    }

    private val adapter = NewsFeedAdapter(onItemClickListener)

    @Inject
    lateinit var viewModel: FeedListViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // setup Dagger
        DaggerFeedListComponent.builder()
            .applicationComponent((activity?.application as NewsApplication).component)
            .feedListModule(FeedListModule())
            .build()
            .inject(this)

        dataBinding = FragmentFeedListBinding.inflate(inflater, container, false).apply {
            // setup view model
            viewmodel = this@FeedListFragment.viewModel
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupObserver()
        setupAdapter()
        setupPullToRefresh()

        // load news
    }

    private fun setupAdapter() {
        dataBinding.rvDetailsList.apply {
            layoutManager = LinearLayoutManager(this@FeedListFragment.context)
            adapter = this@FeedListFragment.adapter
        }
    }


    private fun setupObserver() {
        dataBinding.viewmodel?.feeds?.observe(viewLifecycleOwner, Observer { newFeeds ->
            newFeeds?.let {
                adapter.updateFeeds(it)
            }
        })

        dataBinding.viewmodel?.isLoading?.observe(viewLifecycleOwner, Observer { isLoading ->
            dataBinding.srlPullToRefresh.isRefreshing = isLoading ?: false
        })
    }

    private fun setupPullToRefresh() {
        dataBinding.srlPullToRefresh.setOnRefreshListener {
            dataBinding.viewmodel?.loadNews()
        }
    }
}