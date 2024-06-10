package utn.ics.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "variante")
@Data
@Builder
public class Variante extends BaseEntity {

  @Column(name = "nombre", length = 256, nullable = false, unique = true)
  private String nombre;

  @Column(name = "descripcion", length = 1024, nullable = true)
  private String descripcion;

  // la imagen queda a implementar en un futuro
  // no tengo en claro cómo hacer referencia a un asset desde un proyecto en Java

  @Column(name = "principal", nullable = false)
  private Boolean principal;

  @ManyToOne
  @JoinColumn(
      name = "producto_id",
      nullable = false,
      referencedColumnName = "numero",
      foreignKey = @ForeignKey(name = "FK_variante_producto"))
  private Producto producto;

  @Builder.Default
  @ManyToMany
  @JoinTable(
      name = "variante_valor_caracteristica",
      joinColumns = @JoinColumn(name = "variante_numero"),
      inverseJoinColumns = @JoinColumn(name = "valor_caracteristica_numero"))
  private Collection<ValorCaracteristica> valores_caracteristica = new ArrayList<>();
}
