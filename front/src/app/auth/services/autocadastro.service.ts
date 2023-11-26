import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Usuario } from "src/app/shared";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class AutocadastroService {
  constructor(
    private http: HttpClient
  ) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  public autocadastro(usuario: Usuario): Observable<any> {
    return this.http.post<any>(`${environment.api}/autocadastro`, usuario, this.httpOptions);
  }
  
  public alterarPerfil(usuario: Usuario): Observable<any> {
    return this.http.put<any>(`${environment.api}/autocadastro`, usuario, this.httpOptions);
  }
}
  