package com.testejava.testejavaws.dominio;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
public class ImagemDTO {

    private List<MultipartFile> imagens;
    private Long idUsuario;
}
