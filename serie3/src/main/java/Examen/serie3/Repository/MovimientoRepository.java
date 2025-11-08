package Examen.serie3.Repository;


import Examen.serie3.Entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
    List<Movimiento> findByProductoId(Integer productoId);
    
    @Query("SELECT m FROM Movimiento m ORDER BY m.fechaMovimiento DESC")
    List<Movimiento> findAllOrderByFechaDesc();
}