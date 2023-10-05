package br.com.fiap.domain.entity.servico;

import br.com.fiap.domain.entity.animal.Animal;

import java.time.LocalDate;

public class Consulta extends Servico{


    public Consulta() {
        super("CONSULTA");
    }

    public Consulta(Long id, String descricao, Animal animal, LocalDate realizacao) {
        super( id, descricao, animal, realizacao, "CONSULTA" );
    }

    @Override
    public String toString() {
        return "Consulta{} " + super.toString();
    }
}
