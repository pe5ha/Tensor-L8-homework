package com.pe5ha.homework

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val employees = MutableLiveData(Employee.getMockEmployees() + Department.getMockDepartment())

    fun addRandomEmployee(){
        employees.value = employees.value?.toMutableList()?.apply {
            add(Employee.getMockEmployees().random())
        }
    }

    fun deleteEmployee(position: Int){
        employees.value = employees.value?.toMutableList()?.apply {
            removeAt(position)
        }
    }
}