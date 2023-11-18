import { Component, ViewChild } from '@angular/core';
import { PessoaService } from '../pessoa.service';
import { ConfirmationService, MessageService } from 'primeng/api';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Table, TableLazyLoadEvent } from 'primeng/table';

@Component({
  selector: 'app-pessoas-pesquisa',
  templateUrl: './pessoas-pesquisa.component.html',
  styleUrls: ['./pessoas-pesquisa.component.css'],
  providers: [ConfirmationService],
})
export class PessoasPesquisaComponent {
  nome: string = '';
  pessoas = [];
  totalRegistros = 0;
  paginaAtual = 0;
  @ViewChild('tabela') private grid!: Table;

  constructor(
    private service: PessoaService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private errorService: ErrorHandlerService
  ) {}

  pesquisar(pagina: number) {
    this.service.pesquisar({ nome: this.nome, pagina }).subscribe((res) => {
      this.pessoas = res.content;
      this.totalRegistros = res.totalElements;
      console.log(res.number);
      this.paginaAtual = res.number;
    });
  }

  ativar(id: number, deveAtivar: boolean) {
    this.service.ativar(id, !deveAtivar).subscribe((res) => {
      this.pesquisar(this.paginaAtual);

      this.sucesso(
        id,
        deveAtivar
          ? 'Pessoa inativada com sucesso!'
          : 'Pessoa ativa com sucesso!'
      );
    });
  }

  aoMudarPagina(event: TableLazyLoadEvent) {
    if (event.first != null && event.rows != null) {
      const pagina = event.first / event.rows;
      this.pesquisar(pagina);
    }
  }

  excluir(id: number) {
    this.confirmationService.confirm({
      acceptLabel: 'Sim',
      rejectLabel: 'Não',
      message: 'Certeza que quer excluir?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.service.excluir(id).subscribe({
          next: () => {
            this.grid.first = 0;
            this.pesquisar(0);
            this.sucesso(id, 'Pessoa excluída: ' + id);
          },
          error: (error: HttpErrorResponse) => {
            this.errorService.handle(error);
          },
        });
      },
    });
  }

  sucesso(id: number, msg: string) {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: msg,
    });
  }
}
