package com.testejava.testejavaws;

import com.testejava.testejavaws.dominio.Usuario;
import com.testejava.testejavaws.exception.UsuarioInvalidoException;
import com.testejava.testejavaws.service.UsuarioService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteJavaWsApplicationTests {

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void teste1CriarUsuario() {
        Usuario usuario = Usuario.builder().name("Fred").email("nedson@teste.com.br").build();
        usuarioService.create(usuario);

        Usuario usuario1 = Usuario.builder().name("Nedson").email("nedson@teste.com.br").build();
        Usuario savedUser = usuarioService.create(usuario1);

    }

    @Test
    public void teste3UpdateUsuario() throws UsuarioInvalidoException {
        Usuario usuario = Usuario.builder().name("Luana").email("luana@teste.com.br").build();
        Usuario savedUser = usuarioService.create(usuario);
        Usuario oldUser = Usuario.builder().name("Luana Marques").email("luana@teste.com.br").build();
        Usuario updatedUsuario = usuarioService.updateUsuario(oldUser, 3L);
        String nome = updatedUsuario.getName();
        Assert.assertEquals("Luana Marques", nome );
    }

    @Test
    public void teste4ObterUsuarios() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        Assert.assertEquals(3, usuarios.size());

        final Usuario user2 = usuarios.get(2);
        String nome = user2.getName();
        Assert.assertEquals("Luana Marques", nome);

    }

    @Test
    public void teste5DeleteUsuario() {
        List<Usuario> usuarios = usuarioService.getUsuarios();
        Assert.assertEquals(3, usuarios.size());

        usuarioService.deleteUsuario(2L);
        usuarios = usuarioService.getUsuarios();
        Assert.assertEquals(2, usuarios.size());
    }


}
