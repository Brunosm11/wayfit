<template>
    <div class="cadastro-aluno">
      <form @submit.prevent="submitForm">
        <div class="form-group">
          <label for="codigo">Código:</label>
          <input type="text" id="codigo" v-model="aluno.codigo">
        </div>
        <div class="form-group">
          <label for="nome">Nome:</label>
          <input type="text" id="nome" v-model="aluno.nome" required>
        </div>
        <div class="form-group">
          <label for="cpf">CPF:</label>
          <input type="text" id="cpf" v-model="aluno.cpf" required>
        </div>
        <div class="form-group">
          <label for="idade">Idade:</label>
          <input type="number" id="idade" v-model="aluno.idade" required>
        </div>
        <div class="form-group">
          <label for="treino">Treino:</label>
          <input type="text" id="treino" v-model="aluno.treino" required>
        </div>
        <div class="form-group">
          <label for="email">E-mail:</label>
          <input type="email" id="email" v-model="aluno.email" required>
        </div>
        <div class="form-group">
          <label for="telefone">Telefone:</label>
          <input type="text" id="telefone" v-model="aluno.telefone" required>
        </div>
        <button type="submit">Cadastrar</button>
      </form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        aluno: {
          codigo: '',
          nome: '',
          cpf: '',
          idade: '',
          treino: '',
          email: '',
          telefone: ''
        }
      };
    },
    methods: {
      submitForm() {
        // Emitir um evento 'cadastrar' com os dados do aluno
        this.$emit('cadastrar', this.aluno);
        // Limpar os campos do formulário após o envio
        this.limparCampos();
      },
      limparCampos() {
        this.aluno = {
          codigo: '',
          nome: '',
          cpf: '',
          idade: '',
          treino: '',
          email: '',
          telefone: ''
        };
      }
    }
  };
  </script>
  
  <style scoped>
  .cadastro-aluno {
    max-width: 400px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
  }
  
  input[type="text"],
  input[type="number"],
  select,
  button {
    width: 100%;
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 3px;
  }
  
  button {
    background-color: #007bff;
    color: #fff;
    border: none;
    cursor: pointer;
  }
  
  </style>