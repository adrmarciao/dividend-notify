package br.com.adriano.statusinvest.data.response

import com.google.gson.annotations.SerializedName

data class PayoutResultResponse(val chart: PayoutResultChart)
data class PayoutResultChart(
    @SerializedName("category")
    val categories: List<String>,
    @SerializedName("series")
    val series: PayoutSeriesResult
)

data class PayoutSeriesResult(
    @SerializedName("lucroLiquido")
    val lucroLiquido: List<PayoutSeriesItemResult>
)

data class PayoutSeriesItemResult(
    @SerializedName("value")
    val value: Double,
    @SerializedName("value_F")
    val valueF: String,
    @SerializedName("valueSmall_F")
    val valueSmallF: String
)