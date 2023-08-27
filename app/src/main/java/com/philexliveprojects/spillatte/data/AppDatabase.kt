package com.philexliveprojects.spillatte.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.philexliveprojects.spillatte.utils.DATABASE_NAME
import com.philexliveprojects.spillatte.utils.INSET_DATABASE_NAME

@Database(
    entities = [CoffeeDrink::class],
    version = 4,
    exportSchema = false,
)
@TypeConverters(CoffeeDrinkConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun coffeeDrinkDao(): CoffeeDrinkDao

    companion object {
        @Volatile
        var Instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return Instance ?: synchronized(this) {
                Instance ?: buildDatabase(context).also { Instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .createFromAsset(INSET_DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}