package com.clindevstu.registropendientes.common

import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class UsuarioResponseAdapter : JsonDeserializer<UsuarioResponse?> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): UsuarioResponse? {
        return when {
            json.isJsonObject -> {
                context.deserialize<UsuarioResponse>(json.asJsonObject, UsuarioResponse::class.java)
            }
            json.isJsonArray -> {
                val array = json.asJsonArray
                if (array.size() > 0) {
                    context.deserialize<UsuarioResponse>(array[0], UsuarioResponse::class.java)
                } else {
                    null
                }
            }
            else -> null
        }
    }
}
