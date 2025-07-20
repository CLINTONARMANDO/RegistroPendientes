package com.clindevstu.registropendientes.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.clindevstu.registropendientes.core.models.responses.UsuarioResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

// Debe estar FUERA de la clase, en el mismo archivo o en uno separado
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences  @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        val NOMBRE = stringPreferencesKey("nombre")
        val ES_TECNICO = booleanPreferencesKey("esTecnico")
        val NOMBRE_TECNICO = stringPreferencesKey("nombreTecnico")
        val TOKEN = stringPreferencesKey("token")
    }
    suspend fun saveUser(usuario: UsuarioResponse) {
        context.dataStore.edit { prefs ->
            prefs[NOMBRE] = usuario.nombre ?: ""
            prefs[ES_TECNICO] = usuario.esTecnico ?: false
            prefs[NOMBRE_TECNICO] = usuario.nombreTecnico ?: ""
            prefs[TOKEN] = usuario.token ?: ""
        }
    }
    val userData: Flow<UsuarioResponse> = context.dataStore.data.map { prefs ->
        UsuarioResponse(
            nombre = prefs[NOMBRE],
            esTecnico = prefs[ES_TECNICO] ?: false,
            nombreTecnico = prefs[NOMBRE_TECNICO],
            token = prefs[TOKEN]
        )
    }
}
