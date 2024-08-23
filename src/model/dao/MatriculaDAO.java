package model.dao;

import model.entities.Matriculas;

import java.util.List;

public interface MatriculaDAO extends DAO<Matriculas> {

    List<Matriculas> buscarMatriculasPorAluno(Integer idAluno);

    List<Matriculas> buscarMatriculasPorCurso(Integer idCurso);
}
