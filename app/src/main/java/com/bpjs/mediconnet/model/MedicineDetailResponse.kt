package com.bpjs.mediconnet.model

import com.google.gson.annotations.SerializedName

data class MedicineDetailResponse(
    @field:SerializedName("data")
    val data: Medicine,

    @field:SerializedName("status")
    val status: String
)
