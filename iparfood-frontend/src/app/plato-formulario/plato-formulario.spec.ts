import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlatoFormulario } from './plato-formulario';

describe('PlatoFormulario', () => {
  let component: PlatoFormulario;
  let fixture: ComponentFixture<PlatoFormulario>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlatoFormulario],
    }).compileComponents();

    fixture = TestBed.createComponent(PlatoFormulario);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
