package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase3

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import com.lukaslechner.coroutineusecasesonandroid.mock.VersionFeatures
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class PerformNetworkRequestsConcurrentlyViewModel(
    private val mockApi: MockApi = mockApi()
) : BaseViewModel<UiState>() {

    fun performNetworkRequestsSequentially() {
        uiState.value = UiState.Loading
        val startTime = System.currentTimeMillis()
//        viewModelScope.launch {
//            val recentAndroidVersions = mockApi.getRecentAndroidVersions()
//            Timber.d(recentAndroidVersions.toString())
//            var versionDetails = mutableListOf<VersionFeatures>()
//            for (version in recentAndroidVersions){
//                val versionOneDetails = mockApi.getAndroidVersionFeatures(version.apiLevel)
//                Timber.d(versionOneDetails.toString())
//                Timber.d((System.currentTimeMillis() - startTime).toString())
//                versionDetails.add(versionOneDetails)
//            }
//            uiState.value = UiState.Success(versionDetails)
//        }

        // improved code for 21 - 32
        viewModelScope.launch{
            val recentAndroidVersions = mockApi.getRecentAndroidVersions()
            val androidVersionDetails = recentAndroidVersions.map { androidVersion ->
                mockApi.getAndroidVersionFeatures(androidVersion.apiLevel)
            }
            uiState.value = UiState.Success(androidVersionDetails)
        }
    }

    fun performNetworkRequestsConcurrently() {
        uiState.value = UiState.Loading;
        val startTime = System.currentTimeMillis()
//        viewModelScope.launch{
//            val recentAndroidVersions = mockApi.getRecentAndroidVersions()
//            val list = mutableListOf<VersionFeatures>()
////            val job1 = launch{
////                val versionDetail = mockApi().getAndroidVersionFeatures(recentAndroidVersions[0].apiLevel)
////                Timber.d(versionDetail.toString())
////                Timber.d((System.currentTimeMillis() - startTime).toString())
////                list.add(versionDetail)
////            }
////            val job2 = launch{
////                val versionDetail = mockApi().getAndroidVersionFeatures(recentAndroidVersions[0].apiLevel)
////                Timber.d(versionDetail.toString())
////                Timber.d((System.currentTimeMillis() - startTime).toString())
////                list.add(versionDetail)
////            }
////            val job3 = launch(){
////                val versionDetail = mockApi().getAndroidVersionFeatures(recentAndroidVersions[0].apiLevel)
////                Timber.d(versionDetail.toString())
////                Timber.d((System.currentTimeMillis() - startTime).toString())
////                list.add(versionDetail)
////            }
////            job1.join()
////            job2.join()
////            job3.join()
//
//            val job1 = async{
//                val versionDetail = mockApi().getAndroidVersionFeatures(recentAndroidVersions[0].apiLevel)
//                Timber.d(versionDetail.toString())
//                Timber.d((System.currentTimeMillis() - startTime).toString())
//                versionDetail
//            }
//            val job2 = async{
//                val versionDetail = mockApi().getAndroidVersionFeatures(recentAndroidVersions[0].apiLevel)
//                Timber.d(versionDetail.toString())
//                Timber.d((System.currentTimeMillis() - startTime).toString())
//                versionDetail
//            }
//            val job3 = async{
//                val versionDetail = mockApi().getAndroidVersionFeatures(recentAndroidVersions[0].apiLevel)
//                Timber.d(versionDetail.toString())
//                Timber.d((System.currentTimeMillis() - startTime).toString())
//                versionDetail
//            }
//            list.add(job1.await())
//            list.add(job2.await())
//            list.add(job3.await())
//
//            // or
//            awaitAll(job1, job2, job3)
//            // till await works, the parent coroutine will be suspended
//            uiState.value = UiState.Success(list)

            // Kotlin Syntax code
            viewModelScope.launch {
                try {
                    val recentAndroidVersions = mockApi.getRecentAndroidVersions()
                    val versionFeatures = recentAndroidVersions.map { androidVersion ->
                        async {
                            mockApi.getAndroidVersionFeatures(androidVersion.apiLevel)
                        }
                    }.awaitAll()

                    uiState.value = UiState.Success(versionFeatures)
                } catch (e: Exception){
                    uiState.value = UiState.Error("Network request failed")
                }
            }
//        }
    }
}