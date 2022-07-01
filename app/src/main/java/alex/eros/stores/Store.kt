package alex.eros.stores
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "StoreEntity")
data class Store(@PrimaryKey (autoGenerate = true) var storeName:String = "",
                 var id:Long = 0,
                 var phone:String = "",
                 var webSite:String = "",
                 var isFavorite: Boolean = false)
