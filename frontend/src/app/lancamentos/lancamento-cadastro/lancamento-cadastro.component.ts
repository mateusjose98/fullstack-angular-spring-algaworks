import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { CategoriaService } from 'src/app/categorias/categoria.service';
import { Lancamento } from 'src/app/core/model';
import { PessoaService } from 'src/app/pessoas/pessoa.service';
import { LancamentoService } from '../lancamento.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './lancamento-cadastro.component.html',
  styleUrls: ['./lancamento-cadastro.component.css'],
  providers: [ConfirmationService],
})
export class LancamentoCadastroComponent implements OnInit {
  constructor(
    private categoriaService: CategoriaService,
    private pessoaService: PessoaService,
    private lancamentoService: LancamentoService,
    private confirmationService: ConfirmationService,
    private errorService: ErrorHandlerService,
    private messageService: MessageService,
    private route: ActivatedRoute,
    private router: Router
  ) {}
  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.carregarLancamento(id);
    }
    this.listarCategorias();
    this.listarPessoas();
  }
  get editando() {
    return Boolean(this.lancamento.id);
  }
  carregarLancamento(id: any) {
    this.lancamentoService.buscarPorId(id).subscribe({
      next: (res) => {
        this.lancamento = res;
      },
      error: (err) => {
        this.errorService.handle(err);
      },
    });
  }

  tipos = [
    { label: 'Receita', value: 'RECEITA' },
    { label: 'Despesa', value: 'DESPESA' },
  ];

  categorias: any = [];
  pessoas = [];
  lancamento = new Lancamento();

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

  salvar(form: any) {
    if (this.editando) {
      this.atualizarLancamento(form);
    } else {
      this.adicionarLancamento(form);
    }
  }

  atualizarLancamento(form: any) {
    this.lancamentoService.atualizar(this.lancamento).subscribe({
      next: (res) => {
        this.lancamento = res;
        this.sucesso('Lançamento ATUALIZADO com sucesso!');
      },
      error: (e) => this.errorService.handle(e),
    });
  }

  adicionarLancamento(form: any) {
    this.lancamentoService.adicionar(this.lancamento).subscribe({
      next: () => {
        this.sucesso('Lançamento CADASTRADO com sucesso!');
        // form.reset();
        this.router.navigate(['/lancamentos']);
      },
      error: (error) => {
        this.errorService.handle(error);
      },
    });
  }

  novo(form: any) {
    form.reset();
    setTimeout(() => {
      this.lancamento = new Lancamento();
    }, 300);

    this.router.navigate(['/lancamentos/novo']);
  }

  sucesso(msg: string) {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: msg,
    });
  }
}
