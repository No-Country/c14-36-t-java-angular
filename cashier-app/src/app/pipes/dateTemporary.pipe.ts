import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateTemporary'
})
export class DateTemporaryPipe implements PipeTransform {

  transform(value: number[]): string {
    const year = value[0];
    const month = value[1];
    const day = value[2];
    const hour = value[3];
    const minutes = value[4];
    const seconds = value[5];

    return `${year}/${month}/${day}  ${hour}:${minutes}:${seconds}`;
  }

}
