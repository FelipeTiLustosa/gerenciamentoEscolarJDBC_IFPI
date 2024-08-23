package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Cursos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCurso;
    private String nome;
    private String descricao;
    private Integer duracaoHoras;
    private Professores professores; // Referência para a entidade Professor

    // Construtor
    public  Cursos(){

    }
    public Cursos(Integer idCurso, String nome, String descricao, Integer duracaoHoras, Professores professores) {
        this.idCurso = idCurso;
        this.nome = nome;
        this.descricao = descricao;
        this.duracaoHoras = duracaoHoras;
        this.professores = professores;
    }

    // Getters e Setters


    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(Integer duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    public Professores getProfessores() {
        return professores;
    }

    public void setProfessores(Professores professores) {
        this.professores = professores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cursos cursos = (Cursos) o;
        return Objects.equals(idCurso, cursos.idCurso);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCurso);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nome: %s, Descrição: %s, Duração (horas): %d, Professores: %s",
                getIdCurso(), getNome(), getDescricao(), getDuracaoHoras(), professores.getNome());
    }
}
