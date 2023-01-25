package uz.rakhmonov.i.db_preference.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import uz.rakhmonov.i.db_preference.db.my_Const.CUSTOMER_ID
import uz.rakhmonov.i.db_preference.db.my_Const.CustomerTable
import uz.rakhmonov.i.db_preference.db.my_Const.DATE
import uz.rakhmonov.i.db_preference.db.my_Const.DB_Name
import uz.rakhmonov.i.db_preference.db.my_Const.DB_Version
import uz.rakhmonov.i.db_preference.db.my_Const.EMPLOYEE_ID
import uz.rakhmonov.i.db_preference.db.my_Const.EmployeeTable
import uz.rakhmonov.i.db_preference.db.my_Const.ID
import uz.rakhmonov.i.db_preference.db.my_Const.NAME
import uz.rakhmonov.i.db_preference.db.my_Const.NUMBER
import uz.rakhmonov.i.db_preference.db.my_Const.OrdersTable
import uz.rakhmonov.i.db_preference.models.myCustomer
import uz.rakhmonov.i.db_preference.models.myEmployee
import uz.rakhmonov.i.db_preference.models.myOrder

class myDBhelper(context: Context):SQLiteOpenHelper(context, DB_Name,null, DB_Version),myDB_Interface {
    override fun onCreate(p0: SQLiteDatabase?) {
        val customQuery="create table $CustomerTable ($ID integer not null primary key autoincrement unique, $NAME text not null, $NUMBER text not null) "
        p0?.execSQL(customQuery)
        val emloyeeQuery="create table $EmployeeTable ($ID integer not null primary key autoincrement unique, $NAME text not null, $NUMBER text not null ) "
        p0?.execSQL(emloyeeQuery)
        val ordersQuery="create table $OrdersTable ($ID integer not null primary key autoincrement unique, $NAME text not null, $DATE text not null, $CUSTOMER_ID integer not null, $EMPLOYEE_ID integer not null, foreign key ($CUSTOMER_ID) references $CustomerTable($ID), foreign key ($EMPLOYEE_ID) references $EmployeeTable($ID)) "
        p0?.execSQL(ordersQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun addCustomer(myCustomer: myCustomer) {
        val database=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(NAME, myCustomer.name)
        contentValues.put(NUMBER, myCustomer.number)
        database.insert(CustomerTable,null,contentValues)
        database.close()

    }
    override fun addEmployee(myEmployee: myEmployee) {
        val database=writableDatabase
        val contentValues=ContentValues()
        contentValues.put(NAME,myEmployee.name)
        contentValues.put(NUMBER,myEmployee.number)
        database.insert(EmployeeTable,null,contentValues)
        database.close()

    }

    override fun addOrder(myOrder: myOrder) {
        val database=writableDatabase
        val contentValues=ContentValues()
        contentValues.put(NAME,myOrder.name)
        contentValues.put(CUSTOMER_ID,myOrder.MYCustom?.id)
        contentValues.put(EMPLOYEE_ID,myOrder.MYEmployee?.id)
        database.insert(OrdersTable,null,contentValues)
        database.close()

    }

    override fun getAllCustomers(): ArrayList<myCustomer> {
        val list=ArrayList<myCustomer>()
        val database=this.readableDatabase
       val cursor= database.rawQuery("select * from $CustomerTable",null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    myCustomer(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)

            ))

            }while (cursor.moveToNext())
        }
        return list
    }

    override fun getAllEmployes(): ArrayList<myEmployee> {
        val list=ArrayList<myEmployee>()
        val database=this.readableDatabase
        val cursor=database.rawQuery("select*from $EmployeeTable", null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    myEmployee(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)))
            }while (cursor.moveToNext())
        }

        return list
    }

    override fun getAllOrders(): ArrayList<myOrder> {
        val list=ArrayList<myOrder>()
        val database=this.readableDatabase
        val cursor=database.rawQuery("select*from $OrdersTable",null)
        if (cursor.moveToFirst()){
           do {
               list.add(
                   myOrder(cursor.getInt(0),
                   cursor.getString(1),
                       cursor.getString(2),
                       getEmployyebyID(cursor.getInt(3)),
                       getCustombyID(cursor.getInt(4))

                       )
               )

           } while (cursor.moveToNext())
        }
        return list
    }

    override fun getEmployyebyID(id: Int): myEmployee {
        var database=this.readableDatabase
        var cursor=database.query(
            EmployeeTable, arrayOf(ID,NAME, NUMBER),
            "$ID=?", arrayOf(id.toString()),
            null,null,null,
        )
        cursor.moveToFirst()
        val employye=myEmployee(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return employye
    }

    override fun getCustombyID(id: Int): myCustomer {
        val database=this.readableDatabase
        val cursor=database.query(
            CustomerTable, arrayOf(ID, NAME, NUMBER),
            "$ID=?", arrayOf(id.toString()),
            null,null,null
        )
        cursor.moveToFirst()
        val customer=myCustomer(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return customer
    }
}