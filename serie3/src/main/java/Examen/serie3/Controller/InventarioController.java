package Examen.serie3.Controller;


import Examen.serie3.Service.ProductoService;
import Examen.serie3.DTOs.ProductoDTO;
import Examen.serie3.DTOs.MovimientoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private ProductoService productoService;

    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO created = productoService.crearProducto(productoDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/inventario")
    public ResponseEntity<List<ProductoDTO>> consultarInventario() {
        List<ProductoDTO> inventario = productoService.consultarInventario();
        return ResponseEntity.ok(inventario);
    }

    @GetMapping("/bodega")
    public ResponseEntity<List<ProductoDTO>> consultarProductosEnBodega() {
        List<ProductoDTO> productos = productoService.consultarProductosEnBodega();
        return ResponseEntity.ok(productos);
    }

    @PostMapping("/movimientos/entrada")
    public ResponseEntity<MovimientoDTO> registrarEntrada(@RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO movimiento = productoService.registrarEntrada(movimientoDTO);
        return ResponseEntity.ok(movimiento);
    }

    @PostMapping("/movimientos/salida")
    public ResponseEntity<MovimientoDTO> registrarSalida(@RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO movimiento = productoService.registrarSalida(movimientoDTO);
        return ResponseEntity.ok(movimiento);
    }

    @GetMapping("/movimientos")
    public ResponseEntity<List<MovimientoDTO>> consultarHistorialMovimientos() {
        List<MovimientoDTO> movimientos = productoService.consultarHistorialMovimientos();
        return ResponseEntity.ok(movimientos);
    }
}