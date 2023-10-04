package br.com.fiap.domain.repository;

import java.util.List;

public interface Repository<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public List<T> findByTexto(String texto);

    public T persiste(T t);

    public T update(T t);

    public boolean delete(T t);

}
