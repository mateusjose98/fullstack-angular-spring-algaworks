import { Component, Input } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-message',
  template: ` <small *ngIf="temErro()" class="p-error">
    {{ text }}
  </small>`,
  styles: [],
})
export class MessageComponent {
  @Input() error: string | undefined;
  @Input() control: any;
  @Input() text: string | undefined;

  temErro(): boolean | undefined {
    if (this.error) {
      return this.control?.hasError(this.error) && this.control?.dirty;
    }
    return false;
  }
}
