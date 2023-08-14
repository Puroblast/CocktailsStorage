package com.puroblast.cocktailsstorage.utils.adapter_delegate

interface CommonDelegateItem {
    fun content(): Any
    fun id(): Int
    fun compareToOther(other: CommonDelegateItem): Boolean
}
