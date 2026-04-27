package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {
    
    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculoService.obtenerTodos();
    }

    @PostMapping
    public Vehiculo crearVehiculo(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.crearVehiculo(vehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminarVehiculo(@PathVariable Long id) {
        vehiculoService.eliminarVehiculo(id);
    }

    @GetMapping("/{id}")
    public Vehiculo obtenerPorId(@PathVariable Long id) {
        return vehiculoService.obtenerPorId(id);
    }

    @PutMapping("/{id}/favorito")
    public Vehiculo marcarComoFavorito(@PathVariable Long id) {
        return vehiculoService.marcarComoFavorito(id);
    }

    @GetMapping("/buscar")
    public List<Vehiculo> buscar(
        @RequestParam(required = false) String marca,
        @RequestParam(required = false) String modelo,
        @RequestParam(required = false) String color,
        @RequestParam(required = false) Integer anio,
        @RequestParam(required = false) Boolean favorito
    ) {
        return vehiculoService.buscarDinamico(marca, modelo, color, anio, favorito);
    }
    @GetMapping("/estadisticas")
    public EstadisticasDTO verEstadisticas() {
        return vehiculoService.verEstadisticas();
    }
}
