package uz.rakhmonov.i.db_preference.models

import java.text.SimpleDateFormat
import java.util.*

class myOrder {
    var id:Int?=null
    var name:String?=null
    var date=SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Date())
    var MYEmployee:myEmployee?=null
    var MYCustom:myCustomer?=null

    constructor(
        id: Int?,
        name: String?,
        date: String?,
        MYEmployee: myEmployee?,
        MYCustom: myCustomer?
    ) {
        this.id = id
        this.name = name
        this.date = date
        this.MYEmployee = MYEmployee
        this.MYCustom = MYCustom
    }

    constructor(name: String?, MYEmployee: myEmployee?, MYCustom: myCustomer?) {
        this.name = name
        this.MYEmployee = MYEmployee
        this.MYCustom = MYCustom
    }
}