package com.bpjs.mediconnet.model

import com.google.gson.annotations.SerializedName

data class PharmacyDetailResponses(
    @SerializedName("data")
    val data: DataItem,

    @SerializedName("status")
    val status: String
)