package com.omidrezabagherian.userapplication.ui.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.omidrezabagherian.userapplication.databinding.ItemRecyclerviewBinding
import com.omidrezabagherian.userapplication.model.User

class HomeAdapter(private var details: (User) -> Unit) :
    ListAdapter<User, HomeAdapter.HomeViewHolder>(HomeDiffCallback()) {

    inner class HomeViewHolder(
        private var itemRecyclerviewBinding: ItemRecyclerviewBinding,
        private var details: (User) -> Unit
    ) : RecyclerView.ViewHolder(itemRecyclerviewBinding.root) {
        fun bind(user: User) {
            Glide.with(itemRecyclerviewBinding.root).load(user.image)
                .into(itemRecyclerviewBinding.imageViewItem)

            itemRecyclerviewBinding.textFirstName.text = user.firstName

            itemRecyclerviewBinding.textLastName.text = user.lastName

            itemRecyclerviewBinding.root.setOnClickListener {
                details(user)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder =
        HomeViewHolder(
            ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            details
        )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(getItem(position))


}