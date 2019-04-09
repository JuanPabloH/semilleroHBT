import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-creacion-personas',
  templateUrl: './creacion-personas.component.html',
  styleUrls: ['./creacion-personas.component.css']
})
export class CreacionPersonasComponent implements OnInit {
public persona: Persona;
 
  constructor() {}

  ngOnInit() {  

  this.persona={nombre:'Juan',apellido:'Herrera',documento:'1057604965',id:'1',edad:18};
  }



}


interface Persona {
    nombre: string,
    apellido: string,
    documento: string,
    id: string,  
    edad: number  
  } 
