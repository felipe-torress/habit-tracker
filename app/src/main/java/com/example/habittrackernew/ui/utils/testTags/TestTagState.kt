package com.example.habittrackernew.ui.utils.testTags

data class TestTagState(
    val origin: String,
    val type: String = "",
    val action: String = "",
    val section: String = "",
    val item: String = "",
    val index: String = "",
) {
    fun type(text: String) = this.copy(type = text)
    fun appendToType(text: String) = this.copy(type = this.type + text)

    fun action(text: String) = this.copy(action = text)
    fun appendToAction(text: String) = this.copy(action = this.action + text)

    fun section(text: String) = this.copy(section = text)
    fun appendToSection(text: String) = this.copy(section = this.section + text)

    fun item(text: String) = this.copy(item = text)
    fun appendToItem(text: String) = this.copy(item = this.item + text)

    fun index(text: String) = this.copy(index = text)
    fun appendToIndex(text: String) = this.copy(index = this.index + text)
}
