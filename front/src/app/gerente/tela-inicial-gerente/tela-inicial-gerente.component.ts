import { Component, OnInit} from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalTelaInicialComponent } from '../modal-tela-inicial/modal-tela-inicial.component';
import { GerenteService } from '../services/gerente.service';
// import { Cliente } from 'src/app/shared/models/cliente.model';
import { Usuario } from 'src/app/shared';

@Component({
  selector: 'app-tela-inicial-gerente',
  templateUrl: './tela-inicial-gerente.component.html'
})
export class TelaInicialGerenteComponent implements OnInit{
  usuarios: Usuario[] = [];
  usuario!: Usuario;
  constructor(private modalService: NgbModal,
              private gerenteService: GerenteService){}
  
  listarTodos(): Usuario[] {
    this.gerenteService.listarTodos().subscribe({
      next: (data: Usuario[]) => {
        if (data == null) {
          this.usuarios = [];
        }
        else {
          this.usuarios = data;
        }
      }
    });
    return this.usuarios;
  }

  abrirModal(acao: string){
    const modalRef = this.modalService.open(ModalTelaInicialComponent);
    modalRef.componentInstance.acao = acao;
  }

  aprovarCliente(usuario: Usuario){
    console.log(usuario);
    this.gerenteService.aprovarCliente(usuario).subscribe(
      usuario => {
        this.abrirModal('Aprovar');
      }
    );
  }

  ngOnInit(): void {
      this.usuarios = [];
      this.listarTodos();
  }
}
