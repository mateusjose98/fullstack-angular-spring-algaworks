import { Component, OnInit, ViewChild } from '@angular/core';
import { LancamentoFiltro, LancamentoService } from '../lancamento.service';
import {
  ConfirmationService,
  MessageService,
  ConfirmEventType,
} from 'primeng/api';
import { Table, TableLazyLoadEvent } from 'primeng/table';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { CategoriaService } from 'src/app/categorias/categoria.service';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css'],
  providers: [ConfirmationService],
})
export class LancamentosPesquisaComponent implements OnInit {
  lancamentos = [];
  filtro = new LancamentoFiltro();
  totalRegistros = 0;
  categorias: any = [];
  @ViewChild('tabela') private grid!: Table;

  constructor(
    private lancamentosService: LancamentoService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    private errorService: ErrorHandlerService
  ) {}
  ngOnInit(): void {}
  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.lancamentosService.pesquisar(this.filtro).subscribe({
      next: (res) => {
        this.lancamentos = res.content;
        this.totalRegistros = res.totalElements;
      },
      error: (error) => this.errorService.handle(error),
    });
  }

  aoMudarPagina(event: TableLazyLoadEvent) {
    if (event.first != null && event.rows != null) {
      const pagina = event.first / event.rows;
      this.pesquisar(pagina);
    }
  }

  excluir(id: number): void {
    this.confirmationService.confirm({
      message: 'Certeza que quer excluir?',
      header: 'Confirmation',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.lancamentosService.excluir(id).subscribe({
          next: () => {
            this.grid.first = 0;
            this.pesquisar(0);
            this.sucesso(id);
          },
          error: (error: HttpErrorResponse) => {
            this.errorService.handle(error);
          },
        });
      },
      reject: (type: ConfirmEventType) => {
        console.log(type);
      },
    });
  }

  sucesso(id: number) {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: 'Lançamento excluído: ' + id,
    });
  }
}
