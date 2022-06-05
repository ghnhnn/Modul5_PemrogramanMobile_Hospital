package com.example.hospital

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.network.Hospital
import com.example.hospital.ui.HospitalApiStatus
import com.example.hospital.ui.HospitalListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Hospital>?) {
    val adapter = recyclerView.adapter as HospitalListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: HospitalApiStatus?) {
    when(status) {
        HospitalApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HospitalApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        HospitalApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}