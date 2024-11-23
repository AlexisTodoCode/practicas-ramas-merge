import { Routes } from '@angular/router';
import { VentaComponent } from './components/venta/venta.component';

export const routes: Routes = [
    {
        path:'',
        component:VentaComponent,
        title:'venta'

    },
    {
        path:'**',
        redirectTo:'',
        pathMatch:'full'
    }
];