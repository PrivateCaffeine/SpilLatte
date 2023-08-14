package com.philexliveprojects.spillatte.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.philexliveprojects.spillatte.utils.DATABASE_NAME

@Database(
    entities = [CoffeeDrink::class],
    version = 2,
    exportSchema = false
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
                .createFromAsset("prepopulated.db")
                .addMigrations(MIGRATION_1_2)
                .build()
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE coffee_drink ADD COLUMN uri TEXT")
            }
        }
    }
}