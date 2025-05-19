package com.example.tallermecanico_sebastian.modelo

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Ruta<T : Any>(
    @StringRes val nombre: Int,
    val ruta: T,
    val iconoLleno: Int?,
    val iconoVacio: Int?
)