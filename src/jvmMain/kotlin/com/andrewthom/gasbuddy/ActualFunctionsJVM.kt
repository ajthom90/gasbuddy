package com.andrewthom.gasbuddy

import kong.unirest.Unirest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlin.system.exitProcess

actual suspend fun getJSONStringSuspend(forUrl: String): String {
	return Unirest.get(forUrl).asString().body
}
