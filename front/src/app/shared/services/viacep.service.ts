import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

export interface IViacepResponse {
  cep: string;
  logradouro: string;
  complemento: string;
  bairro: string;
  localidade: string;
  uf: string;
  ibge: string;
  gia: string;
  ddd: string;
  siafi: string;
}

@Injectable({
  providedIn: 'root'
})
export class ViaCepService {
  constructor(private http: HttpClient) {}
  
  public getAddress(cep: string): Observable<IViacepResponse> {
    return this.http.get<IViacepResponse>(`https://viacep.com.br/ws/${cep}/json/`)
  }
}
