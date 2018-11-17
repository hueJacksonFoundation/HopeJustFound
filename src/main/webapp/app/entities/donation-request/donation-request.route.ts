import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DonationRequest } from 'app/shared/model/donation-request.model';
import { DonationRequestService } from './donation-request.service';
import { DonationRequestComponent } from './donation-request.component';
import { DonationRequestDetailComponent } from './donation-request-detail.component';
import { DonationRequestUpdateComponent } from './donation-request-update.component';
import { DonationRequestDeletePopupComponent } from './donation-request-delete-dialog.component';
import { IDonationRequest } from 'app/shared/model/donation-request.model';

@Injectable({ providedIn: 'root' })
export class DonationRequestResolve implements Resolve<IDonationRequest> {
    constructor(private service: DonationRequestService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DonationRequest> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DonationRequest>) => response.ok),
                map((donationRequest: HttpResponse<DonationRequest>) => donationRequest.body)
            );
        }
        return of(new DonationRequest());
    }
}

export const donationRequestRoute: Routes = [
    {
        path: 'donation-request',
        component: DonationRequestComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DonationRequests'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'donation-request/:id/view',
        component: DonationRequestDetailComponent,
        resolve: {
            donationRequest: DonationRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DonationRequests'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'donation-request/new',
        component: DonationRequestUpdateComponent,
        resolve: {
            donationRequest: DonationRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DonationRequests'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'donation-request/:id/edit',
        component: DonationRequestUpdateComponent,
        resolve: {
            donationRequest: DonationRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DonationRequests'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const donationRequestPopupRoute: Routes = [
    {
        path: 'donation-request/:id/delete',
        component: DonationRequestDeletePopupComponent,
        resolve: {
            donationRequest: DonationRequestResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DonationRequests'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
