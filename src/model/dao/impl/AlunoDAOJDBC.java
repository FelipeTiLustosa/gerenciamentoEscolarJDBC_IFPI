package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import db.DbIntegrityException;
import model.dao.AlunoDAO;
import model.entities.Alunos;

public class AlunoDAOJDBC implements AlunoDAO {

    private Connection conn;

    public AlunoDAOJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void inserir(Alunos obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO Alunos (nome, data_nascimento, endereco, telefone, email) " +
                            "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            setAlunosData(st, obj);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setIdAluno(id);
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
    public void atualizar(Alunos obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE Alunos SET nome = ?, data_nascimento = ?, endereco = ?, telefone = ?, email = ? " +
                            "WHERE id_aluno = ?");

            setAlunosData(st, obj);
            st.setInt(6, obj.getIdAluno());

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
            st = conn.prepareStatement("DELETE FROM Alunos WHERE id_aluno = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbIntegrityException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Alunos buscarPorId(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Alunos WHERE id_aluno = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Alunos obj = instantiateAlunos(rs);
                printAlunoHeader();
                printAlunoData(obj);
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
    public List<Alunos> buscarTodos() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM Alunos ORDER BY nome");
            rs = st.executeQuery();

            List<Alunos> list = new ArrayList<>();

            printAlunoHeader();

            while (rs.next()) {
                Alunos obj = instantiateAlunos(rs);
                list.add(obj);
                printAlunoData(obj);
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
    public List<Alunos> buscarAlunosPorCurso(Integer idCurso) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT a.* FROM Alunos a " +
                            "INNER JOIN Matriculas m ON a.id_aluno = m.id_aluno " +
                            "WHERE m.id_curso = ?");

            st.setInt(1, idCurso);
            rs = st.executeQuery();

            List<Alunos> list = new ArrayList<>();

            printAlunoHeader();

            while (rs.next()) {
                Alunos obj = instantiateAlunos(rs);
                list.add(obj);
                printAlunoData(obj);
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

    private Alunos instantiateAlunos(ResultSet rs) throws SQLException {
        Alunos obj = new Alunos();
        obj.setIdAluno(rs.getInt("id_aluno"));
        obj.setNome(rs.getString("nome"));
        obj.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        obj.setEndereco(rs.getString("endereco"));
        obj.setTelefone(rs.getString("telefone"));
        obj.setEmail(rs.getString("email"));
        return obj;
    }

    private void setAlunosData(PreparedStatement st, Alunos obj) throws SQLException {
        st.setString(1, obj.getNome());
        st.setDate(2, java.sql.Date.valueOf(obj.getDataNascimento()));
        st.setString(3, obj.getEndereco());
        st.setString(4, obj.getTelefone());
        st.setString(5, obj.getEmail());
    }

    private void printAlunoHeader() {
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-12s %-25s %-15s %-30s%n", "| ID", "| NOME", "| DATA NASC.", "| ENDEREÇO", "| TEL.", "| EMAIL                       |");
        System.out.println("----- -------------------- ------------ ------------------------- --------------- ------------------------------");
    }

    private void printAlunoData(Alunos obj) {
        System.out.printf("  %-5d %-20s %-12s %-25s %-15s %-30s%n",
                obj.getIdAluno(),
                obj.getNome(),
                obj.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                obj.getEndereco(),
                obj.getTelefone(),
                obj.getEmail());
    }
}
