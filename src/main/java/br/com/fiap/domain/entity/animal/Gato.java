package br.com.fiap.domain.entity.animal;

import br.com.fiap.domain.entity.pessoa.Pessoa;

public class Gato extends Animal{

    public Gato() {
        super("GATO");
    }

    public Gato(Long id, String nome, String raca, String descricao, Pessoa dono) {
        super( id, nome, raca, descricao, dono, "GATO" );
    }

    @Override
    public String toString() {
        return "Gato{} " + super.toString();
    }
}
