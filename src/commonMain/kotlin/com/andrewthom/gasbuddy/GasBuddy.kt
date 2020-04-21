package com.andrewthom.gasbuddy

import kotlinx.serialization.KSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@UnstableDefault
object GasBuddy {
	private val json = Json(JsonConfiguration.Default.copy(ignoreUnknownKeys = true, isLenient = true))

	suspend fun getStations(forState: String): StationsResponse {
		val url = "https://www.gasbuddy.com/assets-v2/api/stations?countryCode=US&regionCode=$forState"
		return getAndParse(url, StationsResponse.serializer())
	}

	suspend fun getAmenities(forStationId: Int): AmenitiesResponse {
		val url = "https://www.gasbuddy.com/assets-v2/api/stations/$forStationId/amenities"
		return getAndParse(url, AmenitiesResponse.serializer())
	}

	suspend fun getFuels(forStationIds: List<Int>): FuelsResponse {
		val params = forStationIds.joinToString("&") {
			"stationIds=$it"
		}
		val url = "https://www.gasbuddy.com/assets-v2/api/fuels?$params"
		return getAndParse(url, FuelsResponse.serializer())
	}

	private suspend fun <T> getAndParse(url: String, serializer: KSerializer<T>): T {
		val jsonString = getJSONStringSuspend(url)
		return json.parse(serializer, jsonString)
	}
}
