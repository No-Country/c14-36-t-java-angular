import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { TokenService } from 'src/app/services/token.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router)
  const tokenService = inject(TokenService)

  if(tokenService.hasToken()){
    return true;
  }
  router.navigate(['login'])
  return false;
};


