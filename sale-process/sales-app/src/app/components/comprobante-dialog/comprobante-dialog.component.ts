import { Component, Inject } from '@angular/core';
import { Comprobante } from '../../interfaces/comprobante';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';  // Importar CommonModule

@Component({
  selector: 'app-comprobante-dialog',
  imports: [MatDialogModule,CommonModule],
  templateUrl: './comprobante-dialog.component.html',
  styleUrls: ['./comprobante-dialog.component.css'],
})
export class ComprobanteDialogComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: Comprobante) {} // Recibe el comprobante en los datos
}