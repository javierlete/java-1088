import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizacionPedido } from './visualizacion-pedido';
import { provideRouter } from '@angular/router';

describe('VisualizacionPedido', () => {
  let component: VisualizacionPedido;
  let fixture: ComponentFixture<VisualizacionPedido>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VisualizacionPedido],
      providers: [provideRouter([])]
    }).compileComponents();

    fixture = TestBed.createComponent(VisualizacionPedido);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
