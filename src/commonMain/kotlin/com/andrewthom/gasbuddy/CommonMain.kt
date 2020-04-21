package com.andrewthom.gasbuddy

import kotlinx.coroutines.launch
import kotlinx.serialization.UnstableDefault

@UnstableDefault
suspend fun run() {
	val stations = GasBuddy.getStations("MN")
	println(stations)
	processStations(stations)
}

@UnstableDefault
suspend fun processStations(stationsResponse: StationsResponse) {
	val stationIds = stationsResponse.stations.map { it.id }
	val fuelsResponse = GasBuddy.getFuels(stationIds)
	for (fuel in fuelsResponse.fuels) {
		println(fuel)
	}
	for (station in stationsResponse.stations) {
		val amenitiesResponse = GasBuddy.getAmenities(station.id)
		println(amenitiesResponse.amenities)
	}
}
