package utn.ics.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "caracteristica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caracteristica extends BaseEntity {

  @Column(name = "nombre", length = 256, nullable = false, unique = true)
  private String nombre;

  @Builder.Default
  @OneToMany
  @JoinColumn(
      nullable = false,
      foreignKey = @ForeignKey(name = "FK_valor_caracteristica_caracteristica"))
  private Collection<ValorCaracteristica> valores = new ArrayList<>();

  @ManyToMany(mappedBy = "caracteristicas")
  private Collection<Subcategoria> subcategorias=new ArrayList<>();
}
