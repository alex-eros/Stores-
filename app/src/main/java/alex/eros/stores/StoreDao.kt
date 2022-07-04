package alex.eros.stores
import androidx.room.*

@Dao
interface StoreDao {

    @Query ("SELECT * from StoreEntity")
    fun getAllStores():MutableList<Store>

    @Insert
    fun addStore(store:Store):Long

    @Update
    fun updateStores(store:Store)

    @Delete
    fun deleteStore(store:Store)

}