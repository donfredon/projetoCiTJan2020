package com.testejava.testejavaws.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.CompareToBuilder;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import static lombok.AccessLevel.PUBLIC;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor(access = PUBLIC)
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USUARIO_ID")
    private Long id;

    @Column(name = "USUARIO_NAME")
    private String name;

    @Column(name = "USUARIO_EMAIL")
    private String email;

    public Usuario() {
    }


}
