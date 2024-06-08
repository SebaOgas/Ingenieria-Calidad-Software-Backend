package utn.ics.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.ics.config.Parameter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "producto")
@Data
@Builder
public class Producto extends BaseEntity {

  @Column(name = "nombre", length = Parameter.shortStringLength, nullable = false, unique = true)
  private String nombre;

  @Column(name = "descripcion", length = Parameter.longStringLength, nullable = true)
  private String descripcion;

  @Column(name = "visibilidad", nullable = false)
  private Boolean visibilidad;

  @ManyToOne
  @JoinColumn(
      name = "marca_id",
      nullable = false,
      referencedColumnName = "numero",
      foreignKey = @ForeignKey(name = "FK_producto_marca"))
  private Marca marca;
}
