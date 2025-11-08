package Examen.serie3.Repository;

import Examen.serie3.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByProveedorId(Integer proveedorId);
    
    @Query("SELECT p FROM Producto p WHERE p.cantidad > 0")
    List<Producto> findProductosEnBodega();
}