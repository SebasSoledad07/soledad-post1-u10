package com.universidad.pruebas;

import com.universidad.pruebas.model.Tarea;
import com.universidad.pruebas.respository.TareaRepository;
import com.universidad.pruebas.service.TareaService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TareaServiceTest {
    @Mock
    TareaRepository repo;
    @InjectMocks
    TareaService service;

    @Test
    void crear_conTituloValido_guardaYRetorna() {
        Tarea t = new Tarea(); t.setTitulo("Estudiar JUnit");
        when(repo.save(any())).thenReturn(t);
        assertThat(service.crear(t).getTitulo()).isEqualTo("Estudiar JUnit");
        verify(repo).save(t);
    }

    @Test
    void crear_conTituloVacio_lanzaIllegalArgumentException() {
        Tarea t = new Tarea(); t.setTitulo(" ");
        assertThrows(IllegalArgumentException.class, () -> service.crear(t));
        verify(repo, never()).save(any());
    }

    @Test
    void buscarPorId_noExiste_lanzaEntityNotFoundException() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.buscarPorId(99L));
    }

    @Test
    void completar_cambiaEstadoYGuarda() {
        Tarea t = new Tarea(); t.setId(5L); t.setTitulo("Ir al gym"); t.setCompletada(false);
        when(repo.findById(5L)).thenReturn(Optional.of(t));
        when(repo.save(any())).thenAnswer(inv -> inv.getArgument(0));

        Tarea resultado = service.completar(5L);
        assertThat(resultado.isCompletada()).isTrue();
        verify(repo).save(t);
    }
}