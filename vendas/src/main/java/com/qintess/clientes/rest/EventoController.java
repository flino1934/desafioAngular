package com.qintess.clientes.rest;

import com.qintess.clientes.model.entity.Cliente;
import com.qintess.clientes.model.entity.Evento;
import com.qintess.clientes.model.repository.ClienteRepository;
import com.qintess.clientes.model.repository.EventoRepository;
import com.qintess.clientes.rest.dto.EventoParticipadoDTo;
import com.qintess.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final ClienteRepository clienteRepository;
    private final EventoRepository repository;
    private final BigDecimalConverter bigDecimalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Evento salvar(@RequestBody EventoParticipadoDTo dto){

        LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = dto.getIdCliente();

       Cliente cliente =
                clienteRepository
                        .findById(idCliente)
                            .orElseThrow(()->
                                    new ResponseStatusException(
                                            HttpStatus.BAD_REQUEST,"Cliente Inexistente"
                                    ));

        Evento evento = new Evento();
        evento.setDescricao(dto.getDescricao());
        evento.setData(data);
        evento.setQuantidade(Integer.parseInt(dto.getQuantidade()));
        evento.setEndereco(dto.getEndereco());
        //evento.setCliente(cliente); eu apaguei cliente em evento tem que colocar
        evento.setValor(bigDecimalConverter.converter(dto.getPreco()));

        return repository.save(evento);

    }


}
