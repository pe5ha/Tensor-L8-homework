package com.pe5ha.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class EmployeesAdapter() : RecyclerView.Adapter<EmployeesAdapter.EmployeeViewHolder>() {

    private val employees = Employee.getMockEmloyees()

    class EmployeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: ImageView = itemView.findViewById(R.id.avatarView)
        val fullnameTextView: TextView = itemView.findViewById(R.id.fullnameView)
        val departmentTextView: TextView = itemView.findViewById(R.id.departmentView)
        val deleteButtonView: ImageView = itemView.findViewById(R.id.deleteItemView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmployeesAdapter.EmployeeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.emloyees_list_item, parent, false)
        return EmployeeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeesAdapter.EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.fullnameTextView.text = employee.fullName
        holder.departmentTextView.text = employee.department
        Glide.with(holder.photoImageView.context)
            .load(employee.photoUrl)
            .centerCrop()
            .into(holder.photoImageView)
    }

    override fun getItemCount(): Int = employees.size

}