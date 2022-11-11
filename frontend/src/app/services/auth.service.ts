import { Injectable } from '@angular/core';
import { AuthenticationDetails, CognitoUser, CognitoUserPool } from 'amazon-cognito-identity-js';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  isAuth(): boolean {
    var auth = false;
    var poolData = {
      UserPoolId: environment.UserPoolId, // Your user pool id here
      ClientId: environment.ClientId, // Your client id here
    };
    var userPool = new CognitoUserPool(poolData);
    var currentUser = userPool.getCurrentUser();
    if(currentUser != null){
      currentUser.getSession((err: any, session: any) => {
        if (err) {
          alert(err.message || JSON.stringify(err));
        }
        auth = session.isValid();
      });
    }
    return auth;
  }

  getToken(): string{
    for (let i = 0; i < localStorage.length; i++) {
      if (localStorage.key(i).endsWith('accessToken') && localStorage.key(i).includes(environment.ClientId)) {
        return localStorage.getItem(localStorage.key(i));
      }
    }
    return null;
  }
}
