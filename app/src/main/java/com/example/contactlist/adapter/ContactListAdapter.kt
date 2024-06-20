package com.example.contactlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.R
import com.example.contactlist.data.Contact

class ContactListAdapter(private var contactList: List<Contact>) : RecyclerView.Adapter<ContactListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.contact_item, parent, false)
        return ContactListViewHolder(view)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: ContactListViewHolder, position: Int) {
        val contact = contactList[position]
        holder.bind(contact)
    }

    fun updateContactList(newContactList: List<Contact>) {
        contactList = newContactList
        notifyDataSetChanged()
    }
}
