package com.qintess.clientes.rest;

import com.qintess.clientes.model.entity.CasaDeShow;
import com.qintess.clientes.model.entity.Cliente;
import com.qintess.clientes.model.repository.CasaDeShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/casas-de-show")
@CrossOrigin("http://localhost:4200")
public class CasaDeShowController {

   private CasaDeShowRepository repository;

   @Autowired
    public CasaDeShowController(CasaDeShowRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CasaDeShow> obterTodos(){

        return repository.findAll();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CasaDeShow salvar(@RequestBody @Valid CasaDeShow casaDeShow){

        return repository.save(casaDeShow);

    }

    @GetMapping("{id}")
    public CasaDeShow acharPorId(@PathVariable Integer id){

        return repository.findById(id).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){

        //repository.deleteById(id); poderiamos usae essa abordagem que é mais simples

        repository.findById(id).map(casaDeShow ->
        {
            repository.delete(casaDeShow);
            return Void.TYPE;
        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"casa de show não encontrado!"));//essa abordagem ele vai pesquisar o cliente

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody CasaDeShow casaDeShowAtualizado){

        repository.findById(id).map(casaDeShow ->
        {
            casaDeShow.setNomeCasa(casaDeShowAtualizado.getNomeCasa());
            casaDeShow.setCapacidade(casaDeShowAtualizado.getCapacidade());
            casaDeShow.setEndereco(casaDeShowAtualizado.getEndereco());
            return repository.save(casaDeShowAtualizado);

        }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado!"));//essa abordagem ele vai pesquisar o cliente


    }
    
}
