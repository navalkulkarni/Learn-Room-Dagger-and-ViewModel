package com.mindorks.bootcamp.learndagger.ui.main

import com.mindorks.bootcamp.learndagger.data.local.DatabaseService
import com.mindorks.bootcamp.learndagger.data.remote.NetworkService
import com.mindorks.bootcamp.learndagger.di.ActivityScope
import io.reactivex.disposables.CompositeDisposable

import javax.inject.Inject

@ActivityScope
class MainViewModel @Inject constructor(
        private val compositeDisposable: CompositeDisposable,
        private val databaseService: DatabaseService,
        private val networkService: NetworkService) {

    //val someData: String
        //get() = databaseService.dummyData + " : " + networkService.dummyData
}
