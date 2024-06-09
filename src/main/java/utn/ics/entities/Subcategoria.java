package utn.ics.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "subcategoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subcategoria extends BaseEntity{

    @Column(name = "titulo", length = 256, nullable = false, unique = true)
    private String titulo;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "subcaterogia-caracteristica",
    joinColumns = @JoinColumn(name = "subcategoria_numero"),
    inverseJoinColumns = @JoinColumn(name = "caracteristica_numero"))
    private Collection<Caracteristica> caracteristicas=new ArrayList<>();
}
