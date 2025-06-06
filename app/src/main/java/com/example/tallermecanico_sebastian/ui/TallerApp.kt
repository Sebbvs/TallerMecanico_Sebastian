package com.example.tallermecanico_sebastian.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.tallermecanico_sebastian.R
import com.example.tallermecanico_sebastian.modelo.Ruta
import com.example.tallermecanico_sebastian.ui.pantallas.filtros.PantallaBuscarPorMatricula
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaCambiarContrasenya
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirAveria
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirCliente
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirCoche
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirEmpleado
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaAverias
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaClientes
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaCoches
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleAveria
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleCliente
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleCoche
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleEmpleado
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarAverias
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarClientes
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarCoches
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarEmpleados
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaEmpleados
import com.example.tallermecanico_sebastian.ui.pantallas.PantallaLogin
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetallePieza
import com.example.tallermecanico_sebastian.ui.pantallas.detalle.PantallaDetalleRolPermiso
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirPieza
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirTipoaveria
import com.example.tallermecanico_sebastian.ui.pantallas.insertar.PantallaAnyadirTipopieza
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaPiezas
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaTipoaverias
import com.example.tallermecanico_sebastian.ui.pantallas.listar.PantallaTipopiezas
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarPiezas
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarTipoaverias
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaEditarTipopiezas
import com.example.tallermecanico_sebastian.ui.pantallas.modificar.PantallaMiPerfil
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaAveriaAveriapiezas
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaAveriaCliente
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaAveriaEmpleado
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaAveriaTipoaverias
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaAveriaVehiculo
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaEmpleadoRol
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaPiezaTipopieza
import com.example.tallermecanico_sebastian.ui.pantallas.seleccionar.PantallaVehiculoCliente
import com.example.tallermecanico_sebastian.ui.theme.AzulPrincipal
import com.example.tallermecanico_sebastian.ui.theme.Blanco
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriapiezaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.AveriatipoaveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.ClienteViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.EmpleadoViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.PiezaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.RolViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.TipoaveriaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.TipopiezaViewModel
import com.example.tallermecanico_sebastian.ui.viewmodel.VehiculoViewModel

enum class Pantallas(@StringRes val titulo: Int) {
    //LOGIN
    Login(titulo = R.string.pantalla_login),

    //LISTAS
    Averias(titulo = R.string.pantalla_averias),
    Coches(titulo = R.string.pantalla_coches),
    Clientes(titulo = R.string.pantalla_clientes),
    Empleados(titulo = R.string.pantalla_empleados),
    Piezas(titulo = R.string.pantalla_piezas),
    Facturas(titulo = R.string.pantalla_facturas),
    Tipopiezas(titulo = R.string.pantalla_tipopiezas),
    Tipoaverias(titulo = R.string.pantalla_tipoaverias),

    //FILTRO MATRICULA
    BuscarMatricula(titulo = R.string.pantalla_buscar),

    //EDITAR
    EditarAverias(titulo = R.string.pantalla_editar_averias),
    EditarCoches(titulo = R.string.pantalla_editar_coches),
    EditarClientes(titulo = R.string.pantalla_editar_clientes),
    EditarEmpleados(titulo = R.string.pantalla_editar_empleados),
    EditarPiezas(titulo = R.string.pantalla_editar_piezas),
    EditarTipopiezas(titulo = R.string.pantalla_editar_tipopiezas),
    EditarTipoaverias(titulo = R.string.pantalla_editar_tipoaverias),

    //INSERTAR
    AnyadirAveria(titulo = R.string.pantalla_anyadir_averia),
    AnyadirCliente(titulo = R.string.pantalla_anyadir_cliente),
    AnyadirCoche(titulo = R.string.pantalla_anyadir_coche),
    AnyadirEmpleado(titulo = R.string.pantalla_anyadir_empleado),
    AnyadirPieza(titulo = R.string.pantalla_anyadir_pieza),
    AnyadirTipopieza(titulo = R.string.pantalla_anyadir_tipopieza),
    AnyadirTipoaveria(titulo = R.string.pantalla_anyadir_tipoaveria),

    //MOSTRAR
    DetalleAveria(titulo = R.string.pantalla_detalle_averia),
    DetalleCliente(titulo = R.string.pantalla_detalle_cliente),
    DetalleCoche(titulo = R.string.pantalla_detalle_coche),
    DetalleEmpleado(titulo = R.string.pantalla_detalle_empleado),
    DetallePieza(titulo = R.string.pantalla_detalle_pieza),
    InfoRol(titulo = R.string.pantalla_info_rol),

