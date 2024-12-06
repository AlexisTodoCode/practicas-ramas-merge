import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComprobanteDialogComponent } from './comprobante-dialog.component';

describe('ComprobanteDialogComponent', () => {
  let component: ComprobanteDialogComponent;
  let fixture: ComponentFixture<ComprobanteDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ComprobanteDialogComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ComprobanteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
