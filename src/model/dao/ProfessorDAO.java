package model.dao;

import model.entities.Professores;

import java.util.List;

public interface ProfessorDAO extends DAO<Professores> {

    List<Professores> buscarProfessoresPorCurso(Integer idCurso);
}
