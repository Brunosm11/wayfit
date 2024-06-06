<template>
  <div id="app">
    <div class="header">
      <!-- Adiciona a logo na barra de cabeçalho -->
      <img src="@/assets/wayfit-logo.png" alt="WayFit Logo" class="logo">
    </div>

    <div class="container">
      <div class="centered-buttons">
        <button @click="exibirFormulario" class="btn btn-primary">Cadastrar Aluno</button>
        <button v-if="mostrarFormulario" @click="fecharFormulario" class="btn btn-close">Fechar <i class="fas fa-times"></i></button>
        <button @click="listarAlunos" class="btn btn-primary">Listar Alunos</button>
        <button v-if="!mostrarBusca" @click="exibirBusca" class="btn btn-primary">Buscar por CPF</button>
        <button @click="exibirFormulariodeedição" class="btn btn-edit">Editar Alunos</button>
      </div>

      <cadastro-aluno v-if="mostrarFormulario" @cadastrar="cadastrarAluno"></cadastro-aluno>

      <div v-if="mostrarLista" class="lista-alunos">
        <h2 class="titulo">Lista de Alunos</h2>
        <button @click="fecharLista" class="btn btn-close">Fechar</button>
        <lista-alunos :alunos="listaAlunos" />
      </div>

      <div v-if="mostrarBusca" class="centered-container">
        <input type="text" v-model="cpfConsulta" placeholder="Digite o CPF" class="cpf-input form-control" />
        <button @click="consultarPorCPF" class="btn btn-primary">Consultar</button>
        <button @click="deletarAluno" class="btn btn-danger">Deletar</button>
        <!-- Adiciona o botão Fechar para a barra de consulta -->
        <button @click="fecharBusca" class="btn btn-close">Fechar</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import CadastroAluno from './components/CadastroAluno.vue';
import ListaAlunos from './components/ListaAlunos.vue';

export default {
  name: 'App',
  components: {
    CadastroAluno,
    ListaAlunos
  },
  data() {
    return {
      mostrarFormulario: false,
      mostrarLista: false,
      listaAlunos: [],
      mostrarBusca: false,
      cpfConsulta: ''
    };
  },
  methods: {
    exibirFormulario() {
      this.mostrarFormulario = true;
      this.mostrarLista = false;
      this.mostrarBusca = false;
    },
    async cadastrarAluno(aluno) {
      try {
        const response = await axios.post('http://localhost:8081/alunos/cadastrar', aluno);
        console.log(response.data);
        this.mostrarFormulario = false;
        await this.listarAlunos();
      } catch (error) {
        console.error('Erro ao cadastrar aluno:', error);
      }
    },
    fecharFormulario() {
      this.mostrarFormulario = false;
    },
    async listarAlunos() {
      try {
        const response = await axios.get('http://localhost:8081/alunos/listar');
        this.listaAlunos = response.data;

        this.listaAlunos.sort((a, b) => {
          return a.codigo - b.codigo;
        });

        this.mostrarLista = true;
        this.mostrarFormulario = false;
        this.mostrarBusca = false;
      } catch (error) {
        console.error('Erro ao listar alunos:', error);
      }
    },
    fecharLista() {
      this.mostrarLista = false;
    },
    exibirBusca() {
      this.mostrarBusca = true;
      this.mostrarLista = false;
      this.mostrarFormulario = false;
    },
    fecharBusca() {
      this.mostrarBusca = false;
    },
    async consultarPorCPF() {
      try {
        if (!this.cpfConsulta) {
          console.error('CPF não pode estar vazio.');
          return;
        }

        const response = await axios.get(`http://localhost:8081/alunos/buscar/cpf/${this.cpfConsulta}`);
        console.log('Resposta do servidor:', response.data);
        this.cpfConsulta = '';
      } catch (error) {
        console.error('Erro ao consultar por CPF:', error);
      }
    },
    async deletarAluno() {
      try {
        if (!this.cpfConsulta) {
          console.error('CPF não pode estar vazio.');
          return;
        }

        const response = await axios.delete(`http://localhost:8081/alunos/excluir/${this.cpfConsulta}`);
        console.log('Resposta do servidor:', response.data);
        this.cpfConsulta = '';
        alert('Aluno excluído com sucesso.');
      } catch (error) {
        console.error('Erro ao deletar aluno:', error);
      }
    
    }
  }
};

</script>

<style scoped>
.header {
  position: fixed;
  top: 20px;
  left: 20px;
}

.logo {
  width: 250px; /* Defina o tamanho adequado para sua logo */
}

.container {
  text-align: center;
  margin-top: 100px; /* Ajuste a margem superior conforme necessário */
}

.centered-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.centered-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 20px;
}

.btn {
  margin: 5px;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
  transition: all 0.3s ease;
}

.btn:hover {
  opacity: 0.8;
}

.btn-edit {
  background-color: #007bff;
  color: #fff;
}

.btn-primary {
  background-color: #007bff;
  color: #fff;
}

.btn-secondary {
  background-color: #6c757d;
  color: #fff;
}

.btn-danger {
  background-color: hsl(0, 100%, 50%);
  color: #fff;
}

/* Estilo para o botão Fechar */
.btn-close {
  background-color: hsl(0, 100%, 50%);
  color: #fff;
}

.form-control {
  width: 200px;
  margin-right: 10px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.cpf-input {
  width: 200px;
  padding: 8px;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.lista-alunos {
  margin-top: 20px;
}

.lista-alunos ul {
  list-style: none;
  padding: 0;
}

.lista-alunos li {
  background-color: #f4f4f4;
  padding: 10px;
  border-radius: 5px;
  margin-bottom: 5px;
}

.barra-consulta {
  margin-bottom: 20px;
}
</style>

