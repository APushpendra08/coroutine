package com.lukaslechner.coroutineusecasesonandroid.playground.utils.fundamentals

import kotlin.concurrent.thread

fun main() {
    println("Main starts")
    threadRoutined(1, 500)
    threadRoutined(2, 300)
    Thread.sleep(1000)
    println("Main ends")

}

fun threadRoutined(number: Int, delay: Long){
    thread {
        println("Routine $number starts work")
        Thread.sleep(delay)
        println("Routine $number has finished")
    }
}