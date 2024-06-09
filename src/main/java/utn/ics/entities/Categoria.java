package utn.ics.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria extends BaseEntity{

    @Column(name = "titulo", length = 256, nullable = false, unique = true)
    private String titulo;

    @Builder.Default
    @OneToMany
    @JoinColumn(
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_subcategoria_categoria"))
    private Collection<Subcategoria> subcategorias=new ArrayList<>();

}
