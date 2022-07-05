package alex.eros.stores

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


class StoreApplication : Application() {

    /*Con esto implementamos un patrón singleton pero hay que instanciar un objeto de la
    * clase aunque sea una vez, eso se hace en el manifest en la primera linea del apartado de
    * application*/
    companion object{
         lateinit var databse:StoreDatabase
    }

    override fun onCreate() {
        super.onCreate()

        val MIGRATION_1_2 = object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
               database.execSQL(
                   "ALTER TABLE StoreEntity ADD COLUMN photoUrl TEXT NOT NULL DEFAULT '' ")
            }
        }
        databse = Room.databaseBuilder(
            this,
             StoreDatabase::class.java,
            "StoreDatabase" )
            .addMigrations(MIGRATION_1_2)
            .build()

    }


}