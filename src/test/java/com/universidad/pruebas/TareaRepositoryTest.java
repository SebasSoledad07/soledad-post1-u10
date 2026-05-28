package com.universidad.pruebas;

import com.universidad.pruebas.model.Tarea;
import com.universidad.pruebas.respository.TareaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class TareaRepositoryTest {
    @Autowired
    TareaRepository repo;
    @Autowired
    TestEntityManager em;
    @BeforeEach
    void setUp() {
        Tarea t = new Tarea(); t.setTitulo("Pendiente");
        t.setCompletada(false);
        em.persistAndFlush(t);
    }
    @Test
    void findByCompletada_false_retornaUnaTarea() {
        assertThat(repo.findByCompletada(false)).hasSize(1)
                .extracting("titulo").containsExactly("Pendiente");
    }
}
