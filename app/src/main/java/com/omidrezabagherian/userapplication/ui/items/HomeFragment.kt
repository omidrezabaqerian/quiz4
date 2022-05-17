package com.omidrezabagherian.userapplication.ui.items

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.omidrezabagherian.userapplication.R
import com.omidrezabagherian.userapplication.data.Resource
import com.omidrezabagherian.userapplication.databinding.FragmentHomeBinding
import com.omidrezabagherian.userapplication.ui.SwipeGesture
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeBinding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    private val navController by lazy { findNavController() }
    private val homeAdapter = HomeAdapter(details = {
        navController.navigate(
            HomeFragmentDirections.actionItemFragmentToUploadImageFragment(
                it._id
            )
        )
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getUsers()
        lifecycleScope.launchWhenStarted {

            homeViewModel.userList.collect {
                when (it) {
                    is Resource.Success -> {
                        homeAdapter.submitList(it.data)
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                    }
                }

            }
        }

        swipeApplication()

    }

    fun swipeApplication(): FragmentHomeBinding {
        return homeBinding.apply {
            val swipeGesture = object : SwipeGesture(view?.context!!) {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    when (direction) {
                        ItemTouchHelper.LEFT -> {
                            val position = viewHolder.absoluteAdapterPosition
                            homeViewModel.insertUser(homeAdapter.currentList.get(position))

                            recyclerViewHome.adapter?.notifyDataSetChanged()

                        }
                        ItemTouchHelper.RIGHT -> {
                            val position = viewHolder.absoluteAdapterPosition
                            lifecycleScope.launch {
                                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                                    homeViewModel.userList.collectLatest {
                                        findNavController().navigate(
                                            HomeFragmentDirections.actionItemFragmentToUploadImageFragment(
                                                it.data?.get(position)!!._id
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            val touchHelper = ItemTouchHelper(swipeGesture)
            touchHelper.attachToRecyclerView(recyclerViewHome)
        }
    }
}