package org.techm.employeeinfo.ui.employee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techm.employeeinfo.R
import org.techm.employeeinfo.data.model.employeedisplay.DataModel

/**
 * @class{EmployeeListAdapter}
 */

class EmployeeListAdapter(private var dataModelList: ArrayList<DataModel>, var context: Context) :
    RecyclerView.Adapter<EmployeeDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeDataViewHolder =
        EmployeeDataViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_employee,
                parent,
                false
            )
        )

    override fun getItemCount() = dataModelList.size

    override fun onBindViewHolder(holder: EmployeeDataViewHolder, position: Int) {
        holder.employeeItemEmployeeBinding.employee = dataModelList[position]
    }

    fun removeItem(position: Int) {
        dataModelList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(position: Int) {
        notifyItemInserted(position)
    }

    public interface OnItemClick {
        fun onClick(position: Int)
    }

    public interface OnLongItemClick {
        fun onLongClick(position: Int)
    }

}