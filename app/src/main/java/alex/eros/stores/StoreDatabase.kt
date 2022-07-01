package alex.eros.stores
import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = arrayOf(Store::class),version = 1)
abstract class StoreDatabase : RoomDatabase() {

    abstract fun StoreDao():StoreDao
}