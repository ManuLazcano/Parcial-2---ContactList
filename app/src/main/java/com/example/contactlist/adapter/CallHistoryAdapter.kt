package com.example.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.R
import com.example.contactlist.data.Call

class CallHistoryAdapter(private val callList: List<Call>) : RecyclerView.Adapter<CallHistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CallHistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.call_history_item, parent, false)
        return CallHistoryViewHolder(view)
    }

    override fun getItemCount() = callList.size

    override fun onBindViewHolder(holder: CallHistoryViewHolder, position: Int) {
        val call = callList[position]
        holder.bind(call)
    }
}
