import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Unskilled } from 'app/shared/model/unskilled.model';
import { UnskilledService } from './unskilled.service';
import { UnskilledComponent } from './unskilled.component';
import { UnskilledDetailComponent } from './unskilled-detail.component';
import { UnskilledUpdateComponent } from './unskilled-update.component';
import { UnskilledDeletePopupComponent } from './unskilled-delete-dialog.component';
import { IUnskilled } from 'app/shared/model/unskilled.model';

@Injectable({ providedIn: 'root' })
export class UnskilledResolve implements Resolve<IUnskilled> {
    constructor(private service: UnskilledService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((unskilled: HttpResponse<Unskilled>) => unskilled.body));
        }
        return of(new Unskilled());
    }
}

export const unskilledRoute: Routes = [
    {
        path: 'unskilled',
        component: UnskilledComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.unskilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'unskilled/:id/view',
        component: UnskilledDetailComponent,
        resolve: {
            unskilled: UnskilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.unskilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'unskilled/new',
        component: UnskilledUpdateComponent,
        resolve: {
            unskilled: UnskilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.unskilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'unskilled/:id/edit',
        component: UnskilledUpdateComponent,
        resolve: {
            unskilled: UnskilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.unskilled.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const unskilledPopupRoute: Routes = [
    {
        path: 'unskilled/:id/delete',
        component: UnskilledDeletePopupComponent,
        resolve: {
            unskilled: UnskilledResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.unskilled.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
