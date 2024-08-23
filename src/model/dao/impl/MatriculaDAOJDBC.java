package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MatriculaDAO;
import model.entities.Alunos;
import model.entities.Cursos;
import model.entities.Matriculas;

public class MatriculaDAOJDBC implements MatriculaDAO {

    private Connection conn;

    public MatriculaDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Matriculas obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Matriculas " +
                            "(id_aluno, id_curso, data_matricula) " +
                            "VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            setMatriculasData(st, obj);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setIdMatricula(id);
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
    public void atualizar(Matriculas obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Matriculas " +
                            "SET id_aluno = ?, id_curso = ?, data_matricula = ? " +
                            "WHERE id_matricula = ?");

            setMatriculasData(st, obj);
            st.setInt(4, obj.getIdMatricula());

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
            st = conn.prepareStatement("DELETE FROM Matriculas WHERE id_matricula = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Matriculas buscarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Matriculas WHERE id_matricula = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Matriculas obj = instantiateMatriculas(rs);
                printMatriculaHeader();
                printMatriculaData(obj);
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
    public List<Matriculas> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Matriculas");
            rs = st.executeQuery();

            List<Matriculas> list = new ArrayList<>();

            while (rs.next()) {
                Matriculas obj = instantiateMatriculas(rs);
                list.add(obj);
                printMatriculaDData(obj);
                printMatriculaHeader();
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
    private void printMatriculaDData(Matriculas obj) {
        System.out.printf("| %-5d | %-20s | %-20s | %-15s%n",
                obj.getIdMatricula(),
                obj.getAluno().getNome(),
                obj.getCurso().getNome(),
                obj.getDataMatricula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }


    @Override
    public List<Matriculas> buscarMatriculasPorAluno(Integer idAluno) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Matriculas WHERE id_aluno = ?");
            st.setInt(1, idAluno);
            rs = st.executeQuery();

            List<Matriculas> list = new ArrayList<>();

            printMatriculaHeader();

            while (rs.next()) {
                Matriculas obj = instantiateMatriculas(rs);
                list.add(obj);
                printMatriculaData(obj);
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
    public List<Matriculas> buscarMatriculasPorCurso(Integer idCurso) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM Matriculas WHERE id_curso = ?");
            st.setInt(1, idCurso);
            rs = st.executeQuery();

            List<Matriculas> list = new ArrayList<>();

            printMatriculaHeader();

            while (rs.next()) {
                Matriculas obj = instantiateMatriculas(rs);
                list.add(obj);
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

    private Matriculas instantiateMatriculas(ResultSet rs) throws SQLException {
        Matriculas obj = new Matriculas();
        obj.setIdMatricula(rs.getInt("id_matricula"));

        Alunos aluno = new AlunoDAOJDBC(conn).buscarPorId(rs.getInt("id_aluno"));
        Cursos curso = new CursoDAOJDBC(conn).buscarPorId(rs.getInt("id_curso"));

        obj.setAluno(aluno);
        obj.setCurso(curso);
        obj.setDataMatricula(rs.getDate("data_matricula").toLocalDate());

        return obj;
    }

    private void setMatriculasData(PreparedStatement st, Matriculas obj) throws SQLException {
        st.setInt(1, obj.getAluno().getIdAluno());
        st.setInt(2, obj.getCurso().getIdCurso());
        st.setDate(3, Date.valueOf(obj.getDataMatricula()));
    }

    private void printMatriculaHeader() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-15s%n", "| ID", "| ALUNO", "| CURSO", "| DATA MATRÍCULA");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

    private void printMatriculaPorID(Matriculas obj) {
        System.out.printf("  %-5d %-20s %-20s %-15s%n",
                obj.getIdMatricula(),
                obj.getAluno().getNome(),
                obj.getCurso().getNome(),
                obj.getDataMatricula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
    private void printMatriculaData(Matriculas obj) {
        System.out.printf("  %-5d %-20s %-20s %-15s%n",
                obj.getIdMatricula(),
                obj.getAluno().getNome(),
                obj.getCurso().getNome(),
                obj.getDataMatricula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
