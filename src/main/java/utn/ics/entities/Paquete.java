package utn.ics.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paquetes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paquete extends BaseEntity {

    @Column(name = "titulo", nullable = false, length = 256, unique = true)
    private String titulo;

    @ManyToMany
    @JoinTable(
            name = "paquetes_productos",
            joinColumns = @JoinColumn(name = "numero_paquete"),
            inverseJoinColumns = @JoinColumn(name = "numero_producto"))
    private List<Producto> listaProductos = new ArrayList<>();

    public void add(Producto producto) {
        this.listaProductos.add(producto);
    }
}