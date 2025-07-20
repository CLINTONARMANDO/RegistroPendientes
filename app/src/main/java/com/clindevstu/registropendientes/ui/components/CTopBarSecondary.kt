package com.clindevstu.registropendientes.ui.components

import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.clindevstu.registropendientes.ui.theme.RegistroPendientesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CTopBarSecondary(
    title: String,
    subtitle: String? = null,
    descripcion: String? = null,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BackHandler {
        navController.popBackStack()
    }

    TopAppBar(
        title = {
            Column {
                Text(text = title, style = MaterialTheme.typography.displayMedium)
                subtitle?.let {
                    Text(text = it, style = MaterialTheme.typography.bodySmall)
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            navigationIconContentColor = MaterialTheme.colorScheme.onSurface
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCTopBarSecondary() {
    // NavController ficticio para preview (no hace navegación real)
    val navController = rememberNavController()
    RegistroPendientesTheme {
        CTopBarSecondary(
            title = "Mi Título",
            subtitle = "Mi Subtítulo",
            navController = navController
        )
    }
}
