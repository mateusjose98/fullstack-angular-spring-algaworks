import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
@Injectable()
export class ErrorHandlerService {
  constructor(private messageService: MessageService) {}

  errorMap: any = {
    404: 'Recurso não encontrado',
    500: 'Erro interno desconhecido',
    403: 'Recurso não pode ser acessado por falta de permissão',
  };

  handle(error: any) {
    if (typeof error === 'string') {
      this.showError(error);
    } else if (typeof error === 'object') {
      if (error instanceof HttpErrorResponse) {
        if (error.error instanceof Array) {
          for (let e of error.error) {
            this.showError(`[${error.status}] ${e.msgUsuario}`);
          }
        } else {
          this.showError(
            `[${error.status}] ${
              error.error?.msgUsuario
                ? error.error?.msgUsuario
                : this.errorMap[error.status]
            }`
          );
        }
      } else {
        this.showError();
      }
    } else {
      this.showError();
    }
  }

  showError(msg?: string) {
    this.messageService.add({
      life: 10000,
      severity: 'error',
      summary: 'Error',
      detail: msg ? msg : 'Ocorreu um erro ao realizar ação',
    });
  }
}
