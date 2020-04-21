package com.andrewthom.gasbuddy

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StationsResponse(val key: String, val trendsKey: String, val count: Int, val nextCursor: String, val stations: List<Station>)

@Serializable
data class Station(val id: Int, @SerialName("item_type") val itemType: List<String> = emptyList(), val name: String, val alias: String,
				   @SerialName("brand_id") val brandId: Int, val address: Address, val phone: String, @SerialName("open_status") val openStatus: String,
				   val status: String, val timezone: String, @SerialName("star_rating") val starRating: Double = 0.0,
				   @SerialName("ratings_count") val ratingsCount: Int = 0, val amenities: List<String> = emptyList(), val latitude: Double,
				   val longitude: Double, val payStatus: PayStatus? = null, val brandings: List<Branding> = emptyList())

@Serializable
data class Address(@SerialName("line_1") val line1: String, @SerialName("line_2") val line2: String, val locality: String,
				   val region: String, val country: String, @SerialName("postal_code") val postalCode: String,
				   @SerialName("cross_street") val crossStreet: String, @SerialName("cross_street_alias") val crossStreetAlias: String,
				   @SerialName("address_alias") val addressAlias: String, @SerialName("at_intersection") val atIntersection: Boolean,
				   @SerialName("nearby_street") val nearbyStreet: String, val description: String)

@Serializable
data class PayStatus(@SerialName("is_pay_available") val isPayAvailable: Boolean)

@Serializable
data class Branding(@SerialName("brand_id") val brandId: Int, @SerialName("branding_type") val brandingType: String)

@Serializable
data class AmenitiesResponse(@SerialName("station_id") val stationId: Int, val amenities: List<Amenity>)

@Serializable
data class Amenity(val id: String, val imageURL: String, val displayName: String)

@Serializable
data class FuelsResponse(val stationIds: List<Int>, val fuels: List<Fuel>)

@Serializable
data class Fuel(val id: Int, val fuelType: String, val stationId: String, val prices: List<Price>)

@Serializable
data class Price(val isCash: Boolean, val price: String, val reportedBy: String = "", val postedTime: String = "")
