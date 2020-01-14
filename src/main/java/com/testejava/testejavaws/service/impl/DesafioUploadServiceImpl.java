package com.testejava.testejavaws.service.impl;

import com.testejava.testejavaws.dominio.Imagem;
import com.testejava.testejavaws.dominio.ImagemDTO;
import com.testejava.testejavaws.dominio.TipoPermitidos;
import com.testejava.testejavaws.dominio.Usuario;
import com.testejava.testejavaws.dominio.repository.ImagemRepository;
import com.testejava.testejavaws.dominio.repository.UsuarioRepository;
import com.testejava.testejavaws.exception.ImagemException;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;
import com.testejava.testejavaws.service.DesafioUploadService;
import com.testejava.testejavaws.service.UsuarioService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.testejava.testejavaws.dominio.TipoPermitidos.valuesAceitos;

import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.join;

@Service
@Transactional
public class DesafioUploadServiceImpl implements DesafioUploadService {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ImagemRepository imagemRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public String upload(final ImagemDTO imagemDTO)
            throws ImagemException, UsuarioInvalidoException, IOException {

        final Optional<Usuario> usuario = verificarUsuario(imagemDTO.getIdUsuario());

        verificarImagens(imagemDTO.getImagens());

        salvarImagens(imagemDTO, usuario);

        return "Salvo com sucesso";
    }

    private void salvarImagens(final ImagemDTO imagemDTO, final Optional<Usuario> usuario) throws IOException {

        for (final MultipartFile imagem : imagemDTO.getImagens()) {

            final byte[] imgBytesBase64 = Base64.encodeBase64(imagem.getBytes());
            final Imagem imagemSalvar = Imagem.builder()
                    .id(imagemDTO.getIdUsuario())
                    .usuario(usuario.get())
                    .imgBytesBase64(imgBytesBase64)
                    .tamanhoArquivo(imagem.getSize())
                    .nome(imagem.getOriginalFilename())
                    .tipo(imagem.getContentType())
                    .build();

            imagemRepository.save(imagemSalvar);
        }
    }

    private Optional<Usuario> verificarUsuario(Long usuaraioId) throws UsuarioInvalidoException {
        if (usuaraioId != null) {
            Optional<Usuario> usuario = usuarioService.findById(usuaraioId);
            if (usuario.isPresent()) {
                return usuario;
            }
            throw new UsuarioInvalidoException(usuaraioId.toString());
        }
        throw new UsuarioInvalidoException();
    }

    private void verificarImagens(final List<MultipartFile> imagens) throws ImagemException {
        contemImagens(imagens);
        validarTiposImagens(imagens);
    }

    private void validarTiposImagens(final List<MultipartFile> imagens) throws ImagemException {

        Collection<String> tiposNaoAceitos = new ArrayList<>();
        for (final MultipartFile imagem : imagens) {
            boolean isTipoAceito = false;
            String contentType = imagem.getContentType();
            String[] parts = contentType.split("/");

            for (final TipoPermitidos tipoPermitido : valuesAceitos()) {
                if (tipoPermitido.name().equals(parts[1].toUpperCase())) {
                    isTipoAceito = true;
                    continue;
                }
            }
            if (!isTipoAceito) {
                tiposNaoAceitos.add(parts[1].toUpperCase());
            }
        }
        if (!tiposNaoAceitos.isEmpty()) {
            throw new ImagemException("Arquivos com extensão " + join(tiposNaoAceitos) + " não são aceitos.");
        }
    }

    private void contemImagens(final List<MultipartFile> imagens) throws ImagemException {
        if(imagens.isEmpty()){
            throw new ImagemException("Não foi recebido nenhuma imagem.");
        }
    }

    public Collection<Imagem> findAll(){
        return this.imagemRepository.findAll();
    }

    public Collection<Imagem> findByUsuario(Long idUsuario) throws UsuarioInvalidoException {
        Optional<Usuario> usuario = this.usuarioRepository.findById(idUsuario);
        if (usuario.isPresent()) {
            return this.imagemRepository.findByUsuario((Usuario) usuario.get());
        }
        throw new UsuarioInvalidoException("Usuário inávildo: " + idUsuario);
    }

    public Collection<Imagem> findByNome(String nomeImagem) {
        return this.imagemRepository.findByNome(nomeImagem);
    }

    @Override
    public void removerImagemPorId(Long imagemId) throws ImagemException {
        final Optional<Imagem> imagem = this.imagemRepository.findById(imagemId);
        if(!imagem.isPresent()){
            throw new ImagemException("Imagem com id '" + imagemId.toString()+ "' não encontrada. Não foi possivel remover.");
        }
        this.imagemRepository.deleteById(imagemId);
    }
}
