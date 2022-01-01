package br.com.adriano.statusinvest.data.response

import com.google.gson.annotations.SerializedName

data class AdvancedSearchResponse(
    @SerializedName("companyId")
    val companyId: Long,
    @SerializedName("companyName")
    val companyName: String,
    @SerializedName("ticker")
    val ticker: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("p_L")
    val pL: Double,
    @SerializedName("dy")
    val dy: Double,
    @SerializedName("p_VP")
    val pVP: Double,
    @SerializedName("p_Ebit")
    val pEbit: Double,
    @SerializedName("p_Ativo")
    val pAtivo: Double,
    @SerializedName("eV_Ebit")
    val eVEbit: Double,
    @SerializedName("margemBruta")
    val margemBruta: Double,
    @SerializedName("margemEbit")
    val margemEbit: Double,
    @SerializedName("margemLiquida")
    val margemLiquida: Double,
    @SerializedName("p_SR")
    val pSR: Double,
    @SerializedName("p_CapitalGiro")
    val pCapitalGiro: Double,
    @SerializedName("pAtivoCirculante")
    val pAtivoCirculante: Double,
    @SerializedName("giroAtivos")
    val giroAtivos: Double,
    @SerializedName("roe")
    val roe: Double,
    @SerializedName("roa")
    val roa: Double,
    @SerializedName("roic")
    val roic: Double,
    @SerializedName("dividaliquidaPatrimonioLiquido")
    val dividaliquidaPatrimonioLiquido: Double,
    @SerializedName("dividaLiquidaEbit")
    val dividaLiquidaEbit: Double,
    @SerializedName("pl_Ativo")
    val plAtivo: Double,
    @SerializedName("passivo_Ativo")
    val passivoAtivo: Double,
    @SerializedName("liquidezCorrente")
    val liquidezCorrente: Double,
    @SerializedName("peg_Ratio")
    val peg_Ratio: Double,
    @SerializedName("receitas_Cagr5")
    val receitasCagr5: Double,
    @SerializedName("lucros_Cagr5")
    val lucrosCagr5: Double,
    @SerializedName("liquidezMediaDiaria")
    val liquidezMediaDiaria: Double,
    @SerializedName("vpa")
    val vpa: Double,
    @SerializedName("lpa")
    val lpa: Double,
    @SerializedName("valorMercado")
    val valorMercado: Double,
    var fairPriceBG: Double? = null,
    var margemBG: Double? = null
)
