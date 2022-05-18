package com.pe5ha.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val viewModel = MainActivityViewModel()
    private lateinit var employeesAdapter: EmployeesAdapter
    private lateinit var diffUtilCallback: EmployeesDiffUtilCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        employeesAdapter = EmployeesAdapter(viewModel::deleteEmployee)
        recyclerView.adapter = employeesAdapter

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            GridLayoutManager.VERTICAL
        )
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.item_divider)
        dividerItemDecoration.setDrawable(drawable!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        viewModel.employees.observe(this) {
            diffUtilCallback = EmployeesDiffUtilCallback(employeesAdapter.getData(), it)
            val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
            employeesAdapter.setData(it)
            diffResult.dispatchUpdatesTo(employeesAdapter)
        }

        val addButton = findViewById<FloatingActionButton>(R.id.addButton)
        addButton.setOnClickListener {
            viewModel.addRandomEmployee()
        }
    }

    class EmployeesDiffUtilCallback(
        private val oldList: List<Any>,
        private val newList: List<Any>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            when (oldList[oldItemPosition]) {
                is Employee -> {
                    if (newList[newItemPosition] is Department) return false
                    if ((oldList[oldItemPosition] as Employee).id == (newList[newItemPosition] as Employee).id) return true
                    return false
                }
                is Department -> {
                    if (newList[newItemPosition] is Employee) return false
                    if ((oldList[oldItemPosition] as Department).title == (newList[newItemPosition] as Department).title) return true
                    return false
                }
                else -> throw IllegalAccessException("Тип не поддерживается")
            }
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldItemPosition != newItemPosition) return false
                // когда карточка меняет позицию, ее в любом случае надо перерисвать?, потому что иначе
                // кнопка удаления на карточке останется ссылаться на её предыдущую позицию, а не на новую

            when (oldList[oldItemPosition]) {
                is Employee -> {
                    if (newList[newItemPosition] is Department) return false
                    val oldItem = (oldList[oldItemPosition] as Employee)
                    val newItem = (newList[newItemPosition] as Employee)
                    if (
                        oldItem.id == newItem.id
                        && oldItem.department == newItem.department
                        && oldItem.fullName == newItem.fullName
                        && oldItem.photoUrl == newItem.photoUrl
                    ) return true
                    return false
                }
                is Department -> {
                    if (newList[newItemPosition] is Employee) return false
                    if ((oldList[oldItemPosition] as Department).title == (newList[newItemPosition] as Department).title) return true
                    return false
                }
                else -> throw IllegalAccessException("Тип не поддерживается")
            }
        }

    }

}