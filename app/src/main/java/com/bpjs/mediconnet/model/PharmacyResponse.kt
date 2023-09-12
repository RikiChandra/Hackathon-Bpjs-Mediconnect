package com.bpjs.mediconnet.model

import com.google.gson.annotations.SerializedName

data class PharmacyResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("foto")
	val foto: String,

	@field:SerializedName("review")
	val review: Int,

	@field:SerializedName("rating")
	val rating: Any,

	@field:SerializedName("lon")
	val lon: Any,

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("lat")
	val lat: Any,

	@field:SerializedName("alamat")
	val alamat: String
)
