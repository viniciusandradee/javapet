package br.com.fiap.domain.entity.servico;

import br.com.fiap.domain.entity.animal.Animal;

import java.time.LocalDate;

public class Tosa extends Servico {


    public Tosa() {
        super( "TOSA" );
    }

    public Tosa(Long id, String descricao, Animal animal, LocalDate realizacao) {
        super( id, descricao, animal, realizacao, "TOSA" );
    }

    @Override
    public String toString() {
        return "Tosa{} " + super.toString();
    }
}
