package model.dao;

import model.entities.Notas;

import java.util.List;

public interface NotaDAO extends DAO<Notas> {

    List<Notas> buscarNotasPorMatricula(Integer idMatricula);
}
