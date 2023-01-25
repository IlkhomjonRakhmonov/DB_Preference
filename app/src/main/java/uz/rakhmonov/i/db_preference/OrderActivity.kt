package uz.rakhmonov.i.db_preference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import uz.rakhmonov.i.db_preference.R
import uz.rakhmonov.i.db_preference.databinding.ActivityOrderBinding
import uz.rakhmonov.i.db_preference.databinding.MyRvItemBinding
import uz.rakhmonov.i.db_preference.db.myDBhelper
import uz.rakhmonov.i.db_preference.models.myCustomer
import uz.rakhmonov.i.db_preference.models.myEmployee
import uz.rakhmonov.i.db_preference.models.myOrder
import java.lang.reflect.Array.get

class OrderActivity : AppCompatActivity() {
    private val binding:ActivityOrderBinding by lazy { ActivityOrderBinding.inflate(layoutInflater) }
    private lateinit var list: ArrayList<myOrder>
    private lateinit var dBhelper: myDBhelper
    private lateinit var rvAdapter:RV_adapter
    private lateinit var customerList: ArrayList<myCustomer>
    private lateinit var employeeList: ArrayList<myEmployee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        dBhelper= myDBhelper(this)
        list=ArrayList()
         list.addAll(dBhelper.getAllOrders())

        customerList=dBhelper.getAllCustomers() as ArrayList<myCustomer>
        employeeList=dBhelper.getAllEmployes() as ArrayList<myEmployee>


        val custListName=ArrayList<String>()
        customerList.forEach {
            custListName.add(it.name!!)
        }

        val empListName=ArrayList<String>()
        employeeList.forEach {
            empListName.add(it.name!!)
        }
            val custspinnerAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,custListName)
            val employeespinAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,empListName)


        binding.spinnerCustomer.adapter=custspinnerAdapter
        binding.spinnerEmployee.adapter=employeespinAdapter
        binding.btn3Save.setOnClickListener {

            val myorder=myOrder(
                binding.edtName.toString(),
                employeeList[binding.spinnerEmployee.selectedItemPosition],
                customerList[binding.spinnerCustomer.selectedItemPosition]
            )
            dBhelper.addOrder(myorder)
            Toast.makeText(this, "order saved", Toast.LENGTH_SHORT).show()
        }
    }
}