import {
  Component,
  ElementRef,
  EventEmitter,
  Output,
  QueryList,
  ViewChildren,
} from '@angular/core';

@Component({
  selector: 'app-side-bar-alt',
  templateUrl: './side-bar-alt.component.html',
  styleUrls: ['./side-bar-alt.component.scss'],
})
export class SideBarAltComponent {
  @ViewChildren('collapse', { read: ElementRef }) elementsHidden!: QueryList<ElementRef>;

  @Output() toUserComp = new EventEmitter<boolean>();
  sidebarStatus = false;

  setSidebarStatus() {
    this.sidebarStatus = !this.sidebarStatus;
    this.toUserComp.emit(this.sidebarStatus);
    this.elementsHidden.forEach(el=>{
      const tagName = el.nativeElement.tagName.toLowerCase();
      if(tagName === "span"){
        el.nativeElement.classList.toggle("activeList");
      }else{
        el.nativeElement.classList.toggle("active");
      }
    })
  }
}
