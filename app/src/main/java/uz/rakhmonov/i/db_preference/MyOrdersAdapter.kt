package uz.rakhmonov.i.db_preference

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.rakhmonov.i.db_preference.databinding.MyRvItemBinding
import uz.rakhmonov.i.db_preference.models.myOrder

class RV_adapter (val list:ArrayList<myOrder>):RecyclerView.Adapter<RV_adapter.VH>(){

    inner class VH (val rvItemBinding: MyRvItemBinding): RecyclerView.ViewHolder(rvItemBinding.root){
        fun onHolder(myOrder: myOrder,position:Int){
            rvItemBinding.tvID.text=myOrder.id.toString()
            rvItemBinding.tvName.text=myOrder.name.toString()
            rvItemBinding.tvDate.text=myOrder.date


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(MyRvItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onHolder(list[position],position)

    }

    override fun getItemCount(): Int=list.size




}