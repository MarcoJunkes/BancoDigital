import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Cliente } from "src/app/shared/models/cliente.model";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class autocadastroService {
  
    constructor(
        private http: HttpClient
    ) { }

    httpOptions = {
        headers: new HttpHeaders({
        'Content-Type': 'application/json'
        })
    };

    public autocadastro(cliente: Cliente): Observable<any> {
        return this.http.post<any>(`${environment.api}/autocadastro`, cliente, this.httpOptions);
    }
}
  