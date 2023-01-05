package com.example.photos.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.photos.R
import com.example.photos.databinding.FragmentProfileBinding
import com.example.photos.util.bind

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = bind<FragmentProfileBinding>(R.layout.fragment_profile, container).root

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
