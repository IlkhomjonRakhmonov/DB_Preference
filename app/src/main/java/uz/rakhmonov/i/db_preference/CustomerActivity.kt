package uz.rakhmonov.i.db_preference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.rakhmonov.i.db_preference.adapters.CustomerEmployeeAdapter
import uz.rakhmonov.i.db_preference.databinding.ActivityCustomerBinding
import uz.rakhmonov.i.db_preference.db.myDBhelper
import uz.rakhmonov.i.db_preference.models.myCustomer

class CustomerActivity : AppCompatActivity() {
    private val binding: ActivityCustomerBinding by lazy { ActivityCustomerBinding.inflate(layoutInflater) }
    private lateinit var dBhelper: myDBhelper
    private lateinit var list: ArrayList<myCustomer>
    private lateinit var customerEmployeeAdapter:CustomerEmployeeAdapter<myCustomer>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dBhelper= myDBhelper(this)
        list=dBhelper.getAllCustomers() as ArrayList<myCustomer>

        customerEmployeeAdapter=CustomerEmployeeAdapter(list)
        binding.RV1.adapter=customerEmployeeAdapter

        binding.btn1Save.setOnClickListener {
            val customer=myCustomer(
                binding.edtName.text.toString(),
                binding.edtNumber.text.toString()
            )
            dBhelper.addCustomer(customer)
            list.add(customer)
            customerEmployeeAdapter.notifyItemInserted(list.size-1)
            Toast.makeText(this, "saqlandi", Toast.LENGTH_SHORT).show()
            binding.edtName.text.clear()
            binding.edtNumber.text.clear()

        }
    }
}