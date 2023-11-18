import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LancamentoCadastroComponent } from './lancamento-cadastro/lancamento-cadastro.component';
import { LancamentosPesquisaComponent } from './lancamentos-pesquisa/lancamentos-pesquisa.component';
import { FormsModule } from '@angular/forms';
import { SelectButtonModule } from 'primeng/selectbutton';
import { MenubarModule } from 'primeng/menubar';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CalendarModule } from 'primeng/calendar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DropdownModule } from 'primeng/dropdown';
import { CurrencyMaskModule } from 'ng2-currency-mask';
import { ToastModule } from 'primeng/toast';
import { SharedModule } from '../shared/shared.module';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { MessageService } from 'primeng/api';
import { CategoriaService } from '../categorias/categoria.service';
@NgModule({
  declarations: [LancamentoCadastroComponent, LancamentosPesquisaComponent],
  exports: [LancamentoCadastroComponent, LancamentosPesquisaComponent],
  imports: [
    CommonModule,
    FormsModule,
    SelectButtonModule,
    MenubarModule,
    TableModule,
    ButtonModule,
    InputTextModule,
    TooltipModule,
    InputTextModule,
    InputTextareaModule,
    CalendarModule,
    BrowserAnimationsModule,
    DropdownModule,
    CurrencyMaskModule,
    SharedModule,
    ToastModule,
    ConfirmDialogModule,
  ],
  providers: [MessageService, CategoriaService],
})
export class LancamentosModule {}
