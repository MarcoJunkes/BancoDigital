import { HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse, HttpInterceptor } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, catchError, throwError } from "rxjs";
import { LoginService } from "../auth/services/login.service";
import { Router } from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private router: Router,
    private loginService: LoginService
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.loginService.getAuthorizationToken();
    let request: HttpRequest<any> = req;

    if (token) {
      request = req.clone({
        headers: req.headers.set('x-access-token', token)
      });
    }

    return next.handle(request)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error.error instanceof ErrorEvent) {
            console.error('Ocorreu um erro: ', error.error.message);
          } else {
            console.error(
              `Código do erro ${error.status}, ` +
              `Erro: ${JSON.stringify(error.error)}`
            );
          }

          if (error.status === 401) {
            this.loginService.logout();
            this.router.navigate(['/login']);
          }
          return throwError(() => error);
        })
      );
  }
}
