package br.com.spring.angular.controller;

import br.com.spring.angular.model.Usuario;
import br.com.spring.angular.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/init")
    public ResponseEntity init(@RequestParam(value = "nome", defaultValue = "Nome não informado", required = true) String nome) { //required = false nome == null
        System.out.println(nome);
        return new ResponseEntity("Olá " + nome, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity usuario(@RequestParam(value = "nome", defaultValue = "Nome não informado", required = true) String nome) { //required = false nome == null

        Usuario usuario = new Usuario();
        usuario.setId(50L);
        usuario.setNome("João");
        usuario.setLogin("jotape");
        usuario.setSenha("12345");

        Usuario usuario2 = new Usuario();
        usuario2.setId(50L);
        usuario2.setNome("João Paulo");
        usuario2.setLogin("jotape");
        usuario2.setSenha("12345");

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        usuarios.add(usuario2);

        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> init2(@PathVariable(value = "id") Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Usuario>> findAll(){
       List<Usuario> list = usuarioRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario ) {

        Usuario usuarioSalvo  = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario ) {
        //save tmb atualiza(Tem que passar o id se não cria um usuári novo, opcional usar @PathVariable)
        Usuario usuarioSalvo  = usuarioRepository.save(usuario);
        return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagarPorId(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

