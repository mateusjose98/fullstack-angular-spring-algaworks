import { Component, OnInit } from '@angular/core';
import { CategoriaService } from 'src/app/categorias/categoria.service';
import { PessoaService } from 'src/app/pessoas/pessoa.service';

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './lancamento-cadastro.component.html',
  styleUrls: ['./lancamento-cadastro.component.css'],
})
export class LancamentoCadastroComponent implements OnInit {
  constructor(
    private categoriaService: CategoriaService,
    private pessoaService: PessoaService
  ) {}
  ngOnInit(): void {
    this.listarCategorias();
    this.listarPessoas();
  }
  tipos = [
    { label: 'Receita', value: 'RECEITA' },
    { label: 'Despesa', value: 'DESPESA' },
  ];

  categorias: any = [];

  pessoas = [
    { label: 'João da Silva', value: 1 },
    { label: 'Sebastião Souza', value: 2 },
    { label: 'Maria Abadia', value: 3 },
  ];

  listarPessoas() {
    this.pessoaService.pesquisar({ devePaginar: false }).subscribe((res) => {
      this.pessoas = res.content.map((el: any) => {
        return { label: el.nome, value: el.id };
      });
    });
  }

  listarCategorias() {
    this.categoriaService.listarTodas().subscribe((res) => {
      this.categorias = res.map((el) => {
        return { label: el.nome, value: el.id };
      });
    });
  }
}
