package com.example.contactlist

import android.content.Intent
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

        // Inflar el layout de la actividad principal y el diálogo para agregar contacto
        binding = ActivityMainBinding.inflate(layoutInflater)
        dialogContactBinding = DialogContactBinding.inflate(layoutInflater)

        // Configuración del AlertDialog para agregar contactos
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setView(dialogContactBinding.root)
        val alertDialog = alertDialogBuilder.create()

        setContentView(binding.root)

        // Inicializar el ViewModel y el adaptador de la lista de contactos
        contactListViewModel = ContactListViewModel()
        contactListAdapter = ContactListAdapter(listOf())
        initRecycler()

        // Observar cambios en la lista de contactos
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

        binding.btnViewCallHistory.setOnClickListener {
            val intent = Intent(this, CallHistoryActivity::class.java)
            startActivity(intent)
        }
    }

    // Inicializa el RecyclerView con un LinearLayoutManager y asigna el adaptador
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
                // Añade el nuevo contacto a través del ViewModel
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
