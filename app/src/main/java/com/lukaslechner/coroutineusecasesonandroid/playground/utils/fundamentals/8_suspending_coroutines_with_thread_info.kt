package com.lukaslechner.coroutineusecasesonandroid.playground.utils.fundamentals

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("main start")
    joinAll(
        async { suspendingCoroutine(1, 500); },
        async { suspendingCoroutine(2, 300) },
        async {
            repeat(5) {
                println("other task is working on ${Thread.currentThread().name}")
                delay(100)
            }
        }
    )
    println("main ends")
}

suspend fun suspendingCoroutine(number: Int, delay: Long) {
    println("Cotoutine $number has started on ${Thread.currentThread().name}")
    delay(delay)
    println("Coroutine $number has stopped on ${Thread.currentThread().name}")
}