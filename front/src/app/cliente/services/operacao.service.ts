import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Operacao } from "src/app/shared";

@Injectable({
  providedIn: 'root'
})
export class OperacaoService {
  private readonly API = 'http://localhost:3000/operacoes';

  constructor(private http: HttpClient) { }

  listar(): Observable<Operacao[]> {
    return this.http.get<Operacao[]>(this.API);
  }

  buscarPorId(id: number): Observable<Operacao> {
    return this.http.get<Operacao>(`${this.API}/${id}`);
  }

  salvar(operacao: Operacao): Observable<Operacao> {
    return this.http.post<Operacao>(this.API, operacao);
  }

  atualizar(operacao: Operacao): Observable<Operacao> {
    return this.http.put<Operacao>(`${this.API}/${operacao.id}`, operacao);
  }

  excluir(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}
