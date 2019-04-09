import { Component, OnInit } from '@angular/core';
import {personaDTO} from './model/personaDTO'

@Component({
  selector: 'app-creacion-personas',
  templateUrl: './creacion-personas.component.html'
})
export class CreacionPersonasComponent implements OnInit {
 
  public persona:personaDTO;
  public persona2:personaDTO;
  public personas: personaDTO [];
  public mostrarFormulario: boolean=false;
  public posActualizar:number;
  public actualizarPersona:boolean=false;
  
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
      sexo : '',
      fechaNacimiento: '',
    };
    
    this.personas.push(this.persona);
  }
/**
 * asignar el valor de true a la variable mostrarFormulario
 */
  public mostrar() {
    this.actualizarPersona=false;
    this.mostrarFormulario= true;

  }
/**
 * asignar el valor de false a la variable mostrarFormulario
 */
  public ocultar(){
    this.mostrarFormulario= false;

  }
 

  public guardar(){
    
    this.persona={
      id : this.personas.length+'',
      nombre : '',
      apellido : '',
      tipoIdentificacion : '',
      numeroIdentificacion : '',
      mayorEdad : true,
      sexo : '',
      fechaNacimiento:''
    };
    this.personas.push(this.persona)

  }
  /**
   * El metodo recibe como parametro una persona y la elimina del arreglo
   * @param per 
   */
  private borrar(per:personaDTO){
    this.personas.splice(this.personas.indexOf(per),1);
  }
  /**
   * Este metodo recibe como parametro la persona que se va a actualizar y la guarda en un objeto llamado persona2 de tipo persona
   * y desde el formulario se trabaja con persona2 para actualizar los valores posteriormente.
   * Tambien se almacena la posicion para posteriormente en el metodo update realizar la actualizacion
   * @param per 
   */
  public formActualizar(per:personaDTO){
    this.mostrar();
    this.actualizarPersona=true;
    this.posActualizar=this.personas.indexOf(per);
    this.persona2=this.personas[this.posActualizar];
    
  }
  /**
   * Este metodo es llamado cuando el usuario ha cambiado los campos en el formulario de edicion y se asignan a 
   * el arreglo de personas en la posicion que se dio en el metodo de actualizar
   */
  public update(){
    
    this.personas[this.posActualizar]=this.persona2;
    this.ocultar();
    this.actualizarPersona=false;

  }

}
