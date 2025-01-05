package com.lukaslechner.coroutineusecasesonandroid.playground.utils.fundamentals

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main starts")
    joinAll(
        async { delayedDemonstration(1, 500) },
        async { delayedDemonstration(2, 300) }
    )
    println("Main ends")

}

suspend fun delayedDemonstration(number: Int, delay: Long) {
    println("Cotoutine $number has started")
    delay(delay)
    println("Coroutine $number has stopped")

    // how it works internally for line 20 and 21
//    Handler(Looper.getMainLooper())
//        .postDelayed({
//            println("Coroutine $number has finished")
//        }, delay)
}