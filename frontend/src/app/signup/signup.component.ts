import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CognitoUserAttribute, CognitoUserPool } from 'amazon-cognito-identity-js';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  email: string;
  givenName: string;
  nickName: string;
  password: string;

  constructor(
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onRegister(): void {
    var poolData = {
      UserPoolId: environment.UserPoolId, // Your user pool id here
      ClientId: environment.ClientId, // Your client id here
    };

    var userPool = new CognitoUserPool(poolData);
    // set attributes
    var attributeList = [];
    var user: User = {
      email: this.email,
      given_name: this.givenName,
      nickname: this.nickName
    }
    for (let key in user){
      var attrData = {
        Name: key,
        Value: user[key]
      }
      var attr = new CognitoUserAttribute(attrData)
      attributeList.push(attr);
    }
    // signup
    userPool.signUp(this.email, this.password, attributeList, [], (err, result) => {
      if (err) {
        alert(err.message || JSON.stringify(err));
        return;
      }
      var newuser = result.user;
      console.log(JSON.stringify(newuser));
      alert('Se ha enviado un correo para activar la cuenta');
      this.router.navigate(['/login']);
    });
  }

}
