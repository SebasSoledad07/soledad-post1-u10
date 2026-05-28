package com.universidad.pruebas;

import com.universidad.pruebas.model.Tarea;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TareaModelTest {

    @Test
    void gettersYSetters_funcionan_y_completadaPorDefectoEsFalse() {
        Tarea tarea = new Tarea();

        assertThat(tarea.isCompletada()).isFalse();
        assertThat(tarea.getFechaCreacion()).isNull();

        tarea.setId(10L);
        tarea.setTitulo("Comprar leche");
        tarea.setDescripcion("Leche, pan y huevos");
        tarea.setCompletada(true);

        assertThat(tarea.getId()).isEqualTo(10L);
        assertThat(tarea.getTitulo()).isEqualTo("Comprar leche");
        assertThat(tarea.getDescripcion()).isEqualTo("Leche, pan y huevos");
        assertThat(tarea.isCompletada()).isTrue();
    }
}

