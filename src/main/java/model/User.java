package model;

import enums.EuserGenderEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import utils.StringFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Slf4j
@Table(name = "utenti")
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

    @Column(name = "user_data_nascita")
    private LocalDate dataNascita;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_sesso")
    @Enumerated(EnumType.STRING)
    private EuserGenderEnum sesso;

    @Column(name = "data_creazione")
    private LocalDateTime dataCreazione;

    @Column(name = "ultimo_update")
    private LocalDateTime ultimoAggiornamento;


    @PrePersist
    protected void onCreate() {
        dataCreazione = LocalDateTime.now();
        ultimoAggiornamento = dataCreazione;
        userUuid = UUID.randomUUID();
        this.nome = StringFormatter.formatNameOrSurname(nome);
        this.cognome = StringFormatter.formatNameOrSurname(cognome);
    }

    @PreUpdate
    protected void onUpdate() {
        if (ultimoAggiornamento == null) {
            ultimoAggiornamento = dataCreazione;
        } else {
            ultimoAggiornamento = LocalDateTime.now();
        }
        this.nome = StringFormatter.formatNameOrSurname(nome);
        this.cognome = StringFormatter.formatNameOrSurname(cognome);
    }
}
