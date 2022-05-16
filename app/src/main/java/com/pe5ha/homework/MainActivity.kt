package com.pe5ha.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val viewModel = MainActivityViewModel()
    private lateinit var employeesAdapter: EmployeesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        employeesAdapter = EmployeesAdapter(viewModel::deleteEmployee)
        recyclerView.adapter = employeesAdapter

        viewModel.employees.observe(this) {
            employeesAdapter.reload(it)
        }

        val addButton = findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener {
            viewModel.addRandomEmployee()
        }

    }
}