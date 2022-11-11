import { Component, OnInit, ViewChild } from '@angular/core';
import { ApiRestService } from '../services/api-rest.service';

class Publicacion {
  author: string;
  texto: string;
  titulo: string;
  constructor(author: string, titulo: string, texto: string) {
      this.author = author;
      this.titulo = titulo;
      this.texto = texto;
  }
}

@Component({
  selector: 'app-tweets',
  templateUrl: './tweets.component.html',
  styleUrls: ['./tweets.component.css']
})
export class TweetsComponent implements OnInit {
  @ViewChild("inputBox") inputField:any;

  publicaciones: Publicacion[];

  constructor(
    private apiRestService: ApiRestService
  ) { }

  ngOnInit(): void {
  }

  sendReq(texto: string): void {
    var userNickName = localStorage.getItem('user_nickname');
    var newPublicacion = new Publicacion(userNickName, '', texto);
    this.apiRestService.postPublicacion(newPublicacion).subscribe(response =>{
      console.log(userNickName);
      console.log(texto);
      console.log(response);
      alert("Se ha enviado el Tweet!");
      this.inputField.nativeElement.value = '';
      this.getPublications();
    });
  }

  getPublications(): void{
    this.apiRestService.getPublications().subscribe(response => {
      console.log(response);
      this.publicaciones = Object.values(response);
    });
  }

}
