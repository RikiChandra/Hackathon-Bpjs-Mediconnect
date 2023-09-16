package com.bpjs.mediconnet.model

import com.google.gson.annotations.SerializedName

data class MedicineResponse(

	@field:SerializedName("data")
	val data: List<Medicine>,

	@field:SerializedName("status")
	val status: String
)

data class Medicine(

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("foto")
	val foto: String,

	@field:SerializedName("dosis")
	val dosis: String,

	@field:SerializedName("efek")
	val efek: String,

	@field:SerializedName("_id")
	val id: String,

	@field:SerializedName("deskripsi")
	val deskripsi: String
)
