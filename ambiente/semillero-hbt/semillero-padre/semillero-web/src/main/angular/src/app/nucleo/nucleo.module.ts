import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CapaComponent } from './capa/capa.component';
import { EncabezadoComponent } from './capa/encabezado/encabezado.component';
import { MainComponent } from './capa/main/main.component';
import { PiepaginaComponent } from './capa/piepagina/piepagina.component';
import { CreacionPersonasComponent } from './capa/main/creacion-personas/creacion-personas.component';

@NgModule({
  declarations: [CapaComponent, EncabezadoComponent, MainComponent, PiepaginaComponent, CreacionPersonasComponent],
  imports: [
    CommonModule
  ],
  exports: [
  	CapaComponent
  ]
})
export class NucleoModule { }
