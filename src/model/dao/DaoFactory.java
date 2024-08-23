package model.dao;

import db.DB; // Supondo que você tenha uma classe DB para gerenciar a conexão com o banco de dados
import model.dao.impl.*;

public class DaoFactory {

    public static AlunoDAO criarAlunoDAO() {
        return new AlunoDAOJDBC(DB.getConnection());
    }

    public static ProfessorDAO criarProfessorDAO() {
        return new ProfessorDAOJDBC(DB.getConnection());
    }

    public static CursoDAO criarCursoDAO() {
        return new CursoDAOJDBC(DB.getConnection());
    }

    public static MatriculaDAO criarMatriculaDAO() {
        return new MatriculaDAOJDBC(DB.getConnection());
    }

    public static NotaDAO criarNotaDAO() {
        return new NotaDAOJDBC(DB.getConnection());
    }
}
