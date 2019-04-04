import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreacionPersonasComponent } from './creacion-personas.component';

describe('CreacionPersonasComponent', () => {
  let component: CreacionPersonasComponent;
  let fixture: ComponentFixture<CreacionPersonasComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreacionPersonasComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreacionPersonasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
