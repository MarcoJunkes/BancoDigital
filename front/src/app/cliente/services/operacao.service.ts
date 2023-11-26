import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Operacao } from "src/app/shared";

@Injectable({
  providedIn: 'root'
})
export class OperacaoService {
  private readonly API = 'http://localhost:3000/operacoes';

  constructor(private http: HttpClient) {
  }

  listar(filtro: any): Observable<Operacao[]> {
    let id = JSON.parse(localStorage.getItem('usuarioLogado') || '')['cpf'];
    return this.http.get<Operacao[]>(this.API+'/'+id, {params: {...filtro}});
  }

  buscarPorId(id: number): Observable<Operacao> {
    return this.http.get<Operacao>(`${this.API}/${id}`);
  }

  salvar(operacao: Operacao): Observable<Operacao> {
    
    let id = JSON.parse(localStorage.getItem('usuarioLogado') || '')['cpf'];
    let url = this.API+'/'+id+'/'+operacao.tipo?.toLowerCase();
    const operacaoObj = {
      valor: operacao.valor,
      destino: operacao.destino
    }
    
    return this.http.post<Operacao>(url, operacaoObj);

  }
}
