package com.github.kr328.clash.design.util

import androidx.recyclerview.widget.DiffUtil

fun <T> List<T>.diffWith(
    newList: List<T>,
    detectMove: Boolean = false,
    id: (T) -> Any? = { it },
): DiffUtil.DiffResult {
    val oldList = this

    return DiffUtil.calculateDiff(
        object : DiffUtil.Callback() {
            override fun areItemsTheSame(
                oldItemPosition: Int,
                newItemPosition: Int,
            ): Boolean = id(oldList[oldItemPosition]) == id(newList[newItemPosition])

            override fun getOldListSize(): Int = oldList.size

            override fun getNewListSize(): Int = newList.size

            override fun areContentsTheSame(
                oldItemPosition: Int,
                newItemPosition: Int,
            ): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
        },
        detectMove,
    )
}
