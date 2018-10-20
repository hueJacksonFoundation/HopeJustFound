import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Skilled } from 'app/shared/model/skilled.model';
import { SkilledService } from './skilled.service';
import { SkilledComponent } from './skilled.component';
import { SkilledDetailComponent } from './skilled-detail.component';
import { SkilledUpdateComponent } from './skilled-update.component';
import { SkilledDeletePopupComponent } from './skilled-delete-dialog.component';
import { ISkilled } from 'app/shared/model/skilled.model';

@Injectable({ providedIn: 'root' })
export class SkilledResolve implements Resolve<ISkilled> {
    constructor(private service: SkilledService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((skilled: HttpResponse<Skilled>) => skilled.body));
        }
        return of(new Skilled());
    }
}

export const skilledRoute: Routes = [
    {
        path: 'skilled',
        component: SkilledComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.skilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'skilled/:id/view',
        component: SkilledDetailComponent,
        resolve: {
            skilled: SkilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.skilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'skilled/new',
        component: SkilledUpdateComponent,
        resolve: {
            skilled: SkilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.skilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'skilled/:id/edit',
        component: SkilledUpdateComponent,
        resolve: {
            skilled: SkilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.skilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const skilledPopupRoute: Routes = [
    {
        path: 'skilled/:id/delete',
        component: SkilledDeletePopupComponent,
        resolve: {
            skilled: SkilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.skilled.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
