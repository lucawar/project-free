package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Slf4j
@Table(name = "Utenti")
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Long id;

    private String nome;

    private String cognome;

    private String email;

    private LocalDateTime dataCreazione;

    private LocalDateTime ultimoAggiornamento;



    @PrePersist
    protected void onCreate() {
        dataCreazione = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        ultimoAggiornamento = LocalDateTime.now();
    }
}
