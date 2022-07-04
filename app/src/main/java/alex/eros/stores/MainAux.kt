package alex.eros.stores

interface MainAux {

    fun fabState(isVisible:Boolean = false)
    fun addStore(store:Store)
    fun updateStore(store:Store)
}