package br.com.fiap.domain.entity.servico;

import br.com.fiap.domain.entity.animal.Animal;

import java.time.LocalDate;

public class Banho extends Servico{


    public Banho() {
        super("BANHO");
    }

    public Banho(Long id, String descricao, Animal animal, LocalDate realizacao) {
        super( id, descricao, animal, realizacao, "BANHO" );
    }

    @Override
    public String toString() {
        return "Banho{} " + super.toString();
    }
}
