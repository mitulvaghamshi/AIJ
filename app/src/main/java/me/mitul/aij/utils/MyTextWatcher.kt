package me.mitul.aij.utils

import android.text.Editable
import android.text.TextWatcher
import java.util.Locale

class MyTextWatcher<T>(val list: ArrayList<T>, private val listOps: ArrayListOps<T>) : TextWatcher {
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        val str = charSequence.toString().lowercase(Locale.getDefault())
        listOps.onListSet(
            if (str.isEmpty()) list else {
                val tempList = ArrayList<T>()
                for (i in list.indices) {
                    val value = listOps.getName(list[i])
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
