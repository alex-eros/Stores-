package alex.eros.stores

interface OnClickListener {

    fun Onclick (storeId:Long)
    fun OnFavoriteStore(store: Store)
    fun OnDeleteStore(store:Store)

}