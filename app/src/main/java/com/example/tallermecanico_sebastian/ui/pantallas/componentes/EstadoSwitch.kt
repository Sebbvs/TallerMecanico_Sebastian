package com.example.tallermecanico_sebastian.ui.pantallas.componentes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.ui.theme.Blanco
import com.example.tallermecanico_sebastian.ui.theme.NegroCarbon
import com.example.tallermecanico_sebastian.ui.theme.Rojo
import com.example.tallermecanico_sebastian.ui.theme.Verde

@Composable
fun EstadoSwitch(
    estado: Boolean, onEstadoChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.width(110.dp)) {
            Text(
                text = if (estado) "Reparado" else "No reparado",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Box(modifier = Modifier.width(110.dp)) {
            Switch(checked = estado, onCheckedChange = onEstadoChange, thumbContent = if (estado) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = NegroCarbon
                    )
                }
            } else {
                {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = NegroCarbon
                    )
                }
            }, colors = SwitchDefaults.colors(
                checkedThumbColor = Blanco,
                checkedTrackColor = Verde,
                uncheckedThumbColor = Blanco,
                uncheckedTrackColor = Rojo
            ))
        }
    }
}