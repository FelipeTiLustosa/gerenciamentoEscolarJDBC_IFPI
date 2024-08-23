package model.dao;

import java.util.List;

public interface DAO<T> {

    void inserir(T obj);

    void atualizar(T obj);

    void excluirPorId(Integer id);

    T buscarPorId(Integer id);

    List<T> buscarTodos();
}
