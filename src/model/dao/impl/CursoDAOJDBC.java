package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.CursoDAO;
import model.entities.Cursos;
import model.entities.Professores;

public class CursoDAOJDBC implements CursoDAO {

    private Connection conn;

    public CursoDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Cursos obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Cursos " +
                            "(nome, descricao, duracao_horas, id_professor) " +
                            "VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            setCursoData(st, obj);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setIdCurso(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha foi afetada!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void atualizar(Cursos obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Cursos SET nome = ?, descricao = ?, duracao_horas = ?, id_professor = ? " +
                            "WHERE id_curso = ?");

            setCursoData(st, obj);
            st.setInt(5, obj.getIdCurso());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void excluirPorId(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM Cursos WHERE id_curso = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Cursos buscarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Cursos WHERE id_curso = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Cursos obj = instantiateCursos(rs);
                printCursoHeader();
                printCursoData(obj);
                System.out.println("----------------------------------------------------------------------------------------------------------------");
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Cursos> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Cursos ORDER BY nome");
            rs = st.executeQuery();

            List<Cursos> list = new ArrayList<>();

            printCursoHeader();

            while (rs.next()) {
                Cursos obj = instantiateCursos(rs);
                list.add(obj);
                printCursoData(obj);
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------");
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Cursos> buscarCursosPorProfessor(Integer idProfessor) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Cursos WHERE id_professor = ?");
            st.setInt(1, idProfessor);
            rs = st.executeQuery();

            List<Cursos> list = new ArrayList<>();

            printCursoHeader();

            while (rs.next()) {
                Cursos obj = instantiateCursos(rs);
                list.add(obj);
                printCursoData(obj);
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------");
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Cursos instantiateCursos(ResultSet rs) throws SQLException {
        Cursos obj = new Cursos();
        obj.setIdCurso(rs.getInt("id_curso"));
        obj.setNome(rs.getString("nome"));
        obj.setDescricao(rs.getString("descricao"));
        obj.setDuracaoHoras(rs.getInt("duracao_horas"));

        Professores professor = new Professores();
        professor.setIdProfessor(rs.getInt("id_professor"));
        obj.setProfessores(professor);

        return obj;
    }

    private void setCursoData(PreparedStatement st, Cursos obj) throws SQLException {
        st.setString(1, obj.getNome());
        st.setString(2, obj.getDescricao());
        st.setInt(3, obj.getDuracaoHoras());
        st.setInt(4, obj.getProfessores().getIdProfessor());
    }

    private void printCursoHeader() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-30s %-15s %-15s%n", "| ID", "| NOME", "| DESCRIÇÃO", "| DURAÇÃO (horas)", "| PROFESSOR");
        System.out.println("----- -------------------- ------------------------------ --------------- ---------------");
    }

    private void printCursoData(Cursos obj) {
        System.out.printf("  %-5d %-20s %-30s %-15d %-15d%n",
                obj.getIdCurso(),
                obj.getNome(),
                obj.getDescricao(),
                obj.getDuracaoHoras(),
                obj.getProfessores().getIdProfessor());
    }
}
