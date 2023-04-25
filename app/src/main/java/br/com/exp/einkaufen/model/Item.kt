package br.com.exp.einkaufen.model

data class Item(
    val id: Int = 0,
    val item: String
) {

    override fun equals(other: Any?): Boolean {
        return if (other !is Item) {
            false
        }else {
            other.item == this.item
        }
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

}