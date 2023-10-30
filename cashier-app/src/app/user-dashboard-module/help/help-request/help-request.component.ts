import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-help-request',
  templateUrl: './help-request.component.html',
  styleUrls: ['./help-request.component.scss'],
})
export class HelpRequestComponent {
  searchForm!:FormGroup;
  constructor(private router:Router, private fb:FormBuilder){
    this.searchForm = this.fb.group({
      keyword:['', Validators.required]
    })
  }

  toResponse(e:MouseEvent){
    const id = (e.target as HTMLAnchorElement).id;
    this.router.navigate(['user/help/helpRes',id])
  }
  onSubmit(e:Event){
    const {keyword} = this.searchForm.value;

    const keywordFormat = (keyword as string).trim().replace(/\s+/g, '_').toLocaleLowerCase();
    this.router.navigate(["user/help/helpQuestionRes",keywordFormat])
  }
}
