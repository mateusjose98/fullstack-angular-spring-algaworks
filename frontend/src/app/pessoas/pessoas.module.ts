import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PessoaCadastroComponent } from './pessoa-cadastro/pessoa-cadastro.component';
import PessoasPesquisaComponent from './pessoas-pesquisa/pessoas-pesquisa.component';
import { BrowserModule } from '@angular/platform-browser';
import { SelectButtonModule } from 'primeng/selectbutton';
import { MenubarModule } from 'primeng/menubar';
import { FormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CalendarModule } from 'primeng/calendar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DropdownModule } from 'primeng/dropdown';

import { InputMaskModule } from 'primeng/inputmask';
import { TagModule } from 'primeng/tag';
import { SharedModule } from '../shared/shared.module';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';

@NgModule({
  declarations: [PessoaCadastroComponent, PessoasPesquisaComponent],
  imports: [
    CommonModule,
    BrowserModule,
    SelectButtonModule,
    MenubarModule,
    FormsModule,
    TableModule,
    ButtonModule,
    TooltipModule,
    InputTextModule,
    InputTextareaModule,
    CalendarModule,
    BrowserAnimationsModule,
    DropdownModule,
    InputMaskModule,
    SharedModule,
    ToastModule,
    ConfirmDialogModule,
    TagModule,
  ],
  // exports: [PessoaCadastroComponent, PessoasPesquisaComponent],
  providers: [],
})
export class PessoasModule {}
