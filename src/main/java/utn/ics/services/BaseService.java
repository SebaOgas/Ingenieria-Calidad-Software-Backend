package utn.ics.services;

import utn.ics.entities.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseService <E extends BaseEntity,ID extends Serializable>{
    List<E> findAll() throws Exception;

    E findById(ID id) throws Exception;

    E save(E entity) throws Exception;

    E update(ID id, E entity) throws Exception;

    boolean delete(ID id) throws Exception;
}
