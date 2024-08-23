package model.dao;

import model.entities.Cursos;

import java.util.List;

public interface CursoDAO extends DAO<Cursos> {

    List<Cursos> buscarCursosPorProfessor(Integer idProfessor);
}
