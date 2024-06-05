package br.com.projeto.api.service;

import br.com.projeto.api.model.Aluno;
import br.com.projeto.api.repositorio.Repositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private Repositorio repositorio;

    public Aluno cadastrarAluno(Aluno aluno) {
        return repositorio.save(aluno);
    }

    public List<Aluno> listarAlunos() {
        return repositorio.findAllByOrderByNome();
    }

    public Optional<Aluno> buscarAlunoPorCpf(String cpf) {
        return repositorio.findByCpf(cpf);
    }

    public void excluirAlunoPorCpf(String cpf) {
        repositorio.deleteByCpf(cpf);
    }

    public Optional<Aluno> editarAluno(Aluno aluno) {
        if (repositorio.existsById(aluno.getCodigo())) {
            return Optional.of(repositorio.save(aluno));
        } else {
            return Optional.empty(); // aluno não encontrado
        }
    }
}