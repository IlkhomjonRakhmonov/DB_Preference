package uz.rakhmonov.i.db_preference.db

import uz.rakhmonov.i.db_preference.models.myCustomer
import uz.rakhmonov.i.db_preference.models.myEmployee
import uz.rakhmonov.i.db_preference.models.myOrder

interface myDB_Interface {
    fun addCustomer(myCustomer: myCustomer)
    fun addEmployee(myEmployee: myEmployee)
    fun addOrder(myOrder: myOrder)
    fun getAllCustomers():ArrayList<myCustomer>
    fun getAllEmployes():ArrayList<myEmployee>
    fun getAllOrders():ArrayList<myOrder>

    fun getEmployyebyID(id:Int):myEmployee
    fun getCustombyID(id:Int):myCustomer
}