package com.example.photos.ui.profile.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.photos.BR
import com.example.photos.R
import com.example.photos.util.bind
import com.example.photos.viewmodel.profile.ProfileViewModel

class ProfileAdapter(private val viewModel: ProfileViewModel?):
    Adapter<ProfileAdapter.ProfileViewHolder>() {
    
    inner class ProfileViewHolder(private val binding: ViewDataBinding): ViewHolder(binding.root) {
        fun bind() {
            binding.setVariable(BR.profileViewModel, viewModel)
        }
    }

    override fun getItemViewType(position: Int) = position + 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProfileViewHolder(
        bind(when(viewType) {
            VIEW_TYPE_DP -> R.layout.layout_dp
            VIEW_TYPE_DASHBOARD -> R.layout.layout_dashboard
            VIEW_TYPE_TAB -> R.layout.layout_tab
            else -> R.layout.layout_dp
        }, parent)
    )

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) = holder.bind()

    override fun getItemCount() = 3

    companion object {
        const val VIEW_TYPE_DP = 1
        const val VIEW_TYPE_DASHBOARD = 2
        const val VIEW_TYPE_TAB = 3
    }

}
