package model.dao;

import model.entities.Alunos;

import java.util.List;

public interface AlunoDAO extends DAO<Alunos>{
//Explicação
    /*Herança de Interfaces permite que uma interface herde métodos de outra.
     A interface que herda não precisa redefinir os métodos, mas a classe que
     implementa a interface deve fornecer a implementação para todos os métodos,
     incluindo os herdados.
    Implementação de Métodos é feita na classe que implementa a interface, como AlunoDAOJDBC no exemplo.
    Portanto, na interface AlunoDAO, você declara os métodos que a classe que a implementa deve fornecer,
     mas a implementação real desses métodos ocorre na classe concreta (AlunoDAOJDBC), não na interface.*/

    List<Alunos> buscarAlunosPorCurso(Integer idCurso);
}
