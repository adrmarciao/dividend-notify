package br.com.adriano.statusinvest.data.response

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class EventsResponse(
    @SerializedName("loadStalker") val loadStalker: Boolean,
    @SerializedName("type") val type: Int,
    @SerializedName("loaded") val loaded: Boolean,
    @SerializedName("provents") val provents: List<ProventResponse>,
    )
data class ProventResponse(
    @SerializedName("resultAbsoluteValue") val resultAbsoluteValue: String,
    @SerializedName("dateCom") val dateCom: LocalDateTime,
    @SerializedName("paymentDividend") val paymentDividend: LocalDateTime?,
    @SerializedName("code") val code: String,
    @SerializedName("companyName") val companyName: String,
    @SerializedName("normalizedName") val normalizedName: String,
    @SerializedName("companyId") val companyId: Long,
    @SerializedName("type") val type: Int,
    @SerializedName("typeDesc") val typeDesc: String,
    @SerializedName("day") val day: Int,
    @SerializedName("date") val date: LocalDateTime,
    @SerializedName("url") val url: String,
    @SerializedName("categoryType") val categoryType: Int,


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProventResponse

        if (dateCom != other.dateCom) return false
        if (code != other.code) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dateCom.hashCode()
        result = 31 * result + code.hashCode()
        return result
    }
}