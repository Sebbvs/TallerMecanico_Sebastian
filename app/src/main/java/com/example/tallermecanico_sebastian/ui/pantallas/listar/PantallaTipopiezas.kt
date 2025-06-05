package com.example.tallermecanico_sebastian.ui.pantallas.listar

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Tipopieza
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaCargando
import com.example.tallermecanico_sebastian.ui.pantallas.componentes.PantallaError
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.TipopiezaUIState

@Composable
fun PantallaTipopiezas(
    tipopiezaUIState: TipopiezaUIState,
    onTipopiezasObtenidos: () -> Unit,
    onTipopiezaEditar: (Tipopieza) -> Unit,
    onTipopiezaInsertar: () -> Unit,
    onAceptar: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (tipopiezaUIState) {
        is TipopiezaUIState.Cargando -> PantallaCargando(modifier = modifier.fillMaxSize())
        is TipopiezaUIState.Error -> PantallaError(modifier = modifier.fillMaxWidth())
        is TipopiezaUIState.ObtenerExito -> PantallaExitoTipopieza(
            lista = tipopiezaUIState.tipopieza,
            modifier = modifier.fillMaxWidth(),
            onTipopiezaEditar = onTipopiezaEditar,
            onTipopiezaInsertar = onTipopiezaInsertar,
            onAceptar = onAceptar
        )


        is TipopiezaUIState.CrearExito -> onTipopiezasObtenidos()
        is TipopiezaUIState.ActualizarExito -> onTipopiezasObtenidos()
        is TipopiezaUIState.EliminarExito -> onTipopiezasObtenidos()
    }
}

@Composable
fun PantallaExitoTipopieza(
    lista: List<Tipopieza>,
    modifier: Modifier,
    onTipopiezaEditar: (Tipopieza) -> Unit,
    onTipopiezaInsertar: () -> Unit,
    onAceptar: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp), // deja espacio para el botón
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(lista) { tipopieza ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(3.dp)
                        .border(
                            width = 1.dp, color = AzulPrincipal, shape = RoundedCornerShape(16.dp)
                        ),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "${tipopieza.nombre}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                onTipopiezaEditar(tipopieza)
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Create, contentDescription = "Editar"
                            )
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 32.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = onAceptar) {
                Text(stringResource(R.string.volver))
            }
        }

        Box(
            modifier.fillMaxSize()
        ) {
            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = { onTipopiezaInsertar() },
            ) {
                Icon(Icons.Filled.Add, "Insertar")
            }
        }

    }
}