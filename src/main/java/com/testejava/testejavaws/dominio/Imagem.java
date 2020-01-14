package com.testejava.testejavaws.dominio;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Blob;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Table(name = "IMAGEM")
public class Imagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "TIPO")
    private String tipo;

    @NotNull
    @Column(name = "SIZE")
    private Long tamanhoArquivo;

    @NotNull
    @Column(name = "BYTES")
    private byte[] imgBytesBase64;

    @ManyToOne
    @JoinColumn(name = "DU_USUARIO_ID")
    private Usuario usuario;
}
