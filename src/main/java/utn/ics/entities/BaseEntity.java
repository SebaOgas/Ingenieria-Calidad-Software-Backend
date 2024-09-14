package utn.ics.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity implements Serializable {

  @Id
  @Column(name = "numero")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long numero;

  @Column(name = "fecha_baja")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaBaja;
}
