import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Conta } from "src/app/shared";

@Injectable({
  providedIn: 'root'
})
export class ContaService {
  private readonly API = 'http://localhost:3000/contas';

  constructor(private http: HttpClient) {
  }

  buscarContaCliente(): Observable<Conta> {
    let id = JSON.parse(localStorage.getItem('usuarioLogado') || '')['cpf'];
    return this.http.get<Conta>(`${this.API}/${id}`);
  }
}
