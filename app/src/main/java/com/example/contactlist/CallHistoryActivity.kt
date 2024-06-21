package com.example.contactlist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlist.adapter.CallHistoryAdapter
import com.example.contactlist.data.CallHistory
import com.example.contactlist.databinding.ActivityCallHistoryBinding

class CallHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCallHistoryBinding
    private lateinit var callHistoryAdapter: CallHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCallHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callHistoryAdapter = CallHistoryAdapter(CallHistory.listOfCalls)
        initRecycler()

        // Configura el botón para volver a la pantalla principal
        binding.btnBackToMain.setOnClickListener {
            finish() // Esto cerrará la actividad actual y volverá a la anterior
        }

    }

    private fun initRecycler() {
        binding.rvCallHistory.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        binding.rvCallHistory.adapter = callHistoryAdapter
    }

}
