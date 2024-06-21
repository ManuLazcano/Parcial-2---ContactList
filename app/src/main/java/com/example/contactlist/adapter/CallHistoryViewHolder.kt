package com.example.contactlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.data.Call
import com.example.contactlist.databinding.CallHistoryItemBinding

class CallHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CallHistoryItemBinding.bind(view)

    fun bind(call: Call) {
        binding.tvCallContactName.text = call.contactName
        binding.tvCallTime.text = call.time
    }
}
