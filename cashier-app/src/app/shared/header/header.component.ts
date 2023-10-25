import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  logOutUser(){
    /* logica del servicio */
    localStorage.clear();
    this.route.navigate(["login"])
  }
}
