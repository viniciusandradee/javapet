package br.com.fiap.domain.entity.servico;

import br.com.fiap.domain.entity.animal.Animal;

import java.time.LocalDate;

public class Vacina extends Servico {

    public Vacina() {
        super( "VACINA" );
    }

    public Vacina(Long id, String descricao, Animal animal, LocalDate realizacao) {
        super( id, descricao, animal, realizacao, "VACINA" );
    }

    @Override
    public String toString() {
        return "Vacina{} " + super.toString();
    }
}
