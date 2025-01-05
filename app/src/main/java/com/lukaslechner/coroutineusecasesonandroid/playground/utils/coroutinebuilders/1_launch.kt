package com.lukaslechner.coroutineusecasesonandroid.playground.utils.coroutinebuilders

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        val job = launch {
            netwoekRequest()
            println("result received")
        }
        job.join()
        println("end of runBlocking")
    }
}

suspend fun netwoekRequest(): String {
    delay(500)
    return "ersult"
}