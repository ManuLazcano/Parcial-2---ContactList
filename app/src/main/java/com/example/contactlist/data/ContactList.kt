package com.example.contactlist.data

object ContactList {

    val listOfContacts = mutableListOf<Contact>(
        Contact(name = "Juan Pérez", phone = "123456789"),
        Contact(name = "Ana García", phone = "987654321"),
    )

    fun addContact(contact: Contact) {
        listOfContacts.add(contact)
    }
}
