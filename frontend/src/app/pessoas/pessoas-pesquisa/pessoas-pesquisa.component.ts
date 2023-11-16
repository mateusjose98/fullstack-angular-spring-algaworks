import { Component } from '@angular/core';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css'],
})
export class PessoasPesquisaComponent {
  pessoas = [
    {
      nome: 'José Silva',
      cidade: 'São Paulo',
      estado: 'SP',
      status: true,
    },
    {
      nome: 'Maria Oliveira',
      cidade: 'Rio de Janeiro',
      estado: 'RJ',
      status: false,
    },
    {
      nome: 'João Santos',
      cidade: 'Belo Horizonte',
      estado: 'MG',
      status: true,
    },
    {
      nome: 'Ana Souza',
      cidade: 'Salvador',
      estado: 'BA',
      status: false,
    },
    {
      nome: 'Carlos Lima',
      cidade: 'Porto Alegre',
      estado: 'RS',
      status: true,
    },
    {
      nome: 'Rafaela Costa',
      cidade: 'Recife',
      estado: 'PE',
      status: false,
    },
    {
      nome: 'Patricia Ferreira',
      cidade: 'Fortaleza',
      estado: 'CE',
      status: true,
    },
    {
      nome: 'Fernando Oliveira',
      cidade: 'Manaus',
      estado: 'AM',
      status: false,
    },
  ];
}
