package com.example.contactlist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactlist.adapter.ContactListAdapter
import com.example.contactlist.data.Contact
import com.example.contactlist.databinding.ActivityMainBinding
import com.example.contactlist.databinding.DialogContactBinding
import com.example.contactlist.viewModel.ContactListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogContactBinding: DialogContactBinding
    private lateinit var contactListViewModel: ContactListViewModel
    private lateinit var contactListAdapter: ContactListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogContactBinding = DialogContactBinding.inflate(layoutInflater)
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogContactBinding.root)
        val alertDialog = alertDialogBuilder.create()
        setContentView(binding.root)

        contactListViewModel = ContactListViewModel()
        contactListAdapter = ContactListAdapter(listOf())
        initRecycler()

        contactListViewModel.contactList.observe(this, Observer { contactList ->
            contactListAdapter.updateContactList(contactList)
        })

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setListener(alertDialog)
    }

    private fun initRecycler() {
        binding.rvContacts.layoutManager = LinearLayoutManager(this)
        binding.rvContacts.adapter = contactListAdapter
    }

    private fun setListener(alertDialog: AlertDialog) {
        binding.fabAddContact.setOnClickListener {
            showAlertDialog(alertDialog)
        }
    }

    private fun showAlertDialog(alertDialog: AlertDialog) {
        alertDialog.show()
        clearEditText()

        dialogContactBinding.fabPlus.setOnClickListener {
            val name = addName()
            val phone = addPhone()
            if (name.isNotEmpty() && phone.isNotEmpty()) {
                contactListViewModel.addContact(name, phone)
                alertDialog.dismiss()
            }
        }
    }

    private fun addName(): String {
        return dialogContactBinding.etName.text.toString()
    }

    private fun addPhone(): String {
        return dialogContactBinding.etPhone.text.toString()
    }

    private fun clearEditText() {
        dialogContactBinding.etName.text.clear()
        dialogContactBinding.etPhone.text.clear()
    }
}
