package com.example.dav8

class Utils {
    fun addNewItem(arr : ArrayList<AddableItemModel>, name : String, email : String, num : String, loc : String) : ArrayList<AddableItemModel> {
        arr.add(AddableItemModel(name, email,num,loc))
        return arr
    }

}