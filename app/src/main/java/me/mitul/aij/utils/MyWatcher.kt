package me.mitul.aij.utils

import android.text.Editable
import android.text.TextWatcher
import java.util.Locale

class MyWatcher<T>(private val items: List<T>, private val filter: ListFilter<T>) : TextWatcher {
    override fun afterTextChanged(editable: Editable) = Unit
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) =
        Unit

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
        val str = charSequence.toString().lowercase(Locale.getDefault())
        filter.setList(if (str.isBlank()) items else items.filter {
            filter.getText(it).lowercase(Locale.getDefault()).contains(str)
        })
    }

    interface ListFilter<T> {
        fun setList(items: List<T>)
        fun getText(item: T): String
    }
}
