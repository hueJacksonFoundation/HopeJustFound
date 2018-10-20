import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserContact } from 'app/shared/model/user-contact.model';
import { UserContactService } from './user-contact.service';
import { UserContactComponent } from './user-contact.component';
import { UserContactDetailComponent } from './user-contact-detail.component';
import { UserContactUpdateComponent } from './user-contact-update.component';
import { UserContactDeletePopupComponent } from './user-contact-delete-dialog.component';
import { IUserContact } from 'app/shared/model/user-contact.model';

@Injectable({ providedIn: 'root' })
export class UserContactResolve implements Resolve<IUserContact> {
    constructor(private service: UserContactService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((userContact: HttpResponse<UserContact>) => userContact.body));
        }
        return of(new UserContact());
    }
}

export const userContactRoute: Routes = [
    {
        path: 'user-contact',
        component: UserContactComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.userContact.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-contact/:id/view',
        component: UserContactDetailComponent,
        resolve: {
            userContact: UserContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.userContact.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-contact/new',
        component: UserContactUpdateComponent,
        resolve: {
            userContact: UserContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.userContact.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-contact/:id/edit',
        component: UserContactUpdateComponent,
        resolve: {
            userContact: UserContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.userContact.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userContactPopupRoute: Routes = [
    {
        path: 'user-contact/:id/delete',
        component: UserContactDeletePopupComponent,
        resolve: {
            userContact: UserContactResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'hopeJustFoundApp.userContact.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
