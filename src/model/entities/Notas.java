package model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Notas {
    private Integer idNota;
    private Matriculas matricula; // Referência para a entidade Matricula
    private BigDecimal nota;
    private LocalDate dataAvaliacao;

    // Construtor
    public Notas() {

    }
    public Notas(Integer idNota, Matriculas matricula, BigDecimal nota, LocalDate dataAvaliacao) {
        this.idNota = idNota;
        this.matricula = matricula;
        this.nota = nota;
        this.dataAvaliacao = dataAvaliacao;
    }

    // Getters e Setters

    public Integer getIdNota() {
        return idNota;
    }

    public void setIdNota(Integer idNota) {
        this.idNota = idNota;
    }

    public Matriculas getMatricula() {
        return matricula;
    }

    public void setMatricula(Matriculas matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notas notas = (Notas) o;
        return Objects.equals(idNota, notas.idNota);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idNota);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Matrícula: %d, Nota: %.2f, Data da Avaliação: %s",
                getIdNota(), matricula.getIdMatricula(), getNota(), getDataAvaliacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
