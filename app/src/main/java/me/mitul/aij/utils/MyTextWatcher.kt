package me.mitul.aij.utils

import android.text.Editable
import android.text.TextWatcher
import java.util.Locale

interface ListFilter<T> {
    fun setList(list: List<T>)
    fun getFilterText(item: T): String
}

class MyTextWatcher<T>(private val list: List<T>, private val filter: ListFilter<T>) : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        val str = charSequence.toString().lowercase(Locale.getDefault())
        if (str.isBlank()) filter.setList(list)
        val tempList = list.filter {
            filter.getFilterText(it).lowercase(Locale.getDefault()).contains(str)
        }
        filter.setList(tempList)
    }

    override fun afterTextChanged(editable: Editable) {}
}
