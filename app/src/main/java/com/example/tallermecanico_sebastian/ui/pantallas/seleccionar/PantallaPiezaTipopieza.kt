package com.example.tallermecanico_sebastian.ui.pantallas.seleccionar

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.viewmodel.PiezaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.TipopiezaViewModel

@Composable
fun PantallaPiezaTipopieza(
    viewModelPieza: PiezaViewModel,
    viewModelTipopieza: TipopiezaViewModel,
    onSeleccionar: () -> Unit
) {
    val lista = viewModelTipopieza.listaPiezaTipopieza

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(lista) { tipopieza ->
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(3.dp)
                    .border(
                        width = 1.dp, color = AzulPrincipal, shape = RoundedCornerShape(16.dp)
                    )
                    .clickable {
                        viewModelPieza.seleccionarTipopieza(tipopieza)
                        onSeleccionar()
                    },
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier.padding(start = 9.dp, top = 3.dp, bottom = 3.dp)
                ) {
                    Text(
                        text = "${tipopieza.nombre}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}