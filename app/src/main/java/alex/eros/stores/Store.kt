package alex.eros.stores
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "StoreEntity")
data class Store(var storeName:String ,
                 @PrimaryKey (autoGenerate = true)
                 var id:Long = 0,
                 var phone:String ,
                 var webSite:String = "",
                 var photoUrl: String ,
                 var isFavorite: Boolean = false)
