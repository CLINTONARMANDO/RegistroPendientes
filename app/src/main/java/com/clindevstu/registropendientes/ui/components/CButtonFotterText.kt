package com.clindevstu.registropendientes.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CButtonFotterText(
    onClick: () -> Unit,
    buttonText: String,
    footerText: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = buttonText)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = footerText,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CButtonFotterTextPreview() {
    MaterialTheme {
        Surface {
            CButtonFotterText(
                onClick = { /* Nada */ },
                buttonText = "Guardar",
                footerText = "Asegúrate de revisar antes de continuar",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}