import {
  animate,
  query,
  stagger,
  style,
  transition,
  trigger,
} from '@angular/animations';

export const fadeAnimation = trigger('fadeEffect', [
  transition(':enter', [
    style({ opacity: 0, transform: 'scale(0.7)' }),
    animate(300, style({ opacity: 1, transform: 'scale(1)' })),
  ]),
  transition(':leave', [animate(300, style({ opacity: 0 }))]),
]);

export const enterLateral = trigger('lateralEffect', [
  transition(':enter', [
    style({
      opacity: 0,
      transform: 'translateX(20px)',
    }),
    animate(
      300,
      style({
        opacity: 1,
        transform: 'translateX(0px)',
      })
    ),
  ]),
]);
export const scaling = trigger('scalingEffect', [
  transition("* => *",
    query(':enter', [
      style({
        opacity: 0,
        transform: 'translateX(15px)',
      }),
      stagger(100, [
          animate(300,style({
              opacity: 1,
              transform: 'translate(0px)',
            })
          )
        ]
      ),
    ], {optional:true})
  ),
]);
