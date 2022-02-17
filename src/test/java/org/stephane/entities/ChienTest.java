package org.stephane.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;

class ChienTest {
    @Test
    void constructor() {
        Chien actualChien = new Chien();
        actualChien.setGenre("Genre");
        actualChien.setLocalite("Localite");
        actualChien.setNomAnimal("Nom Animal");
        actualChien.setRace("Race");
        actualChien.setRef("Ref");
        actualChien.setStatut("Statut");
        then(actualChien.getGenre()).hasToString("Genre");
        then(actualChien.getLocalite()).hasToString("Localite");
        then(actualChien.getNomAnimal()).hasToString("Nom Animal");
        then(actualChien.getRace()).hasToString("Race");
        then(actualChien.getRef()).hasToString("Ref");
        then(actualChien.getStatut()).hasToString("Statut");
    }
}

