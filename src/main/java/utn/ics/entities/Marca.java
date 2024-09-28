package utn.ics.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "marca")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Marca extends BaseEntity {

  @Column(name = "nombre", length = 256, nullable = false, unique = true)
  private String nombre;

  @Column(name = "descripcion", length = 1024, nullable = true)
  private String descripcion;
}
