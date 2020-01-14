package com.testejava.testejavaws.dominio.repository;

import com.testejava.testejavaws.dominio.Imagem;
import com.testejava.testejavaws.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    Collection<Imagem> findByUsuario(Usuario usuario);

    Collection<Imagem> findByNome(String nomeImagem);
}
