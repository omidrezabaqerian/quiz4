package com.omidrezabagherian.userapplication.ui.items

import androidx.recyclerview.widget.DiffUtil
import com.omidrezabagherian.userapplication.model.User

class HomeDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.nationalCode == newItem.nationalCode
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}