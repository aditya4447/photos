package com.example.photos.ui.profile.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.photos.BR
import com.example.photos.R
import com.example.photos.model.profile.Image
import com.example.photos.util.bind
import com.example.photos.viewmodel.profile.ImagesViewModel

class ImageAdapter(
    private val imagesViewModel: ImagesViewModel,
    private val owner: LifecycleOwner,
    private val images: List<Image>
): Adapter<ImageAdapter.ImageHolder>() {

    inner class ImageHolder(private val binding: ViewDataBinding): ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.setVariable(BR.imagesViewModel, imagesViewModel)
            binding.setVariable(BR.imagesPosition, position)
        }
    }

    override fun getItemViewType(position: Int) =
        if (position <= images.size)
            TYPE_IMAGE
        else
            TYPE_LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageHolder(
        bind<ViewDataBinding>(
            if (viewType == TYPE_IMAGE)
                R.layout.item_image
            else
                R.layout.item_loading, parent
        ).apply { lifecycleOwner = owner }
    )

    override fun onBindViewHolder(holder: ImageHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = images.size

    companion object {
        const val TYPE_IMAGE = 1
        const val TYPE_LOADING = 2
    }
}
