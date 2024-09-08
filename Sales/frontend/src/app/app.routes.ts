import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductoFormComponent } from './producto-form/producto-form.component';

export const routes: Routes = [
    {
        path:'',
        component:HomeComponent,
        title:'Pagina de Inicio'

    },
    {
        path:'producto-form/:id',
        component:ProductoFormComponent,
        title:'Formulario de Producto'

    },
    {
        path:'**',
        redirectTo:'',
        pathMatch:'full'
    }
];
