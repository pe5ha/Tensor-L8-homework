package com.pe5ha.homework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil

class MainActivityViewModel : ViewModel() {
    val employees = MutableLiveData(Employee.getMockEmployees() + Department.getMockDepartment())
    private var uniqueId: Long = 10

    fun addRandomEmployee() {
        employees.value = employees.value?.toMutableList()?.apply {
            add(Employee.generateMockEmployee(uniqueId))
        }
        uniqueId++
    }

    fun deleteEmployee(position: Int) {
        employees.value = employees.value?.toMutableList()?.apply {
            // а иначе если дважды быстро кликнуть на удаление карточки — вылетает, так как выход за пределы списка
            if (position < this.size)
                removeAt(position)
        }
    }

}