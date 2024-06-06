<template>
    <div class="lista-alunos">
      <ul>
        <li v-for="aluno in alunos" :key="aluno.id">
          <strong>Código:</strong> {{ aluno.codigo }}<br>
          <strong>Nome:</strong> {{ aluno.nome }}<br>
          <strong>CPF:</strong> {{ aluno.cpf }}<br>
          <strong>Idade:</strong> {{ aluno.idade }}<br>
          <strong>Sexo:</strong> {{ aluno.sexo }}<br>
          <strong>Treino:</strong> {{ aluno.treino }}<br>
          <strong>E-mail:</strong> {{ aluno.email }}<br>
          <strong>Telefone:</strong> {{ aluno.telefone }}
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      alunos: {
        type: Array,
        required: true
      }
    }
  };
  </script>
  
  <style scoped>
  .lista-alunos {
    max-width: 400px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  h2 {
    margin-bottom: 10px;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    margin-bottom: 10px;
    border-bottom: 1px solid #ccc;
    padding-bottom: 10px;
  }
  </style>
  
