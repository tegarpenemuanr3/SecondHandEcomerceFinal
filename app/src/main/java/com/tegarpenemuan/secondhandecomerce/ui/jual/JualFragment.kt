package com.tegarpenemuan.secondhandecomerce.ui.jual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tegarpenemuan.secondhandecomerce.databinding.FragmentJualBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JualFragment : Fragment() {

    private var _binding: FragmentJualBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJualBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}