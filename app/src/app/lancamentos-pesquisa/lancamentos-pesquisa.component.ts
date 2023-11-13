import { Component } from '@angular/core';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css'],
})
export class LancamentosPesquisaComponent {
  lancamentos = [
    {
      tipo: 'DESPESA',
      descricao: 'Compra de pão',
      dataVencimento: new Date(2017, 4, 20),
      dataPagamento: null,
      valor: 40.3,
      pessoa: 'José das Neves Vieira',
    },
    {
      tipo: 'DESPESA',
      descricao: 'Pagamento de conta de luz',
      dataVencimento: new Date(2017, 4, 25),
      dataPagamento: new Date(2017, 4, 24),
      valor: 120.0,
      pessoa: 'Maria Silva Penha',
    },
    {
      tipo: 'RECEITA',
      descricao: 'Salário',
      dataVencimento: new Date(2017, 5, 1),
      dataPagamento: new Date(2017, 5, 2),
      valor: 3000.0,
      pessoa: 'João Teste da Silva',
    },
    {
      tipo: 'DESPESA',
      descricao: 'Compra de mantimentos',
      dataVencimento: new Date(2017, 5, 15),
      dataPagamento: null,
      valor: 150.5,
      pessoa: 'Ana Moreira',
    },
    {
      tipo: 'RECEITA',
      descricao: 'Salário',
      dataVencimento: new Date(2017, 5, 1),
      dataPagamento: new Date(2017, 5, 2),
      valor: 3000.0,
      pessoa: 'João Santos',
    },
    {
      tipo: 'DESPESA',
      descricao: 'Compra de mantimentos',
      dataVencimento: new Date(2017, 5, 15),
      dataPagamento: null,
      valor: 150.5,
      pessoa: 'Ana Souza',
    },
    {
      tipo: 'DESPESA',
      descricao: 'Aluguel',
      dataVencimento: new Date(2017, 6, 10),
      dataPagamento: null,
      valor: 800.0,
      pessoa: 'Carlos Lima',
    },
    {
      tipo: 'RECEITA',
      descricao: 'Freelance de programação',
      dataVencimento: new Date(2017, 7, 5),
      dataPagamento: new Date(2017, 7, 6),
      valor: 500.0,
      pessoa: 'Rafaela Costa',
    },
    {
      tipo: 'DESPESA',
      descricao: 'Compra de roupas',
      dataVencimento: new Date(2017, 7, 18),
      dataPagamento: new Date(2017, 7, 17),
      valor: 120.0,
      pessoa: 'Patricia Ferreira',
    },
  ];
}
