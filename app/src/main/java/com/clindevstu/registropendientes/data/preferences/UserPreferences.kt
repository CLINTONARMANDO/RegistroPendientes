package com.clindevstu.registropendientes.data.preferences

import android.content.Context

class UserPreferences(context: Context) {

    private val dataStore = context.createDataStore(name = "user_prefs")

    companion object {
        val NOMBRE = stringPreferencesKey("nombre")
        val ES_TECNICO = booleanPreferencesKey("esTecnico")
        val NOMBRE_TECNICO = stringPreferencesKey("nombreTecnico")
        val TOKEN = stringPreferencesKey("token")
    }

    suspend fun saveUser(usuario: UsuarioResponse) {
        dataStore.edit { prefs ->
            prefs[NOMBRE] = usuario.nombre ?: ""
            prefs[ES_TECNICO] = usuario.esTecnico == 1
            prefs[NOMBRE_TECNICO] = usuario.nombreTecnico ?: ""
            prefs[TOKEN] = usuario.token ?: ""
        }
    }

    val userData: Flow<UsuarioResponse> = dataStore.data.map { prefs ->
        UsuarioResponse(
            nombre = prefs[NOMBRE],
            esTecnico = if (prefs[ES_TECNICO] == true) 1 else 0,
            nombreTecnico = prefs[NOMBRE_TECNICO],
            token = prefs[TOKEN]
        )
    }
}
