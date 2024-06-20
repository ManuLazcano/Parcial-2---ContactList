package com.example.contactlist.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.data.Contact
import com.example.contactlist.databinding.ContactItemBinding

class ContactListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding = ContactItemBinding.bind(view)

    fun bind(contact: Contact) {
        binding.tvName.text = contact.name
        binding.tvPhone.text = contact.phone
    }
}
