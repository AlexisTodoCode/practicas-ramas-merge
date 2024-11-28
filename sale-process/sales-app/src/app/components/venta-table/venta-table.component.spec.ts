import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentaTableComponent } from './venta-table.component';

describe('VentaTableComponent', () => {
  let component: VentaTableComponent;
  let fixture: ComponentFixture<VentaTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VentaTableComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VentaTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
