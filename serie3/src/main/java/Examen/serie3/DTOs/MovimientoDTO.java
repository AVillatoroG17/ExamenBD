package Examen.serie3.DTOs;

import java.time.LocalDateTime;

public class MovimientoDTO {
    private Integer id;
    private Integer productoId;
    private String productoNombre;
    private String tipo;
    private Integer cantidad;
    private LocalDateTime fechaMovimiento;
    private String observacion;

    public MovimientoDTO() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getProductoId() { return productoId; }
    public void setProductoId(Integer productoId) { this.productoId = productoId; }
    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public LocalDateTime getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(LocalDateTime fechaMovimiento) { this.fechaMovimiento = fechaMovimiento; }
    public String getObservacion() { return observacion; }
    public void setObservacion(String observacion) { this.observacion = observacion; }
}