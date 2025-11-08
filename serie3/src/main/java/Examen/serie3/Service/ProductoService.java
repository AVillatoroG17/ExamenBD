package Examen.serie3.Service;

import Examen.serie3.Entity.Producto;
import Examen.serie3.Entity.Proveedor;
import Examen.serie3.Entity.Movimiento;
import Examen.serie3.Repository.ProductoRepository;
import Examen.serie3.Repository.ProveedorRepository;
import Examen.serie3.Repository.MovimientoRepository;
import Examen.serie3.DTOs.ProductoDTO;
import Examen.serie3.DTOs.MovimientoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;

    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Proveedor proveedor = proveedorRepository.findById(productoDTO.getProveedorId())
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado"));
        
        Producto producto = new Producto();
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCantidad(productoDTO.getCantidad());
        producto.setPrecioCompra(productoDTO.getPrecioCompra());
        producto.setProveedor(proveedor);
        
        Producto saved = productoRepository.save(producto);
        return convertirADTO(saved);
    }

    public List<ProductoDTO> consultarInventario() {
        return productoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public List<ProductoDTO> consultarProductosEnBodega() {
        return productoRepository.findProductosEnBodega().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MovimientoDTO registrarEntrada(MovimientoDTO movimientoDTO) {
        Producto producto = productoRepository.findById(movimientoDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        producto.setCantidad(producto.getCantidad() + movimientoDTO.getCantidad());
        productoRepository.save(producto);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setProducto(producto);
        movimiento.setTipo("ENTRADA");
        movimiento.setCantidad(movimientoDTO.getCantidad());
        movimiento.setFechaMovimiento(LocalDateTime.now());
        movimiento.setObservacion(movimientoDTO.getObservacion());
        
        Movimiento saved = movimientoRepository.save(movimiento);
        return convertirMovimientoADTO(saved);
    }

    public MovimientoDTO registrarSalida(MovimientoDTO movimientoDTO) {
        Producto producto = productoRepository.findById(movimientoDTO.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        if (producto.getCantidad() < movimientoDTO.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }
        
        producto.setCantidad(producto.getCantidad() - movimientoDTO.getCantidad());
        productoRepository.save(producto);
        
        Movimiento movimiento = new Movimiento();
        movimiento.setProducto(producto);
        movimiento.setTipo("SALIDA");
        movimiento.setCantidad(movimientoDTO.getCantidad());
        movimiento.setFechaMovimiento(LocalDateTime.now());
        movimiento.setObservacion(movimientoDTO.getObservacion());
        
        Movimiento saved = movimientoRepository.save(movimiento);
        return convertirMovimientoADTO(saved);
    }

    public List<MovimientoDTO> consultarHistorialMovimientos() {
        return movimientoRepository.findAllOrderByFechaDesc().stream()
                .map(this::convertirMovimientoADTO)
                .collect(Collectors.toList());
    }

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCantidad(producto.getCantidad());
        dto.setPrecioCompra(producto.getPrecioCompra());
        dto.setProveedorId(producto.getProveedor().getId());
        dto.setProveedorNombre(producto.getProveedor().getNombre());
        return dto;
    }

    private MovimientoDTO convertirMovimientoADTO(Movimiento movimiento) {
        MovimientoDTO dto = new MovimientoDTO();
        dto.setId(movimiento.getId());
        dto.setProductoId(movimiento.getProducto().getId());
        dto.setProductoNombre(movimiento.getProducto().getNombre());
        dto.setTipo(movimiento.getTipo());
        dto.setCantidad(movimiento.getCantidad());
        dto.setFechaMovimiento(movimiento.getFechaMovimiento());
        dto.setObservacion(movimiento.getObservacion());
        return dto;
    }
}