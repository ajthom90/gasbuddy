package com.andrewthom.gasbuddy

import io.ktor.client.HttpClient
import io.ktor.client.engine.curl.Curl
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val httpClient = HttpClient(Curl)

actual suspend fun getJSONStringSuspend(forUrl: String): String {
	return httpClient.request(forUrl) {
		method = HttpMethod.Get
	}
}
