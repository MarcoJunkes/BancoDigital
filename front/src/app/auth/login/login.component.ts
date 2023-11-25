import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Login } from 'src/app/shared';
import { LoginService } from '../services/login.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent {
  @ViewChild('formLogin') formLogin!: NgForm;
  public login = new Login();
  loading: boolean = false;
  message!: string;

  constructor(
    private loginService: LoginService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    if (this.loginService.usuarioLogado) {
      this.redirecionaUsuarioLogado();
    }
  }

  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.message = params['error'];
      });
  }

  public logar(): void {
    this.loading = true;

    if (this.formLogin.form.valid) {
      this.loginService.login(this.login).subscribe({
        next: (output) => {
          this.loading = false;
          this.loginService.setAuthorizationToken(output.token);
          this.loginService.usuarioLogado = output.data;
          
          this.redirecionaUsuarioLogado();
        },
        error: () => {
          this.loading = false;
          this.message = 'Usuário/Senha inválidos.';
        }
      });
    }
  }

  redirecionaUsuarioLogado() {
    if (this.loginService.usuarioLogado.perfil === "cliente")
      this.router.navigate(["/cliente/home"]);
    else if (this.loginService.usuarioLogado.perfil === "gerente")
      this.router.navigate(["/gerente/tela-inicial-gerente"]);
    else if (this.loginService.usuarioLogado.perfil == "admin")
      this.router.navigate(["/administrador"]);
    else
      console.log('login.componets.ts: redirecionaUsuarioLogado - Nenhum perfil correspondeu ao perfil desse usuário');
    
  }

  desabilitarBanner() {
    this.message = '';
  }
}
