package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;

@Service
public class VehiculoService {
    
    private VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public Vehiculo Actualizar(Long id, Vehiculo nuevo){
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        vehiculo.setMarca(nuevo.getMarca());
        vehiculo.setModelo(nuevo.getModelo());
        vehiculo.setAnio(nuevo.getAnio());
        return vehiculoRepository.save(vehiculo);
    }

    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        if (vehiculo.getMarca() == null || vehiculo.getMarca().isEmpty()) {
            throw new RuntimeException("La marca es obligatoria");
        }
        return vehiculoRepository.save(vehiculo);
    }

    public void eliminarVehiculo(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new RuntimeException("Vehículo no encontrado");
        }
        vehiculoRepository.deleteById(id);
    }

    public Vehiculo marcarComoFavorito(Long id) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        vehiculo.setFavorito(!vehiculo.isFavorito());
        return vehiculoRepository.save(vehiculo);
    }

    public EstadisticasDTO verEstadisticas() {
        long totalVehiculos = vehiculoRepository.count();
        long totalFavoritos = vehiculoRepository.countByFavorito(true);
        double porcentajeFavoritos = totalVehiculos > 0 ? (double) totalFavoritos / totalVehiculos * 100 : 0;

        return new EstadisticasDTO(totalVehiculos, totalFavoritos, porcentajeFavoritos);
    }

    public List<Vehiculo> obtenerTodos() {
        return vehiculoRepository.findAll();
    }

    public Vehiculo obtenerPorId(Long id) {
        return vehiculoRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
    }

    public List<Vehiculo> buscarDinamico(String marca, String modelo, String color, Integer anio, Boolean favorito) {
        Specification<Vehiculo> spec = Specification.allOf();
        spec = spec.and(VehiculoSprecifications.marcaEquals(marca));
        spec = spec.and(VehiculoSprecifications.modeloEquals(modelo));
        spec = spec.and(VehiculoSprecifications.colorEquals(color));
        spec = spec.and(VehiculoSprecifications.anioEquals(anio));
        spec = spec.and(VehiculoSprecifications.favoritoEquals(favorito));
        return vehiculoRepository.findAll(spec);
                
    }
}
