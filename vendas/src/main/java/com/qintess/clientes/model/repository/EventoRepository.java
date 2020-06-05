package com.qintess.clientes.model.repository;

import com.qintess.clientes.model.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Integer> {
}
