package uz.rakhmonov.i.db_preference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.rakhmonov.i.db_preference.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding : ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.customer.setOnClickListener { startActivity(Intent(this,CustomerActivity::class.java)) }
        binding.employee.setOnClickListener { startActivity(Intent(this,EmplyeeActivity::class.java)) }
        binding.order.setOnClickListener { startActivity(Intent(this, OrderActivity::class.java)) }
    }
}