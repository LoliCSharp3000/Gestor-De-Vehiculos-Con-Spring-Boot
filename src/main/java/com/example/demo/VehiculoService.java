package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class VehiculoService {
    
    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> buscarPorMarcaYModelo(String marca, String modelo) {
        return vehiculoRepository.findByMarcaAndModelo(marca, modelo);
    }

    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }

    public Vehiculo marcarComoFavorito(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        vehiculo.setFavorito(!vehiculo.isFavorito());
        return vehiculoRepository.save(vehiculo);
    }

    public EstadisticasDTO verEstadisticas() {
        long totalVehiculos = vehiculoRepository.count();
        long totalFavoritos = vehiculoRepository.findByFavorito(true).size();
        double porcentajeFavoritos = totalVehiculos > 0 ? (double) totalFavoritos / totalVehiculos * 100 : 0;

        return new EstadisticasDTO(totalVehiculos, totalFavoritos, porcentajeFavoritos);
    }

    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo obtenerPorId(Long id) {
        return vehiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepository.findByMarca(marca);
    }

    public List<Vehiculo> buscarPorModelo(String modelo) {
        return vehiculoRepository.findByModelo(modelo);
    }

    public List<Vehiculo> buscarFavoritos() {
        return vehiculoRepository.findByFavorito(true);
    }
}
