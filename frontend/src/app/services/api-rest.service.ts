import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class ApiRestService {

  springURL = environment.springBoot;

  constructor(
    private httpClient: HttpClient
  ) { }

  // POST
  public postPublicacion(body: any){
    return this.httpClient.post(this.springURL, body);
  }

  //GET
  public getPublications(){
    return this.httpClient.get(this.springURL);
  }
}
