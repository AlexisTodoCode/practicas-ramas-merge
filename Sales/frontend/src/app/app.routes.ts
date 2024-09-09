import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CategoriaComponent } from './categoria-form/categoria-form.component';

export const routes: Routes = [
    {
        path:'',
        component:HomeComponent,
        title:'Producto'

    },
    {
        path:'categoria',
        component:CategoriaComponent,
        title:'Formulario de Producto'

    },
    {
        path:'**',
        redirectTo:'',
        pathMatch:'full'
    }
];
