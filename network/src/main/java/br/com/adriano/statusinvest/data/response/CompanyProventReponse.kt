package br.com.adriano.statusinvest.data.response

import com.google.gson.annotations.SerializedName

data class CompanyProventReponse(
    @SerializedName("assetEarningsYearlyModels")
    val assetEarningsYearlyModels: List<CompanyProventByYearResponse>
)

data class CompanyProventByYearResponse(
    @SerializedName("rank") val year: Long,
    @SerializedName("value") val value: Double
)