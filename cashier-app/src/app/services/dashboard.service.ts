import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class DashboardService{

  constructor() { }

  showCvu() {
    const cvuData = document.getElementById("cvuData");
    const copyButton = document.getElementById("copyButton");

    if (!cvuData || !copyButton) {
        console.log("Elementos no encontrados en el DOM.");
        return;
    }

    if (cvuData.style.display === "none") {
        cvuData.style.display = "inline"; // Muestra el CVU
        copyButton.style.display = "inline"; // Muestra el botón de copiar

        // Copiar al portapapeles cuando se hace clic en el botón
        copyButton.addEventListener("click", () => {
            const cvuText = cvuData.innerText;
            const textArea = document.createElement("textarea");
            textArea.value = cvuText;
            document.body.appendChild(textArea);
            textArea.select();
            document.execCommand("copy");
            document.body.removeChild(textArea);
            Swal.fire({
              position: 'center',
              icon: 'success',
              title: 'Cvu copiado',
              showConfirmButton: false,
              timer: 1500,
              background: '#153230',
              color: '#fff'
            })
            cvuData.style.display = "none"; // Oculta el CVU
            copyButton.style.display = "none"; // Oculta el botón de copiar
        });

        // Ocultar el CVU y el botón después de 10 segundos
        setTimeout(() => {
            cvuData.style.display = "none";
            copyButton.style.display = "none";
        }, 10000); // 10000 milisegundos = 10 segundos
    }
}
}
