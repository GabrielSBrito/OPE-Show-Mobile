package br.com.cacaushow.controledeestoquecacaushow

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface KitDAO {
    @Query("SELECT * FROM kits WHERE id = :id")
    fun getById(id:Long):Kit?

    @Query("SELECT * FROM kits")
    fun findAll():List<Kit>

    @Insert
    fun insert(kit: Kit)

    @Delete
    fun delete(kit: Kit)
}