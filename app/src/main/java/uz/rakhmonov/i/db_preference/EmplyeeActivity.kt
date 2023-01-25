package uz.rakhmonov.i.db_preference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.rakhmonov.i.db_preference.adapters.CustomerEmployeeAdapter
import uz.rakhmonov.i.db_preference.databinding.ActivityEmplyeeBinding
import uz.rakhmonov.i.db_preference.db.myDBhelper
import uz.rakhmonov.i.db_preference.models.myEmployee

class EmplyeeActivity : AppCompatActivity() {
    private val binding:ActivityEmplyeeBinding by lazy { ActivityEmplyeeBinding.inflate(layoutInflater) }
    private lateinit var myDBhelper: myDBhelper
    private lateinit var customerEmployeeAdapter: CustomerEmployeeAdapter<myEmployee>
    private lateinit var list: ArrayList<myEmployee>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        myDBhelper= myDBhelper(this)
        list=myDBhelper.getAllEmployes()

        customerEmployeeAdapter= CustomerEmployeeAdapter(list)

        binding.apply {
            binding.RV2.adapter=customerEmployeeAdapter
            binding.btn2Save.setOnClickListener {
                val myemployee =
                    myEmployee(binding.edtName.text.toString(), binding.edtNumber.text.toString())

                myDBhelper.addEmployee(myemployee)
                list.add(myemployee)
                customerEmployeeAdapter.notifyItemInserted(list.size-1)
                Toast.makeText(this@EmplyeeActivity, "saved", Toast.LENGTH_SHORT).show()
                binding.edtName.text.clear()
                binding.edtNumber.text.clear()
            }
        }
    }
}