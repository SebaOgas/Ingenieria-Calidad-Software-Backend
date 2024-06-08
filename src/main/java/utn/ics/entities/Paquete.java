package utn.ics.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paquetes")
@Data
@Builder
public class Paquete extends BaseEntity{

    @Column(name = "titulo", nullable = false, length = 256, unique = true)
    private String titulo;
    @ManyToMany
    @JoinTable(
            name = "paquetes_productos",
            joinColumns = @JoinColumn(name = "numero_paquete"),
            inverseJoinColumns = @JoinColumn(name = "numero_producto"))
    private final List<Producto> listaProductos = new ArrayList<Producto>();

    public void add(Producto producto) {
        this.listaProductos.add(producto);
    }
}
