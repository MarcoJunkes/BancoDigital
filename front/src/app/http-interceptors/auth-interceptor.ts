import { HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse, HttpInterceptor } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";
import { LoginService } from "../auth/services/login.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private loginService: LoginService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.loginService.getAuthorizationToken();
    let request: HttpRequest<any> = req;

    if (token) {
      request = req.clone({
        headers: req.headers.set('Authorization', `Bearer ${token}`)
      });
    }

    return next.handle(request)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // Erro retornado pelo cliente
      console.error('Ocorreu um erro: ', error.error.message);
    } else {
      // Erro retornado pelo servidor
      console.error(
        `Código do erro ${error.status}, ` +
        `Erro: ${JSON.stringify(error.error)}`
      );
    }

    // Retorna um observable com mensagem
    return throwError(() => error);
  }
}
