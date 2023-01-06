package com.example.photos.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import com.example.photos.R
import com.example.photos.databinding.FragmentProfileBinding
import com.example.photos.ui.profile.adapter.ImageAdapter
import com.example.photos.util.bind
import com.example.photos.util.observeEvent
import com.example.photos.viewmodel.profile.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val imagesViewModel: ImagesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = bind<FragmentProfileBinding>(R.layout.fragment_profile, container).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (binding.rvProfile.layoutManager as GridLayoutManager).apply {
            spanCount = 2
            spanSizeLookup =
                object: SpanSizeLookup() {
                    override fun getSpanSize(position: Int) =
                        if (position < 3 || position == imagesViewModel.images.size)
                            2
                        else
                            1
                }
        }
//        binding.rvProfile.adapter = ProfileAdapter(null)
        binding.rvProfile.adapter = ImageAdapter(imagesViewModel, viewLifecycleOwner)
        observeEvent(imagesViewModel.imagesAddedEvent) {
            binding.rvProfile.adapter?.notifyItemRangeInserted(
                imagesViewModel.images.size - 2, it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
