import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CategoriaComponent } from './categoria-form/categoria-form.component';
import { UnidadComponent } from './unidad/unidad.component';

export const routes: Routes = [
    {
        path:'',
        component:HomeComponent,
        title:'Producto'

    },
    {
        path:'categoria',
        component:CategoriaComponent,
        title:'Formulario de categoria'

    },
    {
        path:'unidad',
        component:UnidadComponent,
        title:'Formulario de unidad'

    },
    {
        path:'**',
        redirectTo:'',
        pathMatch:'full'
    }
];
