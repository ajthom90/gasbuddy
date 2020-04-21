package com.andrewthom.gasbuddy

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.UnstableDefault

@UnstableDefault
fun main(): Unit = runBlocking {
	withContext(GlobalScope.coroutineContext) {
		coroutineScope {
			run()
		}
	}
}
