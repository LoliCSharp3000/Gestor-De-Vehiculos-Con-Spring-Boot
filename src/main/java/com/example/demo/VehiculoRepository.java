package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Long>, JpaSpecificationExecutor<Vehiculo> {
    List<Vehiculo> findByMarcaAndModelo(String marca, String modelo);
    List<Vehiculo> findByFavorito(boolean favorito);
    List<Vehiculo> findByMarca(String marca);
    List<Vehiculo> findByModelo(String modelo);
    Long countByFavorito(boolean favorito);
}
