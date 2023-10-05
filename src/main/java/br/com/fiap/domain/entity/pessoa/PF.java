package br.com.fiap.domain.entity.pessoa;

import java.time.LocalDate;

public class PF extends Pessoa {

    private String CPF;


    public PF() {
        super( "PF" );
    }

    public PF(String CPF) {
        super( "PF" );
        this.CPF = CPF;
    }

    public PF(Long id, String nome, LocalDate nascimento, String CPF) {
        super( id, nome, nascimento, "PF" );
        this.CPF = CPF;
    }

    public String getCPF() {
        return CPF;
    }

    public PF setCPF(String CPF) {
        this.CPF = CPF;
        return this;
    }

    @Override
    public String toString() {
        return "PF{" +
                "CPF='" + CPF + '\'' +
                "} " + super.toString();
    }
}
