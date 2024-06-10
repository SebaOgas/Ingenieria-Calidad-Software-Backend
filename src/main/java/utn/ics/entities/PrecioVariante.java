package utn.ics.entities;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "precio_variante")
@Data
@Builder
public class PrecioVariante extends BaseEntity {

  @Column(name = "valor")
  private Double valor;

  @Column(name = "fecha_alta")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaAlta;

  @ManyToOne
  @JoinColumn(
      name = "variante_id",
      nullable = false,
      referencedColumnName = "numero",
      foreignKey = @ForeignKey(name = "FK_precio-variante_variante"))
  private Variante variante;
}