    //EDITAR PASS
    CambioContrasenya(titulo = R.string.pantalla_cambio_contrasenya),
    MiPerfil(titulo = R.string.texto_miperfil),

    //SELECCIONADORES
    SeleccionarVehiculoCliente(titulo = R.string.seleccionar_vehiculo_cliente),
    SeleccionarEmpleadoRol(titulo = R.string.seleccionar_empleado_rol),
    SeleccionarAveriaCliente(titulo = R.string.seleccionar_averia_cliente),
    SeleccionarAveriaEmpleado(titulo = R.string.seleccionar_averia_empleado),
    SeleccionarAveriaVehiculo(titulo = R.string.seleccionar_averia_vehiculo),
    SeleccionarPiezaTipopieza(titulo = R.string.seleccionar_pieza_tipopieza),
    SeleccionarAveriaTipoaveria(titulo = R.string.seleccionar_averia_tipoaveria),
    SeleccionarAveriaAveriapieza(titulo = R.string.seleccionar_averia_averiapieza),


}

val listaRutas = listOf(
    Ruta(
        Pantallas.Averias.titulo,
        Pantallas.Averias.name,
        R.drawable.herramientas,
        R.drawable.herramientas,
    ),
    Ruta(
        Pantallas.Coches.titulo,
        Pantallas.Coches.name,
        R.drawable.vehiculo,
        R.drawable.vehiculo,
    ),
    Ruta(
        Pantallas.Clientes.titulo,
        Pantallas.Clientes.name,
        R.drawable.cliente40,
        R.drawable.cliente40,
    ),
    Ruta(
        Pantallas.Piezas.titulo,
        Pantallas.Piezas.name,
        R.drawable.pieza,
        R.drawable.pieza,
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TallerApp(
    navController: NavHostController = rememberNavController(),
    viewModelAveria: AveriaViewModel = viewModel(factory = AveriaViewModel.Factory),
    viewModelCliente: ClienteViewModel = viewModel(factory = ClienteViewModel.Factory),
    viewModelEmpleado: EmpleadoViewModel = viewModel(factory = EmpleadoViewModel.Factory),
    viewModelRol: RolViewModel = viewModel(factory = RolViewModel.Factory),
    viewModelPieza: PiezaViewModel = viewModel(factory = PiezaViewModel.Factory),
    viewModelVehiculo: VehiculoViewModel = viewModel(factory = VehiculoViewModel.Factory),
    viewModelTipopieza: TipopiezaViewModel = viewModel(factory = TipopiezaViewModel.Factory),
    viewModelTipoaveria: TipoaveriaViewModel = viewModel(factory = TipoaveriaViewModel.Factory),
    viewModelAveriapieza: AveriapiezaViewModel = viewModel(factory = AveriapiezaViewModel.Factory),
    viewModelAveriatipoaveria: AveriatipoaveriaViewModel = viewModel(factory = AveriatipoaveriaViewModel.Factory),
) {
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Login.name
    )

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    //ESTADOS
    val averiaUIState = viewModelAveria.averiaUIState
    val clienteUIState = viewModelCliente.clienteUIState
    val empleadoUIState = viewModelEmpleado.empleadoUIState
    val vehiculoUIState = viewModelVehiculo.vehiculoUIState
    val piezaUIState = viewModelPieza.piezaUIState
    val rolUIState = viewModelRol.rolUIState
    val tipopiezaUIState = viewModelTipopieza.tipopiezaUIState
    val tipoaveriaUIState = viewModelTipoaveria.tipoaveriaUIState
    val averiatipoaveriaUIState = viewModelAveriatipoaveria.averiatipoaveriaUIState

    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(topBar = {
        AppTopBar(
            pantallaActual = pantallaActual,
            navController = navController,
            scrollBehavior = scrollBehavior,
        )
    }, bottomBar = {
        if (pantallaActual == Pantallas.Averias || pantallaActual == Pantallas.Coches || pantallaActual == Pantallas.Clientes || pantallaActual == Pantallas.Piezas || pantallaActual == Pantallas.Facturas) {
            NavigationBar {
                listaRutas.forEachIndexed { index, ruta ->
                    NavigationBarItem(icon = {
                        if (selectedItem == index) Image(
                            painter = painterResource(id = ruta.iconoLleno!!),
                            contentDescription = stringResource(id = ruta.nombre),
                            modifier = Modifier.size(18.dp)
                        )
                        else Image(
                            painter = painterResource(id = ruta.iconoVacio!!),
                            contentDescription = stringResource(id = ruta.nombre),
                            modifier = Modifier.size(18.dp)
                        )
                    },
                        label = { Text(text = stringResource(id = ruta.nombre)) },
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            navController.navigate(ruta.ruta)
                        })
                }
            }
        }
    },
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Pantallas.Login.name,
            modifier = Modifier.padding(innerPadding)
        ) {
//            GRAFO DE LAS RUTAS
            composable(route = Pantallas.Login.name) {
                PantallaLogin(
                    modifier = Modifier.fillMaxSize(),
                    onAutenticar = { usuario, contrasenya ->
                        viewModelEmpleado.autenticarUsuario(usuario, contrasenya)
                    },
                    onBuscar = {
                        navController.navigate(Pantallas.BuscarMatricula.name)
                    },
                    empleadoUIState = empleadoUIState,
                    navController = navController,
                )
            }
            //LISTAS
            composable(route = Pantallas.Averias.name) {
                PantallaAverias(
                    modifier = Modifier,
                    averiaUIState = averiaUIState,
                    onAveriasObtenidos = { viewModelAveria.obtenerAverias() },
                    onAveriaClick = {
                        viewModelAveria.actualizarAveriaPulsado(it)
                        navController.navigate(Pantallas.DetalleAveria.name)
                    },
                    onAveriaEditar = {
                        viewModelAveria.seleccionarProvisional(it)
                        it.cliente?.let { it1 -> viewModelAveria.seleccionarCliente(it1) }
                        it.empleado?.let { it1 -> viewModelAveria.seleccionarEmpleado(it1) }
                        it.vehiculo?.let { it1 -> viewModelAveria.seleccionarVehiculo(it1) }
                        it.tipo_averias?.forEach { it1 ->
                            viewModelAveria.seleccionarTipoaveria(
                                it1,
                                true
                            )
                        }
                        it.averia_piezas?.let { it1 -> viewModelAveria.seleccionarAveriapiezas(it1) }
                        viewModelAveria.actualizarAveriaPulsado(it)
                        navController.navigate(Pantallas.EditarAverias.name)
                    },
                    onAveriaInsertar = { navController.navigate(Pantallas.AnyadirAveria.name) },
                )
            }
            composable(route = Pantallas.Coches.name) {
                PantallaCoches(
                    modifier = Modifier,
                    vehiculoUIState = vehiculoUIState,
                    onVehiculosObtenidos = { viewModelVehiculo.obtenerVehiculos() },
                    onVehiculoClick = {
                        viewModelVehiculo.actualizarVehiculoPulsado(it)
                        navController.navigate(Pantallas.DetalleCoche.name)
                    },
                    onVehiculoEditar = {
                        viewModelVehiculo.seleccionarProvisional(it)
                        viewModelVehiculo.seleccionarCliente(it.cliente!!)
                        viewModelVehiculo.actualizarVehiculoPulsado(it)
                        navController.navigate(Pantallas.EditarCoches.name)
                    },
                    onVehiculoInsertar = { navController.navigate(Pantallas.AnyadirCoche.name) },
                )
            }
            composable(route = Pantallas.Clientes.name) {
                PantallaClientes(
                    modifier = Modifier,
                    clienteUIState = clienteUIState,
                    onClientesObtenidos = { viewModelCliente.obtenerClientes() },
                    onClienteClick = {
                        viewModelCliente.actualizarClientePulsado(it)
                        navController.navigate(Pantallas.DetalleCliente.name)
                    },
                    onClienteEditar = {
                        viewModelCliente.actualizarClientePulsado(it)
                        navController.navigate(Pantallas.EditarClientes.name)
                    },
                    onClienteInsertar = { navController.navigate(Pantallas.AnyadirCliente.name) },
                )
            }
            composable(route = Pantallas.Empleados.name) {
                PantallaEmpleados(
                    modifier = Modifier,
                    empleadoUIState = empleadoUIState,
                    onEmpleadosObtenidos = { viewModelEmpleado.obtenerEmpleados() },
                    onEmpleadoClick = {
                        viewModelEmpleado.actualizarEmpleadoPulsado(it)
                        navController.navigate(Pantallas.DetalleEmpleado.name)
                    },
                    onEmpleadoEditar = {
                        viewModelEmpleado.seleccionarProvisional(it)
                        viewModelEmpleado.seleccionarRol(it.rol!!)
                        viewModelEmpleado.actualizarEmpleadoPulsado(it)
                        navController.navigate(Pantallas.EditarEmpleados.name)
                    },
                    onEmpleadoInsertar = { navController.navigate(Pantallas.AnyadirEmpleado.name) },
                    onEmpleadoContrasenya = {
                        viewModelEmpleado.actualizarEmpleadoPulsado(it)
                        navController.navigate(Pantallas.CambioContrasenya.name)
                    },
                    onAceptar = {
                        navController.navigate(Pantallas.Averias.name) {
                            popUpTo(Pantallas.Averias.name) { inclusive = false }
                        }
                    },
                )
            }
            composable(route = Pantallas.Piezas.name) {
                PantallaPiezas(
                    modifier = Modifier,
                    piezaUIState = piezaUIState,
                    onPiezasObtenidos = { viewModelPieza.obtenerPiezas() },
                    onPiezaClick = {
                        viewModelPieza.actualizarPiezaPulsado(it)
                        navController.navigate(Pantallas.DetallePieza.name)
                    },
                    onPiezaEditar = {
                        viewModelPieza.seleccionarProvisional(it)
                        viewModelPieza.seleccionarTipopieza(it.tipo_pieza!!)
                        viewModelPieza.actualizarPiezaPulsado(it)
                        navController.navigate(Pantallas.EditarPiezas.name)
                    },
                    onPiezaInsertar = {
                        navController.navigate(Pantallas.AnyadirPieza.name)
                    },
                )
            }
            composable(route = Pantallas.Tipopiezas.name) {
                PantallaTipopiezas(
                    modifier = Modifier,
                    tipopiezaUIState = tipopiezaUIState,
                    onTipopiezasObtenidos = { viewModelTipopieza.obtenerTipospieza() },
                    onTipopiezaEditar = {
                        viewModelTipopieza.actualizarTipopiezaPulsado(it)
                        navController.navigate(Pantallas.EditarTipopiezas.name)
                    },
                    onTipopiezaInsertar = { navController.navigate(Pantallas.AnyadirTipopieza.name) },
                    onAceptar = {
                        navController.navigate(Pantallas.Averias.name) {
                            popUpTo(Pantallas.Averias.name) { inclusive = false }
                        }
                    },
                )
            }
            composable(route = Pantallas.Tipoaverias.name) {
                PantallaTipoaverias(
                    modifier = Modifier,
                    tipoaveriaUIState = tipoaveriaUIState,
                    onTipoaveriasObtenidos = { viewModelTipoaveria.obtenerTipoaveria() },
                    onTipoaveriaEditar = {
                        viewModelTipoaveria.actualizarTipoaveriaPulsado(it)
                        navController.navigate(Pantallas.EditarTipoaverias.name)
                    },
                    onTipoaveriaInsertar = { navController.navigate(Pantallas.AnyadirTipoaveria.name) },
                    onAceptar = {
                        navController.navigate(Pantallas.Averias.name) {
                            popUpTo(Pantallas.Averias.name) { inclusive = false }
                        }
                    },
                )
            }

            //INSERTS
            composable(route = Pantallas.AnyadirAveria.name) {
                PantallaAnyadirAveria(
                    modifier = Modifier,
                    viewModel = viewModelAveria,
                    onInsertar = { averia ->
                        viewModelAveria.insertarAveria(averia)
                        viewModelAveria.limpiarFormularioAveria()
                        navController.popBackStack()
                    },
                    onCancelar = {
                        viewModelAveria.limpiarFormularioAveria()
                        navController.popBackStack()
                    },
                    onSeleccionarCliente = { navController.navigate(Pantallas.SeleccionarAveriaCliente.name) },
                    onSeleccionarEmpleado = { navController.navigate(Pantallas.SeleccionarAveriaEmpleado.name) },
                    onSeleccionarVehiculo = { navController.navigate(Pantallas.SeleccionarAveriaVehiculo.name) },
                )
            }
            composable(route = Pantallas.AnyadirCoche.name) {
                PantallaAnyadirCoche(
                    modifier = Modifier,
                    viewModel = viewModelVehiculo,
                    onInsertar = { vehiculo ->
                        viewModelVehiculo.insertarVehiculo(vehiculo)
                        viewModelVehiculo.limpiarFormularioVehiculo()
                        navController.popBackStack()
                    },
                    onCancelar = {
                        viewModelVehiculo.limpiarFormularioVehiculo()
                        navController.popBackStack()
                    },
                    onSeleccionarCliente = { navController.navigate(Pantallas.SeleccionarVehiculoCliente.name) },
                )
            }
            composable(route = Pantallas.AnyadirCliente.name) {
                PantallaAnyadirCliente(
                    modifier = Modifier,
                    onInsertar = { cliente ->
                        viewModelCliente.insertarCliente(cliente)
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() },
                )
            }
            composable(route = Pantallas.AnyadirEmpleado.name) {
                PantallaAnyadirEmpleado(
                    modifier = Modifier,
                    viewModel = viewModelEmpleado,
                    onInsertar = { empleado ->
                        viewModelEmpleado.insertarEmpleado(empleado)
                        viewModelEmpleado.limpiarFormularioEmpleado()
                        navController.popBackStack()
                    },
                    onCancelar = {
                        viewModelEmpleado.limpiarFormularioEmpleado()
                        navController.popBackStack()
                    },
                    onSeleccionarRol = {
                        navController.navigate(Pantallas.SeleccionarEmpleadoRol.name)
                    },
                )
            }
            composable(route = Pantallas.AnyadirPieza.name) {
                PantallaAnyadirPieza(
                    modifier = Modifier,
                    viewModel = viewModelPieza,
                    onInsertar = { pieza ->
                        viewModelPieza.insertarPieza(pieza)
                        navController.popBackStack()
                    },
                    onCancelar = {
                        viewModelPieza.limpiarFormularioPieza()
                        navController.popBackStack()
                    },
                    onSeleccionarTipopieza = { navController.navigate(Pantallas.SeleccionarPiezaTipopieza.name) },
                )
            }
            composable(route = Pantallas.AnyadirTipopieza.name) {
                PantallaAnyadirTipopieza(
                    modifier = Modifier,
                    onInsertar = { tipopieza ->
                        viewModelTipopieza.insertarTipopieza(tipopieza)
                    },
                    onCancelar = { navController.popBackStack() }
                )
            }
            composable(route = Pantallas.AnyadirTipoaveria.name) {
                PantallaAnyadirTipoaveria(
                    modifier = Modifier,
                    onInsertar = { tipoaveria ->
                        viewModelTipoaveria.insertarTipoaveria(tipoaveria)
                    },
                    onCancelar = { navController.popBackStack() }
                )
            }
            //DETALLE (INFO)
            composable(route = Pantallas.DetalleAveria.name) {
                PantallaDetalleAveria(
                    modifier = Modifier,
                    averia = viewModelAveria.averiaPulsado,
                    onAceptar = { navController.popBackStack() },
                )
            }
            composable(route = Pantallas.DetalleCoche.name) {
                PantallaDetalleCoche(
                    modifier = Modifier,
                    vehiculo = viewModelVehiculo.vehiculoPulsado,
                    onAceptar = { navController.popBackStack() },
                )
            }
            composable(route = Pantallas.DetalleCliente.name) {
                PantallaDetalleCliente(
                    modifier = Modifier,
                    cliente = viewModelCliente.clientePulsado,
                    onAceptar = { navController.popBackStack() },
                )
            }
            composable(route = Pantallas.DetalleEmpleado.name) {
                PantallaDetalleEmpleado(
                    modifier = Modifier,
                    empleado = viewModelEmpleado.empleadoPulsado,
                    onAceptar = { navController.popBackStack() },
                )
            }
            composable(route = Pantallas.DetallePieza.name) {
                PantallaDetallePieza(
                    modifier = Modifier,
                    pieza = viewModelPieza.piezaPulsado,
                    onAceptar = { navController.popBackStack() },
                )
            }
            //EDITAR Y BORRAR
            composable(route = Pantallas.EditarAverias.name) {
                PantallaEditarAverias(
                    modifier = Modifier,
                    viewModel = viewModelAveria,
                    onCancelar = {
                        viewModelAveria.limpiarFormularioAveria()
                        navController.popBackStack()
                    },
                    onGuardar = {
                        it.cod_averia?.let { it1 -> viewModelAveria.actualizarAveria(it1, it) }
                        viewModelAveria.limpiarFormularioAveria()
                        navController.popBackStack()
                    },
                    onSeleccionarCliente = { navController.navigate(Pantallas.SeleccionarAveriaCliente.name) },
                    onSeleccionarEmpleado = { navController.navigate(Pantallas.SeleccionarAveriaEmpleado.name) },
                    onSeleccionarVehiculo = { navController.navigate(Pantallas.SeleccionarAveriaVehiculo.name) },
                    onSeleccionarTipoaverias = { navController.navigate(Pantallas.SeleccionarAveriaTipoaveria.name) },
                    onSeleccionarAveriapiezas = { navController.navigate(Pantallas.SeleccionarAveriaAveriapieza.name) },
                )
            }
            composable(route = Pantallas.EditarCoches.name) {
                PantallaEditarCoches(
                    modifier = Modifier,
                    viewModel = viewModelVehiculo,
                    vehiculo = viewModelVehiculo.vehiculoPulsado,
                    onCancelar = {
                        viewModelVehiculo.limpiarFormularioVehiculo()
                        navController.popBackStack()
                    },
                    onGuardar = {
                        it.cod_vehiculo?.let { it1 ->
                            viewModelVehiculo.actualizarVehiculo(
                                it1,
                                it
                            )
                        }
                        viewModelVehiculo.limpiarFormularioVehiculo()
                        navController.popBackStack()
                    },
                    onBorrar = {
                        viewModelVehiculo.eliminarVehiculo(id)
                        navController.popBackStack()
                    },
                    onSeleccionarCliente = { navController.navigate(Pantallas.SeleccionarVehiculoCliente.name) },
                )
            }
            composable(route = Pantallas.EditarClientes.name) {
                PantallaEditarClientes(
                    modifier = Modifier,
                    cliente = viewModelCliente.clientePulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        it.cod_cliente?.let { it1 -> viewModelCliente.actualizarCliente(it1, it) }
                        navController.popBackStack()
                    },
                    onBorrar = {
                        viewModelCliente.eliminarCliente(id)
                        navController.popBackStack()
                    },
                )
            }
            composable(route = Pantallas.EditarEmpleados.name) {
                PantallaEditarEmpleados(
                    modifier = Modifier,
                    viewModel = viewModelEmpleado,
                    empleado = viewModelEmpleado.empleadoPulsado,
                    onCancelar = {
                        viewModelEmpleado.limpiarFormularioEmpleado()
                        navController.popBackStack(Pantallas.Averias.name, inclusive = false)
                    },
                    onGuardar = {
                        it.cod_empleado?.let { it1 ->
                            viewModelEmpleado.actualizarEmpleado(
                                it1,
                                it
                            )
                        }
                        viewModelEmpleado.limpiarFormularioEmpleado()
                        navController.popBackStack()
                    },
                    onBorrar = {
                        viewModelEmpleado.eliminarEmpleado(id)
                        navController.popBackStack(Pantallas.Averias.name, inclusive = false)
                    },
                    onSeleccionarRol = { navController.navigate(Pantallas.SeleccionarEmpleadoRol.name) },
                )
            }
            composable(route = Pantallas.EditarPiezas.name) {
                PantallaEditarPiezas(
                    modifier = Modifier,
                    viewModel = viewModelPieza,
                    pieza = viewModelPieza.piezaPulsado,
                    onCancelar = {
                        viewModelPieza.limpiarFormularioPieza()
                        navController.popBackStack()
                    },
                    onGuardar = {
                        it.cod_pieza?.let { it1 -> viewModelPieza.actualizarPieza(it1, it) }
                        viewModelPieza.limpiarFormularioPieza()
                        navController.popBackStack()
                    },
                    onBorrar = {
                        viewModelPieza.eliminarPieza(id)
                        navController.popBackStack()
                    },
                    onSeleccionarTipopieza = { navController.navigate(Pantallas.SeleccionarPiezaTipopieza.name) },
                )
            }
            composable(route = Pantallas.EditarTipoaverias.name) {
                PantallaEditarTipoaverias(
                    modifier = Modifier,
                    tipoaveria = viewModelTipoaveria.tipoaveriaPulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        it.cod_tipo_averia?.let { it1 ->
                            viewModelTipoaveria.actualizarTipoaveria(
                                it1,
                                it
                            )
                        }
                        navController.popBackStack()
                    },
                )
            }
            composable(route = Pantallas.EditarTipopiezas.name) {
                PantallaEditarTipopiezas(
                    modifier = Modifier,
                    tipopieza = viewModelTipopieza.tipopiezaPulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        it.cod_tipo_pieza?.let { it1 ->
                            viewModelTipopieza.actualizarTipopieza(
                                it1,
                                it
                            )
                        }
                        navController.popBackStack()
                    },
                )
            }
            //CAMBIO DE CONTRASENYA
            composable(route = Pantallas.CambioContrasenya.name) {
                PantallaCambiarContrasenya(
                    modifier = Modifier,
                    empleado = viewModelEmpleado.empleadoPulsado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onCambiar = {
                        it.cod_empleado?.let { it1 ->
                            viewModelEmpleado.actualizarEmpleado(
                                it1,
                                it
                            )
                        }
                        navController.popBackStack()
                    })
            }
            //EDITADO MIPERFIL
            composable(route = Pantallas.MiPerfil.name) {
                PantallaMiPerfil(
                    modifier = Modifier,
                    empleadoViewModel = viewModelEmpleado,
                    onCancelar = {
                        navController.popBackStack()
                    },
                    onGuardar = {
                        it.cod_empleado?.let { it1 ->
                            viewModelEmpleado.actualizarEmpleado(
                                it1,
                                it
                            )
                        }
                        navController.popBackStack()
                    },
                    onCambiar = {
                        viewModelEmpleado.empleadoLogin?.let { empleado ->
                            viewModelEmpleado.actualizarEmpleadoPulsado(
                                empleado
                            )
                        }
                        navController.navigate(Pantallas.CambioContrasenya.name) {
                            popUpTo(Pantallas.MiPerfil.name) { inclusive = false }
                        }
                    },
                )
            }
            //PANTALLA BUSCAR MATRICULA
            composable(route = Pantallas.BuscarMatricula.name) {
                PantallaBuscarPorMatricula(
                    modifier = Modifier,
                    viewModel = viewModelAveria,
                    onAceptar = {
                        navController.popBackStack()
                    })
            }
            //SELECCIONABLES
            composable(route = Pantallas.SeleccionarVehiculoCliente.name) {
                LaunchedEffect(Unit) {
                    viewModelCliente.obtenerClientes()
                }
                PantallaVehiculoCliente(
                    viewModelVehiculo = viewModelVehiculo,
                    viewModelCliente = viewModelCliente,
                    onSeleccionar = {
                        navController.popBackStack()
                    })
            }
            composable(route = Pantallas.SeleccionarEmpleadoRol.name) {
                LaunchedEffect(Unit) {
                    viewModelRol.obtenerRoles()
                }
                PantallaEmpleadoRol(
                    viewModelEmpleado = viewModelEmpleado,
                    viewModelRol = viewModelRol,
                    onSeleccionar = {
                        navController.popBackStack()
                    })
            }
            composable(route = Pantallas.SeleccionarAveriaCliente.name) {
                LaunchedEffect(Unit) {
                    viewModelCliente.obtenerClientes()
                }
                PantallaAveriaCliente(
                    viewModelAveria = viewModelAveria,
                    viewModelCliente = viewModelCliente,
                    onSeleccionar = {
                        navController.popBackStack()
                    })
            }
            composable(route = Pantallas.SeleccionarAveriaEmpleado.name) {
                LaunchedEffect(Unit) {
                    viewModelEmpleado.obtenerEmpleados()
                }
                PantallaAveriaEmpleado(
                    viewModelAveria = viewModelAveria,
                    viewModelEmpleado = viewModelEmpleado,
                    onSeleccionar = {
                        navController.popBackStack()
                    })
            }
            composable(route = Pantallas.SeleccionarAveriaVehiculo.name) {
                LaunchedEffect(Unit) {
                    viewModelVehiculo.obtenerVehiculos()
                }
                PantallaAveriaVehiculo(
                    viewModelAveria = viewModelAveria,
                    viewModelVehiculo = viewModelVehiculo,
                    onSeleccionar = {
                        navController.popBackStack()
                    })
            }
            composable(route = Pantallas.SeleccionarPiezaTipopieza.name) {
                LaunchedEffect(Unit) {
                    viewModelTipopieza.obtenerTipospieza()
                }
                PantallaPiezaTipopieza(
                    viewModelPieza = viewModelPieza,
                    viewModelTipopieza = viewModelTipopieza,
                    onSeleccionar = {
                        navController.popBackStack()
                    })
            }
            composable(route = Pantallas.SeleccionarAveriaTipoaveria.name) {
                PantallaAveriaTipoaverias(
                    viewModelAveria = viewModelAveria,
                    viewModelTipoaveria = viewModelTipoaveria,
                    viewModelAveriatipoaveria = viewModelAveriatipoaveria,
                    onSeleccionar = {
                        navController.popBackStack()
                    },
                    onCancelar = { navController.popBackStack() })
            }
            composable(route = Pantallas.SeleccionarAveriaAveriapieza.name) {
                PantallaAveriaAveriapiezas(
                    viewModelAveria = viewModelAveria,
                    viewModelAveriapieza = viewModelAveriapieza,
                    viewModelPieza = viewModelPieza,
                    onSeleccionar = {
                        navController.popBackStack()
                    })
            }
            //INFO
            composable(route = Pantallas.InfoRol.name) {
                PantallaDetalleRolPermiso(
                    modifier = Modifier,
                    rolUIState = rolUIState,
                    onRolesObtenidos = { viewModelRol.obtenerRoles() },
                    onAceptar = {
                        navController.popBackStack()
                    })
            }
        }
    }
}

