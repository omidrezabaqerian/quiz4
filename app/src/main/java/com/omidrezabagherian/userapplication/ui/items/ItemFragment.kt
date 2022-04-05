package com.omidrezabagherian.userapplication.ui.items

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.omidrezabagherian.userapplication.R
import com.omidrezabagherian.userapplication.databinding.FragmentItemBinding
import com.omidrezabagherian.userapplication.model.User

class ItemFragment : Fragment(R.layout.fragment_item) {

    private lateinit var imageBinding: FragmentItemBinding
    private val viewModel: ItemViewModel by viewModels()
    private lateinit var itemAdapter: ItemAdapter
    private var listUsers = mutableListOf<User>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController by lazy { findNavController() }

        imageBinding = FragmentItemBinding.bind(view)

        viewModel.showUserList()

        viewModel.searchResponse.observe(viewLifecycleOwner, Observer {
            listUsers.clear()
            listUsers.addAll(it)
            itemAdapter.notifyDataSetChanged()
        })

        itemAdapter = ItemAdapter(view.context, listUsers)
        imageBinding.recyclerViewItem.layoutManager = LinearLayoutManager(view.context)
        imageBinding.recyclerViewItem.adapter = itemAdapter

        itemAdapter.setItemUserClick(object : ItemAdapter.ItemClick {
            override fun viewClick(position: Int, v: View?) {
                navController.navigate(
                    ItemFragmentDirections.actionItemFragmentToUploadImageFragment(
                        listUsers[position]._id,
                        listUsers[position].nationalCode,
                        listUsers[position].firstName,
                        listUsers[position].lastName
                    )
                )
            }
        })

        imageBinding.floatingActionButtonAddItem.setOnClickListener {
            navController.navigate(R.id.userCreateFragment)
        }

    }

}