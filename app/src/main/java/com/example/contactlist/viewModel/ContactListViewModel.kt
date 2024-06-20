package com.example.contactlist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contactlist.data.Contact
import com.example.contactlist.data.ContactList

class ContactListViewModel : ViewModel() {
    private val _contactList = MutableLiveData<List<Contact>>()
    val contactList: LiveData<List<Contact>> = _contactList

    init {
        _contactList.value = ContactList.listOfContacts
    }

    fun addContact(name: String, phone: String) {
        val contact = Contact(name, phone)
        val updatedList = _contactList.value.orEmpty().toMutableList()

        updatedList.add(contact)
        _contactList.value = updatedList
    }
}
