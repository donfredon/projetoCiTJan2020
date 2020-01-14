package com.testejava.testejavaws.controller;

import com.testejava.testejavaws.dominio.Imagem;
import com.testejava.testejavaws.dominio.ImagemDTO;
import com.testejava.testejavaws.exception.ImagemException;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;
import com.testejava.testejavaws.service.DesafioUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/imagem")
public class ImagemRestController {

    @Autowired
    DesafioUploadService desafioUploadService;

    @PostMapping(value = "/upload")
    public ResponseEntity<String> upload(@RequestParam (required = true) List<MultipartFile> imagens,
                                         @RequestParam (required = true) Long usuarioId)
                                            throws ImagemException, UsuarioInvalidoException, IOException {

        return ResponseEntity.ok(desafioUploadService.upload(ImagemDTO.builder()
                .imagens(imagens)
                .idUsuario(usuarioId)
                .build()));
    }

    @RequestMapping(method = GET, value = "/usuario/{usuarioId}/historico")
    public ResponseEntity<Object> obterPorUsuario(@PathVariable(required = true) Long usuarioId)
            throws UsuarioInvalidoException {
        final Collection<Imagem> imagems = this.desafioUploadService.findByUsuario(usuarioId);
        if (imagems.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(imagems);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<Collection<Imagem>> obterTodasImagens() {
        final Collection<Imagem> imagems = this.desafioUploadService.findAll();
        if (imagems.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(imagems);
    }

    @RequestMapping(method = GET, value = "{nomeImagem}")
    public ResponseEntity<Collection<Imagem>> obterImagemPorNome(@PathVariable(name = "nomeImagem") String nomeImagem) {
        final Collection<Imagem> imagems = this.desafioUploadService.findByNome(nomeImagem);
        if (imagems.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(imagems);
    }

    @RequestMapping(method = DELETE, value = "{imagemId}/delete")
    public ResponseEntity<Object> removerImagemPorId(@PathVariable(required = true) Long imagemId)
            throws ImagemException {
        this.desafioUploadService.removerImagemPorId(imagemId);
        return ResponseEntity.accepted().build();
    }
}
