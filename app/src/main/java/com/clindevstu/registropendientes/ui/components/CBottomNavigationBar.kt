package com.clindevstu.registropendientes.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.clindevstu.registropendientes.ui.modules.panelcentral.BottomNavItem
import com.clindevstu.registropendientes.ui.theme.RegistroPendientesTheme

@Composable
fun CBottomNavigationBar(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    val items = listOf(
        BottomNavItem.Notificaciones,
        BottomNavItem.PanelPrincipal,
        BottomNavItem.Configuraciones
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary, // mejor coherencia con M3
        tonalElevation = 4.dp
    ) {
        items.forEach { item ->
            val isSelected = item == selectedItem

            NavigationBarItem(
                modifier = Modifier.padding(top = 16.dp),
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(35.dp),
                        tint = if (isSelected)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (isSelected)
                            MaterialTheme.colorScheme.onSurface
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                selected = isSelected,
                onClick = { onItemSelected(item) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewCBottomNavigationBar() {
    RegistroPendientesTheme(darkTheme = true) {
        var selectedItem by remember { mutableStateOf(BottomNavItem.PanelPrincipal) }

        CBottomNavigationBar(
            selectedItem = selectedItem,
            onItemSelected = {  }
        )
    }
}
