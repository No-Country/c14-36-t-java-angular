import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DATA } from '../data';
import { IhelpResponse } from '../Ires';

@Component({
  selector: 'app-help-response',
  templateUrl: './help-response.component.html',
  styleUrls: ['./help-response.component.scss'],
})
export class HelpResponseComponent {
  responseData!:IhelpResponse;
  constructor(private routeConfig: ActivatedRoute) {}

  ngOnInit() {
    const id = +(this.routeConfig.snapshot.paramMap.get('id') || 0);
    const [newData] = DATA.filter((res) => res.id == id);
    this.responseData = newData;
  }
}
