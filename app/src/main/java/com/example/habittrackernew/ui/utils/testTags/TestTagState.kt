package com.example.habittrackernew.ui.utils.testTags

data class TestTagState(
    val origin: String,
    val type: String = "",
    val action: String = "",
    val section: String = "",
    val item: String = "",
    val index: String = "",
) {
    //region --- Origin ---
    fun origin(text: String) = this.copy(origin = text)

    fun appendToOrigin(text: String) = this.copy(origin = this.origin + text)

    //endregion

    //region --- Type ---
    fun type(text: String) = this.copy(type = text)

    fun appendToType(text: String) = this.copy(type = this.type + text)

    //endregion

    //region --- Action ---
    fun action(text: String) = this.copy(action = text)

    fun appendToAction(text: String) = this.copy(action = this.action + text)

    //endregion

    //region --- Section ---
    fun section(text: String) = this.copy(section = text)

    fun appendToSection(text: String) = this.copy(section = this.section + text)

    //endregion

    //region --- Item ---
    fun item(text: String) = this.copy(item = text)

    fun appendToItem(text: String) = this.copy(item = this.item + text)

    //endregion

    //region --- Index ---
    fun index(index: Int) = this.copy(index = "$index")

    fun appendToIndex(index: Int) = this.copy(index = "${this.index}.$index")

    //endregion
}
