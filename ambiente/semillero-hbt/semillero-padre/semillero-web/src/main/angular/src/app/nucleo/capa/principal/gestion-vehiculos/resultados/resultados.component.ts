import { Component, Input, OnInit } from '@angular/core';
import { ConsultaVehiculosService } from '../../../../../nucleo/servicios/consulta-vehiculo-servicio/consulta.vehiculo.service'
import { VehiculoDTO } from '../modelo/vehiculosDTO';
import { PersonaServicioDTO } from '../modelo/personaServicioDTO';

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html'
})
export class ResultadosComponent implements OnInit {
  @Input() public marca:string;
  @Input() public placa:string;
  
  public listaVehiculoDTO: VehiculoDTO[]; 
  public vehiculoDTO: VehiculoDTO; 
  
  public listaPersonasDTO: PersonaServicioDTO[]; 
  public personaDTO: PersonaServicioDTO; 

  constructor(private vehiculosService: ConsultaVehiculosService) {}

  ngOnInit() {
  }

  /**
   * consultarPersonas
   */
  public consultarPersonas() {
    let tipoID: string = 'CC';
    let numID: string = '12345';
    this.vehiculosService.consultarPersonas(tipoID,numID).subscribe(
      personas => {
          this.listaPersonasDTO = personas;
        },
        error => {
          console.log(error);
        } 
  ); 
  console.log('resultado servicio.... ' + this.listaPersonasDTO)
  }

}
