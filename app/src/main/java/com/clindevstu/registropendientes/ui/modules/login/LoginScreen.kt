package com.clindevstu.registropendientes.ui.modules.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.clindevstu.registropendientes.ui.components.CTextInput

@Composable
fun ScreenLogin (navController: NavHostController){
    val viewModel: LoginViewModel = hiltViewModel()
    LoginScreen(navController, viewModel)
}

@Composable
fun LoginScreen (navController: NavHostController, viewModel: LoginViewModel){

    val usuario by viewModel.usuario.collectAsState()
    val password by viewModel.password.collectAsState()
    val usuarioError by viewModel.usuarioError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Iniciar sesión",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        CTextInput(
            value = usuario ?: "",
            label = "Usuario",
            onValueChange = { viewModel.onUsuarioChange(it) },
            error = usuarioError,
            keyboardType = KeyboardType.Text
        )

        Spacer(modifier = Modifier.height(8.dp))

        CTextInput(
            value = password ?: "",
            label = "Contraseña",
            onValueChange = { viewModel.onPasswordChange(it) },
            error = passwordError,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.validarUsuarioError()
                viewModel.validarPasswordError()
                viewModel.login()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar")
        }

        when (state) {
            is LoginState.Error -> {
                Text(
                    text = (state as LoginState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            LoginState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }
            else -> {}
        }
    }
}
