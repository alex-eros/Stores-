package alex.eros.stores

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase


class StoreApplication : Application() {

    /*Con esto implementamos un patrón singleton pero hay que instaciar un objeto de la
    * clase aunque sea una vez, eso se hace en el manifest en la primera linea del apartado de
    * application*/
    companion object{
         lateinit var databse:StoreDatabase
    }

    override fun onCreate() {
        super.onCreate()

        databse = Room.databaseBuilder(
            this,
             StoreDatabase::class.java,
            "StoreDatabase" ).build()

    }


}