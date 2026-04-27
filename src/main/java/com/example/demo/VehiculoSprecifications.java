package com.example.demo;

import org.springframework.data.jpa.domain.Specification;

public class VehiculoSprecifications {
    
    public static Specification<Vehiculo> marcaEquals(String marca) {
        return (root, query, criteriaBuilder) -> marca == null ? null : criteriaBuilder.equal(root.get("marca"), marca);
    }

    public static Specification<Vehiculo> modeloEquals(String modelo) {
        return (root, query, criteriaBuilder) -> modelo == null ? null : criteriaBuilder.equal(root.get("modelo"), modelo);
    }

    public static Specification<Vehiculo> colorEquals(String color) {
        return (root, query, criteriaBuilder) -> color == null ? null : criteriaBuilder.equal(root.get("color"), color);
    }

    public static Specification<Vehiculo> anioEquals(Integer anio) {
        return (root, query, criteriaBuilder) -> anio == null ? null : criteriaBuilder.equal(root.get("anio"), anio);
    }

    public static Specification<Vehiculo> favoritoEquals(Boolean favorito) {
        return (root, query, criteriaBuilder) -> favorito == null ? null : criteriaBuilder.equal(root.get("favorito"), favorito);
    }
}
