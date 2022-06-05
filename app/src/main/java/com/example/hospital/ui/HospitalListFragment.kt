package com.example.hospital.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hospital.R
import com.example.hospital.databinding.FragmentHospitalListBinding

class HospitalListFragment : Fragment() {

    private val viewModel: HospitalViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHospitalListBinding.inflate(inflater)
        viewModel.getHospitalList()
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = HospitalListAdapter(HospitalListener { hospital ->
            viewModel.onHospitalClick(hospital)
            findNavController()
                .navigate(R.id.action_hospitalListFragment_to_hospitalDetailFragment)
        })

        // Inflate the layout for this fragment
        return binding.root
    }
}