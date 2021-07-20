package br.com.mmdevelopment.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Query("SELECT * FROM BusinessCard WHERE ID = :id")
    suspend fun findById(id: Int): BusinessCard

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(businessCard: BusinessCard)

    @Delete
    suspend fun delete(businessCard: BusinessCard)
}