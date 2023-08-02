package kr.tr.commom.utill

import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * TravelBProject
 * Created by Naedong
 * Date: 2023-08-02
 * Time: 오후 1:13
 */
class TypeConvetor<T> (private val clazz : Class<T>) {

     private val gson = GsonBuilder().create()

    fun ClassToJsonString(t : T) : String = gson.toJson(t)

    fun JsonStringToClass(data : String) : T = gson.fromJson(data, clazz)



}