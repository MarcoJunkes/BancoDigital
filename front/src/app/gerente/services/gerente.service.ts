import { Injectable } from '@angular/core';
import { Gerente } from 'src/app/shared/models/gerente.model';

const LS_CHAVE: string = "gerentes";

@Injectable({
  providedIn: 'root'
})
export class GerenteService {

  constructor() { }
  
  listarTodos(): Gerente[]{
    const gerentes = localStorage[LS_CHAVE];
    return gerentes ? JSON.parse(gerentes) : [];
  }

  inserir(gerente: Gerente): void{
    const gerentes = this.listarTodos();
    gerentes.push(gerente);
    localStorage[LS_CHAVE] = JSON.stringify(gerentes);
  }
  buscarPorCpf(cpf : string): Gerente | undefined{
    const gerentes: Gerente[] = this.listarTodos();

    return gerentes.find(gerente => gerente.cpf === cpf);
  }
  atualizar(gerente: Gerente): void{
    const gerentes = this.listarTodos();

    gerentes.forEach(
      (obj,index,objs) => {
        objs[index] = gerente;
      }
    );

    localStorage[LS_CHAVE] = JSON.stringify(gerentes);
  }
  remover(cpf: string): void{
    let gerentes: Gerente[] = this.listarTodos();

    gerentes = gerentes.filter( gerente => gerente.cpf !== cpf)
    localStorage[LS_CHAVE] = JSON.stringify(gerentes);
  }
}
