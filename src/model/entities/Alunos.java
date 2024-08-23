package model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alunos implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer idAluno;
    private String nome;
    private LocalDate dataNascimento;
    private String endereco;
    private String telefone;
    private String email;

    // Construtor

    public Alunos(){

    }
    public Alunos(Integer idAluno, String nome, LocalDate dataNascimento, String endereco, String telefone, String email) {
        this.idAluno = idAluno;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
    }

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alunos alunos = (Alunos) o;
        return Objects.equals(idAluno, alunos.idAluno);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idAluno);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Nome: %s, Data de Nascimento: %s, Endereço: %s, Telefone: %s, Email: %s",
                getIdAluno(), getNome(), getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), getEndereco(), getTelefone(), getEmail());
    }
}
