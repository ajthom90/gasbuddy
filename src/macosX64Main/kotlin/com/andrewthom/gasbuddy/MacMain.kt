package com.andrewthom.gasbuddy

import kotlinx.coroutines.*
import kotlinx.serialization.UnstableDefault

@UnstableDefault
fun main(): Unit = runBlocking {
	withContext(GlobalScope.coroutineContext) {
		coroutineScope {
			run()
		}
	}
}
