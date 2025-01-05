package com.lukaslechner.coroutineusecasesonandroid.playground.utils.fundamentals

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

fun main() = runBlocking {
    println("main start")
    joinAll(
        async {
            threadSwitchingCoroutine(1, 500);
        },
        async {
            threadSwitchingCoroutine(2, 300)
        }
    )
    println("main ends")
}

suspend fun threadSwitchingCoroutine(number: Int, delay: Long) {
    println("Cotoutine $number has started on ${Thread.currentThread().name}")
    delay(delay)
//    withContext(Dispatchers.Default) {
        println("Coroutine $number has stopped on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine $number has final on ${Thread.currentThread().name}")
//    }
}