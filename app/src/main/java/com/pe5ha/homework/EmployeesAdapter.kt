package com.pe5ha.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val EMPLOYEE_TYPE = 1
const val DEPARTMENT_TYPE = 2

class EmployeesAdapter(private val deleteAction: (Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val employees = mutableListOf<Any>()

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: ImageView = itemView.findViewById(R.id.avatarView)
        val fullnameTextView: TextView = itemView.findViewById(R.id.fullnameView)
        val departmentTextView: TextView = itemView.findViewById(R.id.departmentView)
        val deleteButtonView: ImageView = itemView.findViewById(R.id.deleteItemView)
    }

    class DepartmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val departmentTitleView: TextView = itemView.findViewById(R.id.department_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            EMPLOYEE_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.emloyees_list_item, parent, false)
                return EmployeeViewHolder(view)
            }
            DEPARTMENT_TYPE -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.department_list_item, parent, false)
                return DepartmentViewHolder(view)
            }
            else -> throw IllegalAccessException("Тип не поддерживается")
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmployeeViewHolder -> {
                val employee = employees[position] as Employee
                holder.fullnameTextView.text = employee.fullName
                holder.departmentTextView.text = employee.department
                Glide.with(holder.photoImageView.context)
                    .load(employee.photoUrl)
                    .centerCrop()
                    .into(holder.photoImageView)
                holder.deleteButtonView.setOnClickListener {
                    deleteAction(position)
                }
            }
            is DepartmentViewHolder -> {
                holder.departmentTitleView.text = (employees[position] as Department).title
            }
            else -> throw IllegalAccessException("Тип не поддерживается")
        }
    }

    override fun getItemCount(): Int = employees.size

    override fun getItemViewType(position: Int): Int {
        return when (employees[position]) {
            is Employee -> EMPLOYEE_TYPE
            is Department -> DEPARTMENT_TYPE
            else -> throw IllegalAccessException("Тип не поддерживается")
        }
    }

    fun reload(data: List<Any>) {
        employees.clear()
        employees.addAll(data)
        notifyDataSetChanged()
    }
}