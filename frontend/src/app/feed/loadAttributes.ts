import { CognitoUserAttribute } from "amazon-cognito-identity-js";
import { environment } from "src/environments/environment";

const { CognitoUserPool } = require("amazon-cognito-identity-js");

var attributes = []
var poolData = {
  UserPoolId: environment.UserPoolId, // Your user pool id here
  ClientId: environment.ClientId, // Your client id here
};
var userPool = new CognitoUserPool();
var currentUser = userPool.getCurrentUser();
currentUser.getSession((err: any, session: any) => {
  if (err) {
    alert(err.message || JSON.stringify(err));
    return;
  }
  currentUser.getUserAttributes((err: any, result: any) => {
    if (err) {
      alert(err.message || JSON.stringify(err));
      return;
    }
    attributes = result;
    attributes.forEach((attr: CognitoUserAttribute) => console.log(attr.Name + ' = ' + attr.Value));
  });
});