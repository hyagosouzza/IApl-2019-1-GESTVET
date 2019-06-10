import { Injectable } from '@angular/core';

declare let $: any;

@Injectable()
export class NotifyService {

  constructor() { }

  createNotify(title: string, message: string, colorType: string) {
    $('.toast').toast({ delay: 3000 });
    $('.toast').toast('show');

    let toastTitle = document.getElementById('toast-title');
    toastTitle.innerText = title;
    toastTitle.style.color = "white";
    document.getElementById('toast-body').innerText = message;
    document.getElementById('toast-title-div').style.backgroundColor = colorType;
  }

}
