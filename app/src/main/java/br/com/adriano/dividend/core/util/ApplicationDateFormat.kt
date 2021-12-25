package br.com.adriano.dividend.core.util

import java.time.format.DateTimeFormatter

object ApplicationDateFormat {
    val US_SIMPLE_DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val BRAZIL_SIMPLE_DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
}