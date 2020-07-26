package com.perales.sepomex.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@EqualsAndHashCode( exclude = { "id", "colonias"})
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity(name = "inegi_clave_municipio")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InegiClaveMunicipio implements Serializable {
    
    @Id
    @GeneratedValue(
            generator = "sequence_inegi_clave_municipio",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "sequence_inegi_clave_municipio",
            allocationSize = 10
    )
    @Column(name = "id")
    private Integer id;
    
    @Field(termVector = TermVector.YES)
    @NotNull
    @NotBlank
    @Column(name = "nombre", nullable = false)
    private String nombre;
    
    
    @OneToMany(mappedBy = "inegiClaveMunicipio", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Colonia> colonias;
}
