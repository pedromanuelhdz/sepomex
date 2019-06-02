package com.perales.sepomex.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Indexed
@Data
@EqualsAndHashCode( exclude = { "id", "ciudades" , "municipios", "colonias", "codigosPostales"})
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@ToString(exclude = {"asentamientoTipo","municipio", "estado", "ciudad", "zonaTipo"})
@Entity(name = "estado")
public class Estado implements Serializable {
    @Id
    @GeneratedValue(
            generator = "sequence_estado",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "sequence_estado",
            allocationSize = 10
    )
    @Column(name = "id")
    private Integer id;
    
    @Field(termVector = TermVector.YES)
    @NotNull
    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    @Field(termVector = TermVector.YES)
    @NotNull
    @NotBlank
    @Column(name = "inegi_clave", nullable = false)
    private String inegiClave;
    
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "estado")
    private List<Ciudad> ciudades;
    
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "estado")
    private List<Municipio> municipios;
    
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "estado")
    private List<Colonia> colonias;
    
    @JsonIdentityReference(alwaysAsId = true)
    @OneToMany(mappedBy = "estado")
    private List<CodigoPostal> codigosPostales;
}
