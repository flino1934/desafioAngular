package com.qintess.clientes.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String endereco;

    @Column
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    private CasaDeShow casaDeShow;

}
