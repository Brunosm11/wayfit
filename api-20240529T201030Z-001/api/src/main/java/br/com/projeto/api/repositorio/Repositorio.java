package br.com.projeto.api.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projeto.api.model.Aluno;
import java.util.List;
import java.util.Optional;

@Repository
public interface Repositorio extends JpaRepository<Aluno, Integer> {

  Optional<Aluno> findByCpf(String cpf);

  List<Aluno> findAllByOrderByNome();

  void deleteByCpf(String cpf);
}
