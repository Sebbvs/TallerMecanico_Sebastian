package com.example.tallermecanico_sebastian.ui.pantallas.detalle

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Rol
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaCargando
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaError
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.RolUIState

@Composable
fun PantallaDetalleRolPermiso(
    rolUIState: RolUIState, onAceptar: () -> Unit, modifier: Modifier = Modifier
) {
    when (rolUIState) {
        is RolUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is RolUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is RolUIState.ObtenerExito -> PantallaExitoRoles(
            lista = rolUIState.roles, modifier = modifier.fillMaxWidth(), onAceptar = onAceptar
        )

        is RolUIState.ActualizarExito -> TODO()
        is RolUIState.CrearExito -> TODO()
        is RolUIState.EliminarExito -> TODO()
    }
}

@Composable
fun PantallaExitoRoles(
    modifier: Modifier = Modifier,
    lista: List<Rol>,
    onAceptar: () -> Unit,
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp), // deja espacio para el botón
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(lista) { rol ->

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp)
                        .border(
                            width = 1.dp, color = AzulPrincipal, shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "${stringResource(R.string.texto_permisos)} ${stringResource(R.string.prep_de)} ${rol.nombre}",
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Center
                        )
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            rol.permisos.forEach { permiso ->
                                Text(
                                    text = permiso?.descripcion ?: "",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onAceptar) {
                Text(stringResource(R.string.volver))
            }
        }
    }
}