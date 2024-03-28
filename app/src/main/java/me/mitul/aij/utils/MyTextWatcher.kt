package me.mitul.aij.utils

import android.text.Editable
import android.text.TextWatcher
import java.util.Locale

interface ListOps<T> {
    fun setList(list: List<T>)
    fun getName(item: T): String
}

class MyTextWatcher<T>(val list: List<T>, private val ops: ListOps<T>) : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        val str = charSequence.toString().lowercase(Locale.getDefault())
        ops.setList(
            if (str.isEmpty()) list else {
                val tempList = ArrayList<T>()
                for (i in list.indices) {
                    val value = ops.getName(list[i])
                    if (value.lowercase(Locale.getDefault()).startsWith(str) ||
                        value.lowercase(Locale.getDefault()).contains(str)
                    ) tempList.add(list[i])
                }
                tempList
            }
        )
    }

    override fun afterTextChanged(editable: Editable) {}
}
