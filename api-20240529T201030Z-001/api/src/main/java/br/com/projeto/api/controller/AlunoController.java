package br.com.projeto.api.controller;

import br.com.projeto.api.model.Aluno;
import br.com.projeto.api.repositorio.Repositorio;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private Repositorio acao;

    @PostMapping("/cadastrar")
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno) {
        Aluno novoAluno = acao.save(aluno);
        return ResponseEntity.ok(novoAluno); // aluno cadastrado
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> alunos = acao.findAllByOrderByNome();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/buscar/{cpf}")
    public ResponseEntity<Aluno> buscarPorCpf(@PathVariable String cpf) {
        Optional<Aluno> aluno = acao.findByCpf(cpf);
        return aluno.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @DeleteMapping("/excluir/{cpf}")
    public ResponseEntity<Void> excluir(@PathVariable String cpf) {
        Optional<Aluno> alunoOptional = acao.findByCpf(cpf);
        if (alunoOptional.isPresent()) {
            acao.delete(alunoOptional.get());
            return ResponseEntity.ok().build(); // aluno excluído com sucesso
        } else {
            return ResponseEntity.notFound().build(); // aluno não encontrado
        }
    }

    @PutMapping("/editar")
    public ResponseEntity<Aluno> editar(@RequestBody Aluno aluno) {
        if (acao.existsById(aluno.getCodigo())) {
            Aluno alunoEditado = acao.save(aluno);
            return ResponseEntity.ok(alunoEditado);
        } else {
            return ResponseEntity.status(404).body(null); // aluno não encontrado
        }
    }
}