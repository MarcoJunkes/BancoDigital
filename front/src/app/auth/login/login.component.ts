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

  logar(): void {
    this.loading = true;
    if (this.formLogin.form.valid) {
      this.loginService.login(this.login).subscribe((usu) => {
        if (usu != null) {
          this.loginService.usuarioLogado = usu;
          this.loading = false;
          this.redirecionaUsuarioLogado();
        } else {
          this.message = "E-mail/Senha inválidos.";
        }
      });
    }
    this.loading = false;
  }

  redirecionaUsuarioLogado() {
    // TODO: redirecionar para a página correta, de acordo com cada perfil
    // if (this.loginService.usuarioLogado.perfil === Perfil.CLIENTE)
    //   this.router.navigate(["/home"]);
    // else {
    //   this.router.navigate(["/pagina/inicial/funcionario"]);
    // }
  }
}
