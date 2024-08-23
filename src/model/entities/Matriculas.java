package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Matriculas implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idMatricula;
    private Alunos aluno; // Referência para a entidade Aluno
    private Cursos curso; // Referência para a entidade Curso
    private LocalDate dataMatricula;

    // Construtor
    public Matriculas() {

    }
    public Matriculas(Integer idMatricula, Alunos aluno, Cursos curso, LocalDate dataMatricula) {
        this.idMatricula = idMatricula;
        this.aluno = aluno;
        this.curso = curso;
        this.dataMatricula = dataMatricula;
    }

    // Getters e Setters


    public Integer getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Alunos getAluno() {
        return aluno;
    }

    public void setAluno(Alunos aluno) {
        this.aluno = aluno;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matriculas that = (Matriculas) o;
        return Objects.equals(idMatricula, that.idMatricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idMatricula);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Aluno: %s, Curso: %s, Data da Matrícula: %s",
                getIdMatricula(), aluno.getNome(), curso.getNome(), getDataMatricula().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
