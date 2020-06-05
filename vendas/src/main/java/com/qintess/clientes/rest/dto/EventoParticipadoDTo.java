package com.qintess.clientes.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventoParticipadoDTo {

    private String descricao;
    private String preco;
    private String data;
    private String quantidade;
    private String endereco;
    private Integer idCliente;
}
