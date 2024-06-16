package model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.Util;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Slf4j
@Table(name = "Utenti")
public class User {

    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "user_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Long id;

    @Column(name = "user_uuid", updatable = false, nullable = false, unique = true)
    private UUID userUuid;

    @Column(name = "user_nome")
    private String nome;

    @Column(name = "user_cognome")
    private String cognome;

    @Column(name = "user_email")
    private String email;

    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;

    @Column(name = "ultimo_update")
    private LocalDateTime ultimoAggiornamento;


    @PrePersist
    protected void onCreate() {
        dataCreazione = LocalDateTime.now();
        ultimoAggiornamento = dataCreazione;
        userUuid = UUID.randomUUID();
        this.nome = Util.capitalizeFirstLetter(nome);
        this.cognome = Util.capitalizeFirstLetter(cognome);
    }

    @PreUpdate
    protected void onUpdate() {
        if (ultimoAggiornamento == null) {
            ultimoAggiornamento = dataCreazione;
        } else {
            ultimoAggiornamento = LocalDateTime.now();
        }
        this.nome = Util.capitalizeFirstLetter(nome);
        this.cognome = Util.capitalizeFirstLetter(cognome);
    }
}
