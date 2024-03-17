package me.mitul.aij.utils

interface ArrayListOps<T> {
    fun onListSet(list: ArrayList<T>)
    fun getName(item: T): String
}
