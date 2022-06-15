package org.jdfossapps.android.bodydatarecord.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import androidx.room.TypeConverters

@TypeConverters(DateConverters::class)
@Database(entities = arrayOf(BodyData::class), version = 1)
abstract class BodyDataRecordDatabase : RoomDatabase() {

    abstract fun bodyDataDao(): BodyDataDao

    companion object {
        @Volatile
        private var INSTANCE: BodyDataRecordDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BodyDataRecordDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BodyDataRecordDatabase::class.java,
                    "body_data_record.db"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .addCallback(ItemDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class ItemDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        //Create inserts here
                        populateDatabase(database.bodyDataDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         */
        fun populateDatabase(bodyDataDao: BodyDataDao) {
        }

    }

}