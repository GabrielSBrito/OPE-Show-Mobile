package br.com.cacaushow.controledeestoquecacaushow

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Kit::class),version = 1)
abstract class LMSDatabase : RoomDatabase() {
    abstract fun kitDAO(): KitDAO



}