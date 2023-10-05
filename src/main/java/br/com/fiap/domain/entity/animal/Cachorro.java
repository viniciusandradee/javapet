package br.com.fiap.domain.entity.animal;

import br.com.fiap.domain.entity.pessoa.Pessoa;

public class Cachorro extends Animal{


    public Cachorro() {
        super("CACHORRO");
    }

    public Cachorro(Long id, String nome, String raca, String descricao, Pessoa dono) {
        super( id, nome, raca, descricao, dono, "CACHORRO" );
    }

    @Override
    public String toString() {
        return "Cachorro{} " + super.toString();
    }
}
