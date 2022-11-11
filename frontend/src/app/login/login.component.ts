import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationDetails, CognitoUser, CognitoUserAttribute, CognitoUserPool } from 'amazon-cognito-identity-js';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email: string;
  password: string;

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onLogin(): void {
    // Datos del pool
    var poolData = {
      UserPoolId: environment.UserPoolId, // Your user pool id here
      ClientId: environment.ClientId, // Your client id here
    };
    var userPool = new CognitoUserPool(poolData);
    // Datos del usuario
    var userData = {
      Username: this.email,
      Pool: userPool
    };
    var cognitoUser = new CognitoUser(userData);
    // Credenciales
    var authData = {
      Username: this.email,
      Password: this.password
    };
    var attributes: CognitoUserAttribute[] = [];
    var authDetails = new AuthenticationDetails(authData);
    // Login
    cognitoUser.authenticateUser(authDetails, {
      onSuccess: (result) => {
        console.log("Token: " + result.getAccessToken().getJwtToken());
        cognitoUser.getSession((err: any, session: any) => {
          if (err) {
            alert(err.message || JSON.stringify(err));
            return;
          }
          cognitoUser.getUserAttributes((err: any, result: any) => {
            if (err) {
              alert(err.message || JSON.stringify(err));
              return;
            }
            attributes = result;
            attributes.forEach((attr: CognitoUserAttribute) => console.log(attr.Name + ' = ' + attr.Value));
            attributes.forEach((attr: CognitoUserAttribute) => {
              if(attr.Name == 'nickname'){
                localStorage.setItem('user_nickname', attr.Value);              }
            });
          });
        });
        this.router.navigate(['/home']);
      },
      onFailure: (error) => {
        alert(error.message || JSON.stringify(error));
      }
    });
  }
}
