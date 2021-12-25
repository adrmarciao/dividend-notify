package br.com.adriano.statusinvest.adapter

import com.google.gson.*
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeTypeAdapter : JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
    private const val BRAZIL_SIMPLE_FORMATS = "dd/MM/yyyy"
    private val dateFormatter: DateTimeFormatter = DateTimeFormatter
        .ofPattern(BRAZIL_SIMPLE_FORMATS)

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): LocalDateTime? {
        return try {
            LocalDate.parse(json?.asString, dateFormatter).atTime(0, 0)
        } catch (e: Exception) {
            null
        }
    }

    override fun serialize(
        src: LocalDateTime?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val dateFormatAsString: String = DateTimeFormatter.ofPattern(BRAZIL_SIMPLE_FORMATS).format(src)
        return JsonPrimitive(dateFormatAsString)
    }
}