package utn.ics.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import utn.ics.config.Parameter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "marca")
@Data
@Builder
public class Marca extends BaseEntity{

    @Column(name = "nombre", length = Parameter.shortStringLength, nullable = false, unique = true)
    private String nombre;

    @Column(name = "descripcion", length = Parameter.longStringLength, nullable = true)
    private String descripcion;
}
