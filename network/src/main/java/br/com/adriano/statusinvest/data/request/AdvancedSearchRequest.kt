package br.com.adriano.statusinvest.data.request

import com.google.gson.annotations.SerializedName

data class AdvancedSearchRequest(
    @SerializedName("Sector")
    val sector: String = "",
    @SerializedName("SubSector")
    val subSector: String = "",
    @SerializedName("Segment")
    val segment: String = "",
    @SerializedName("my_range")
    val myRange: String = "-20;100",
    @SerializedName("p_L")
    val pl: IndicatorRequest
)

data class IndicatorRequest(
    @SerializedName("Item1")
    val item1: Double? = null,
    @SerializedName("Item2")
    val item2: Double? = null
)
