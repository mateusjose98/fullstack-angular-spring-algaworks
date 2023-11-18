import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
@Injectable()
export class ErrorHandlerService {
  constructor(private messageService: MessageService) {}

  handle(error: any) {
    if (typeof error === 'string') {
      this.showError(error);
    } else if (typeof error === 'object') {
      if (error instanceof HttpErrorResponse) {
        this.showError(`[${error.status}] ${error.error.msgUsuario}`);
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