//APP TOP BAR
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    modifier: Modifier = Modifier,
    pantallaActual: Pantallas,
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    var mostrarMenu by remember { mutableStateOf(false) }
    val viewModelEmpleado: EmpleadoViewModel = viewModel(factory = EmpleadoViewModel.Factory)

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = pantallaActual.titulo),
                color = Blanco,
                fontWeight = FontWeight.Bold
            )
        }, colors = TopAppBarDefaults.mediumTopAppBarColors(
//            containerColor = MaterialTheme.colorScheme.primaryContainer
            containerColor = AzulPrincipal
        ), actions = {
            if (pantallaActual == Pantallas.Averias || pantallaActual == Pantallas.Coches || pantallaActual == Pantallas.Clientes || pantallaActual == Pantallas.Piezas || pantallaActual == Pantallas.Facturas) {
                IconButton(onClick = { mostrarMenu = true }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        tint = Blanco,
                        contentDescription = stringResource(R.string.abrir_menu)
                    )
                }
                DropdownMenu(expanded = mostrarMenu, onDismissRequest = { mostrarMenu = false }) {
                    DropdownMenuItem(text = {
                        Text(text = stringResource(id = R.string.texto_miperfil))
                    }, leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.AccountCircle, contentDescription = null
                        )
                    }, onClick = {
                        mostrarMenu = false
                        viewModelEmpleado.empleadoLogin
                        navController.navigate(Pantallas.MiPerfil.name) {
                            popUpTo(Pantallas.Averias.name) { inclusive = false }
//                                launchSingleTop = true
                        }
                    })
                    // 1 == empleado, 2 == encargado, 3 == gerente (admin)
                    if (viewModelEmpleado.empleadoLogin?.rol?.cod_rol == 3) {
                        DropdownMenuItem(text = {
                            Text(text = stringResource(id = R.string.administrar))
                        }, leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Settings, contentDescription = null
                            )
                        }, onClick = {
                            mostrarMenu = false
                            viewModelEmpleado.obtenerEmpleados()
                            navController.navigate(Pantallas.Empleados.name) {
                                popUpTo(Pantallas.Averias.name) { inclusive = false }
//                                launchSingleTop = true
                            }
                        })
                    }
                    if (viewModelEmpleado.empleadoLogin?.rol?.cod_rol == 3) {
                        DropdownMenuItem(text = {
                            Text(text = stringResource(id = R.string.texto_tipoaverias))
                        }, leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Build, contentDescription = null
                            )
                        }, onClick = {
                            mostrarMenu = false
                            navController.navigate(Pantallas.Tipoaverias.name) {
                                popUpTo(Pantallas.Averias.name) { inclusive = false }
//                                launchSingleTop = true
                            }
                        })
                    }
                    if (viewModelEmpleado.empleadoLogin?.rol?.cod_rol == 3) {
                        DropdownMenuItem(text = {
                            Text(text = stringResource(id = R.string.texto_tipopiezas))
                        }, leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Build, contentDescription = null
                            )
                        }, onClick = {
                            mostrarMenu = false
                            navController.navigate(Pantallas.Tipopiezas.name) {
                                popUpTo(Pantallas.Averias.name) { inclusive = false }
//                                launchSingleTop = true
                            }
                        })
                    }
                    DropdownMenuItem(text = {
                        Text(text = stringResource(id = R.string.log_out))
                    }, leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.logout), contentDescription = null
                        )
                    }, onClick = {
                        mostrarMenu = false
                        viewModelEmpleado.cerrarSesion()
                        navController.navigate(Pantallas.Login.name) {
                            popUpTo(0) { inclusive = true }
                            launchSingleTop = true
                        }
                    })
                }
            } else if (pantallaActual == Pantallas.Empleados) {
                IconButton(onClick = {
                    navController.navigate(Pantallas.InfoRol.name)
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        tint = Blanco,
                        contentDescription = stringResource(R.string.pantalla_info_rol)
                    )
                }
            }
        }, scrollBehavior = scrollBehavior, modifier = modifier
    )
}

