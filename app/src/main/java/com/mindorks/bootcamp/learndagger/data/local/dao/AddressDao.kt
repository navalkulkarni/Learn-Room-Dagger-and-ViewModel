package com.mindorks.bootcamp.learndagger.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mindorks.bootcamp.learndagger.data.local.entity.Address
import io.reactivex.Single

@Dao
interface AddressDao {

    @Delete
    fun deleteAddress(address: Address) : Single<Int>

    @Insert
    fun insertMany(vararg addresses: Address) : Single<List<Long>>

    @Query("SELECT * FROM address")
    fun getAllAddresses():Single<List<Address>>
}