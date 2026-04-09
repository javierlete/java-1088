import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoPlatos } from './listado-platos';

describe('ListadoPlatos', () => {
  let component: ListadoPlatos;
  let fixture: ComponentFixture<ListadoPlatos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListadoPlatos],
    }).compileComponents();

    fixture = TestBed.createComponent(ListadoPlatos);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
