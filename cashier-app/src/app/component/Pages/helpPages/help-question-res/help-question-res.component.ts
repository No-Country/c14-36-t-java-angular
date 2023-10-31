import { Component } from '@angular/core';
import { DATA } from '../data';
import { ActivatedRoute } from '@angular/router';
import { IhelpResponse } from '../Ires';
import { scaling } from 'src/app/animations/animation';

@Component({
  selector: 'app-help-question-res',
  templateUrl: './help-question-res.component.html',
  styleUrls: ['./help-question-res.component.scss'],
  animations:[scaling]
})
export class HelpQuestionResComponent {
  results!: IhelpResponse[];
  keywordFormat!: string;
  constructor(private routeConfig: ActivatedRoute) {}

  ngOnInit() {
    let keyword = this.routeConfig.snapshot.paramMap.get('id') || '';
    this.keywordFormat = keyword.replace(/_/g, ' ');
    const keywords = keyword.split('_');

    this.results = keywords.reduce((accumulator, key) => {
      const filteredData = DATA.filter((field) => field.question.includes(key));
      return [...accumulator, ...filteredData];
    }, [] as IhelpResponse[]);

    this.results = Array.from(new Set(this.results));
  }
}
