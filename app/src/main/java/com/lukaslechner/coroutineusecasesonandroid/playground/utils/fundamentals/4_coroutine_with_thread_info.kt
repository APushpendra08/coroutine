package com.lukaslechner.coroutineusecasesonandroid.playground.utils.fundamentals

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main start")
    joinAll(
        async {
            coroutine(1, 500);
        },
        async {
            coroutine(2, 300)
        }
    )
    println("main ends")
}

suspend fun coroutineThreadInfo(number: Int, delay: Long) {
    println("Cotoutine $number has started on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine $number has stopped on ${Thread.currentThread().name}")
}