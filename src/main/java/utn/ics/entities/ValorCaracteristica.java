package utn.ics.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import utn.ics.config.Parameter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "valor_caracteristica")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValorCaracteristica extends BaseEntity {

  @Column(name = "valor", length = Parameter.shortStringLength, nullable = false, unique = false)
  private String valor;
}
