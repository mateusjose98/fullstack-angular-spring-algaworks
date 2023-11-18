import { ErrorHandler, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { ErrorHandlerService } from './error-handler.service';
import { RouterModule } from '@angular/router';
import { NotFoundComponent } from './not-found.component';
import { Title } from '@angular/platform-browser';

@NgModule({
  declarations: [NavbarComponent, NotFoundComponent],
  imports: [CommonModule, RouterModule],
  exports: [NavbarComponent],
  providers: [ErrorHandlerService, Title],
})
export class CoreModule {}
