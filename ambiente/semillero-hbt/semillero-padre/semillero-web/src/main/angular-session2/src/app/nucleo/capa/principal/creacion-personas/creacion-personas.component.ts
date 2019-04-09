import { Component, OnInit } from '@angular/core';
import {personaDTO} from './model/personaDTO'

@Component({
  selector: 'app-creacion-personas',
  templateUrl: './creacion-personas.component.html'
})
export class CreacionPersonasComponent implements OnInit {
 
  public persona:personaDTO;
  public personas: personaDTO [];
  public mostrarFormulario: boolean;
  constructor() { }

  ngOnInit() {
    this.personas=[];    
    this.persona={
      id : '0',
      nombre : '',
      apellido : '',
      tipoIdentificacion : '',
      numeroIdentificacion : '',
      mayorEdad : false,
      sexo : ''
    };
    this.mostrarFormulario = true;
    this.personas.push(this.persona);
  }

  public mostrar() {
    this.mostrarFormulario= true;

  }

  public ocultar(){
    this.mostrarFormulario= false;

  }

  public guardar(){
   
    this.persona={
      id : '0',
      nombre : '',
      apellido : '',
      tipoIdentificacion : '',
      numeroIdentificacion : '',
      mayorEdad : true,
      sexo : ''
    };
    this.personas.push(this.persona)

  }
   
  private borrar(per:personaDTO){
    this.personas.splice(this.personas.indexOf(per),1);
  }

}
