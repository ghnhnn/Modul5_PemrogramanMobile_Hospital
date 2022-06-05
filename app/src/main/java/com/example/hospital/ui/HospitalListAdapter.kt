package com.example.hospital.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.databinding.ListViewItemBinding
import com.example.hospital.network.Hospital

class HospitalListAdapter (val clickListener: HospitalListener) :
    ListAdapter<Hospital, HospitalListAdapter.HospitalViewHolder>(DiffCallback) {

    class HospitalViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: HospitalListener, hospital: Hospital) {
            binding.hospital = hospital
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Hospital>() {

        override fun areItemsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
            return oldItem.region == newItem.region
        }

        override fun areContentsTheSame(oldItem: Hospital, newItem: Hospital): Boolean {
            return oldItem.regionCode == newItem.regionCode && oldItem.period == newItem.period
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HospitalViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HospitalViewHolder, position: Int) {
        val hospital = getItem(position)
        holder.bind(clickListener, hospital)
    }
}

class HospitalListener(val clickListener: (hospital: Hospital) -> Unit) {
    fun onClick(hospital: Hospital) = clickListener(hospital)
}