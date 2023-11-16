import { Component, OnInit } from '@angular/core';
import { LancamentoFiltro, LancamentoService } from '../lancamento.service';
import { LazyLoadEvent } from 'primeng/api';
import { TableLazyLoadEvent } from 'primeng/table';

@Component({
  selector: 'app-lancamentos-pesquisa',
  templateUrl: './lancamentos-pesquisa.component.html',
  styleUrls: ['./lancamentos-pesquisa.component.css'],
})
export class LancamentosPesquisaComponent implements OnInit {
  lancamentos = [];
  filtro = new LancamentoFiltro();
  totalRegistros = 0;

  constructor(private lancamentosService: LancamentoService) {}
  ngOnInit(): void {}
  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;
    this.lancamentosService.pesquisar(this.filtro).subscribe((res) => {
      this.lancamentos = res.content;
      this.totalRegistros = res.totalElements;
    });
  }

  aoMudarPagina(event: TableLazyLoadEvent) {
    if (event.first != null && event.rows != null) {
      const pagina = event.first / event.rows;
      this.pesquisar(pagina);
    }
  }
}
