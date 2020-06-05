package com.qintess.clientes.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CasaDeShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_casa",nullable = false)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nomeCasa;

    @Column(nullable = false)
    @NotNull(message = "{campo.capacidade.obrigatorio}")
    private Integer capacidade;

    @Column(nullable = false)
    @NotEmpty(message = "{campo.endereco.obrigatorio}")
    private String endereco;

    @Column(name = "data_cadastro",updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }

}
