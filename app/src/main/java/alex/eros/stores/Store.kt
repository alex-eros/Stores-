package alex.eros.stores

data class Store(var storeName:String = "",
                 var id:Long = 0,
                 var phone:String = "",
                 var webSite:String = "",
                 var isFavorite: Boolean = false)
