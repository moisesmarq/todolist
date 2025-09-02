package br.com.moisesmarques.todolist.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/**
     * 
     * Usuário (id_Usuario)
     * Descrição
     * Titulo
     * Data de Inicio
     * Data de Termino
     * Prioridade
     * 
     */

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public void setIdUser(UUID idUser){
        this.idUser = idUser;
    }
}
