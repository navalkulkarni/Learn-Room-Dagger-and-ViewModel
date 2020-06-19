package com.mindorks.bootcamp.learndagger.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.mindorks.bootcamp.learndagger.data.local.DatabaseService
import com.mindorks.bootcamp.learndagger.data.local.entity.Address
import com.mindorks.bootcamp.learndagger.data.local.entity.User
import com.mindorks.bootcamp.learndagger.data.remote.NetworkService
import com.mindorks.bootcamp.learndagger.di.ActivityScope
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(
        private val compositeDisposable: CompositeDisposable,
        private val databaseService: DatabaseService,
        private val networkService: NetworkService) {
        private var allUsers: List<User> = emptyList()
        private var allAddresses : List<Address> = emptyList()
    companion object{
        const val TAG = "MainViewModel"
    }

    val user = MutableLiveData<User>()

    val userList = MutableLiveData<List<User>>()

    val addressList = MutableLiveData<List<Address>>()

    init {
        compositeDisposable.add(
                databaseService.getUserDao().count().
                        flatMap {
                            if(it == 0)
                                databaseService.getAddressDao().insertMany(
                                        Address(city = "San Diego",country = "USA"),
                                        Address(city = "San Diego",country = "USA"),
                                        Address(city = "San Diego",country = "USA"),
                                        Address(city = "San Diego",country = "USA"),
                                        Address(city = "San Diego",country = "USA"),
                                        Address(city = "San Diego",country = "USA")
                                )
                                        .flatMap {addressIds->
                                            databaseService.getUserDao().insertMany(
                                                    User(name = "Test 1",addressId = addressIds[0]),
                                                    User(name = "Test 2",addressId = addressIds[1] ),
                                                    User(name = "Test 3",addressId = addressIds[2]),
                                                    User(name = "Test 4",addressId = addressIds[3]),
                                                    User(name = "Test 5",addressId = addressIds[4]),
                                                    User(name = "Test 6",addressId = addressIds[5])
                                            )
                                        }

                            else Single.just(0)
                        }
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    Log.d(TAG,"user Exists in table $it")
                                },
                                {
                                    Log.d(TAG, it.toString())
                                }
                        )

        )
    }

    fun getAllUsers(){
        compositeDisposable.add(
                databaseService.getUserDao()
                        .getAllUsers()
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    allUsers = it
                                    Log.d(TAG,allUsers.toString())
                                    userList.postValue(it)
                                },
                                {
                                    Log.d(TAG, it.toString())
                                }
                        )
        )
    }

    fun deleteUser(){
        if(allUsers.isNotEmpty())
            compositeDisposable.add(
                    databaseService.getUserDao()
                            .delete(allUsers[0])
                            .flatMap {
                                databaseService.getUserDao().getAllUsers()
                            }
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    {
                                        allUsers = it
                                        userList.postValue(it)
                                    },
                                    {
                                        Log.d(TAG, it.toString())
                                    }
                            )
            )
    }

    fun getAllAddresses(){
        compositeDisposable.add(
                databaseService.getAddressDao()
                        .getAllAddresses()
                        .subscribeOn(Schedulers.io())
                        .subscribe(
                                {
                                    allAddresses = it
                                    Log.d(TAG,allAddresses.toString())
                                    addressList.postValue(it)
                                },
                                {
                                    Log.d(TAG, it.toString())
                                }
                        )
        )
    }

    fun deleteAddress(){
        if(allAddresses.isNotEmpty())
            compositeDisposable.add(
                    databaseService.getAddressDao()
                            .deleteAddress(allAddresses[0])
                            .flatMap {
                                databaseService.getAddressDao().getAllAddresses()
                            }
                            .subscribeOn(Schedulers.io())
                            .subscribe(
                                    {
                                        allAddresses = it
                                        addressList.postValue(it)
                                    },
                                    {
                                        Log.d(TAG, it.toString())
                                    }
                            )
            )
    }

    fun onDestroy()
    {
        compositeDisposable.dispose()
    }
    //val someData: String
        //get() = databaseService.dummyData + " : " + networkService.dummyData
}
