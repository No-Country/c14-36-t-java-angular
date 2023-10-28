import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'pipeTransform'
})
export class PipeTransformPipe implements PipeTransform {

  transform(items: any[], searchText: string): any[] {
    if (!items || !searchText) {
      return items;
    }
    searchText = searchText.toLowerCase();

    const filteredItems = items.filter(item => {
      return item.destination.toLowerCase().includes(searchText) ||
             item.type.toLowerCase().includes(searchText) ||
             item.amount.toString().toLowerCase().includes(searchText);
    });

    // Si no hay resultados, devuelve un array con un objeto que indica "No hay resultados"
    return filteredItems.length > 0 ? filteredItems : [{ noResults: true }];
  }

}
