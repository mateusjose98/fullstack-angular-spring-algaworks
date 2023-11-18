import { Component } from '@angular/core';
import { Pessoa } from 'src/app/core/model';
import { PessoaService } from '../pessoa.service';
import { ErrorHandlerService } from 'src/app/core/error-handler.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-pessoa-cadastro',
  templateUrl: './pessoa-cadastro.component.html',
  styleUrls: ['./pessoa-cadastro.component.css'],
})
export class PessoaCadastroComponent {
  constructor(
    private pessoaSerivce: PessoaService,
    private errorService: ErrorHandlerService,
    private messageService: MessageService
  ) {}

  pessoa = new Pessoa();

  salvar(form: any) {
    console.log(this.pessoa);
    this.pessoaSerivce.salvar(this.pessoa).subscribe({
      next: (response) => {
        console.log(response);
        this.sucesso('Pessoa inserida com sucesso!');
        form.reset();
      },
      error: (error) => {
        this.errorService.handle(error);
      },
    });
  }

  sucesso(msg: string) {
    this.messageService.add({
      severity: 'success',
      summary: 'Success',
      detail: msg,
    });
  }
}
