package uz.rakhmonov.i.db_preference.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rakhmonov.i.db_preference.databinding.MyRvItemBinding
import uz.rakhmonov.i.db_preference.models.myCustomer
import uz.rakhmonov.i.db_preference.models.myEmployee

class CustomerEmployeeAdapter<T> (val list:List<T>): RecyclerView.Adapter<CustomerEmployeeAdapter<T>.VH>(){

    inner class VH (val rvItemBinding:MyRvItemBinding):RecyclerView.ViewHolder(rvItemBinding.root){
        fun onHolder(cust_empl: T,position: Int){
            try {
                val customer:myCustomer=cust_empl as myCustomer
                rvItemBinding.tvID.setText(customer.id.toString())
                rvItemBinding.tvName.setText(customer.name)
                rvItemBinding.tvDate.setText(customer.number)
            }catch (e:Exception){
                val employee:myEmployee=cust_empl as myEmployee
                rvItemBinding.tvID.text=employee.id.toString()
                rvItemBinding.tvName.text=employee.name.toString()
                rvItemBinding.tvDate.text=employee.number
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH  {
        return VH(MyRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onHolder(list[position],position)

    }

    override fun getItemCount(): Int=list.size




}