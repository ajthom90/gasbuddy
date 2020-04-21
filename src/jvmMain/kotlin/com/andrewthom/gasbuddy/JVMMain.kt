package com.andrewthom.gasbuddy

import kotlinx.coroutines.*
import kotlinx.serialization.UnstableDefault

@UnstableDefault
fun main() = runBlocking {
	withContext(MainScope().coroutineContext) {
		coroutineScope {
			run()
		}
	}
}
