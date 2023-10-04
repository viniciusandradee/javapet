package br.com.fiap.domain.service;

import java.util.List;

public interface Service<T, U> {

    public List<T> findAll();

    public T findById(U id);

    public List<T> findByTexto(String texto);

    public T persiste(T t);

    public T update(U id, T t);

    public boolean delete(T t);

}