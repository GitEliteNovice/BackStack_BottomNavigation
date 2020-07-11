package com.example.backstack_bottomnavigation

import android.os.Build
import android.util.ArrayMap
import android.util.SparseArray
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.lang.Exception

class FragmentStack {
     var arrayList: ArrayList<String>
    var count :Int=0
    constructor(count :Int){
        arrayList= ArrayList()
       this.count=count
    }

    companion object{

  private  var stack_object:FragmentStack?=null


        fun getInstance(count :Int):FragmentStack{
    if (stack_object==null)
        stack_object= FragmentStack(count)

        return stack_object!!
        }

    }

    fun addAll(array: ArrayList<String> ?){
        arrayList.addAll(array!!)
    }

    fun push(tag:String){
     if (count==arrayList.size){
             if (remove(tag)){
                 arrayList.add(tag)
             }
     }else{

             arrayList.add(tag)
     }
    }

    fun pop(tag: String):String?{
         if (remove(tag)){
             if (arrayList.size==0){
                 return null
             }else{
                 return   arrayList.get(arrayList.size-1)
             }

         }else{
             return null
         }

    }

    fun remove(tag: String):Boolean{
        try {

            var index_value=arrayList.indexOf(tag)
            arrayList.removeAt(index_value)
        }catch (e:Exception){
            return false
        }
            return true
    }




}