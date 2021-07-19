package br.com.mmdevelopment.businesscard.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BusinessCardDao {

    @Query("SELECT * FROM BusinessCard")
    fun getAll(): LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(businessCard: BusinessCard)

    @Delete
    fun delete(businessCard: BusinessCard)
}