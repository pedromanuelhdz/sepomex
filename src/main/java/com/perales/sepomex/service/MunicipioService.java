package com.perales.sepomex.service;

import com.perales.sepomex.contract.ServiceGeneric;
import com.perales.sepomex.model.Municipio;
import com.perales.sepomex.repository.MunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService implements ServiceGeneric<Municipio, Integer> {

    @Autowired
    private MunicipioRepository municipioRepository;

    public Municipio buscarPorId(Integer id) {
        return null;
    }

    public Page<Municipio> buscarTodos(int page, int size) {
        return null;
    }

    public Municipio guardar(Municipio entity) {
        return municipioRepository.save(entity);
    }

    public Municipio actualizar(Municipio entity) {
        return null;
    }

    public Municipio borrar(Integer id) {
        return null;
    }

    public Municipio findFirstByNombreAndEstadoId(String nombre, Integer estadoId) {
        return municipioRepository.findFirstByNombreAndEstadoId(nombre,estadoId);
    }
}
