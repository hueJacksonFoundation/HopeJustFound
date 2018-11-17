import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IDonationRequest } from 'app/shared/model/donation-request.model';
import { DonationRequestService } from './donation-request.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-donation-request-update',
    templateUrl: './donation-request-update.component.html'
})
export class DonationRequestUpdateComponent implements OnInit {
    donationRequest: IDonationRequest;
    isSaving: boolean;

    users: IUser[];
    initialDateDp: any;
    expireDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private donationRequestService: DonationRequestService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ donationRequest }) => {
            this.donationRequest = donationRequest;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.donationRequest.id !== undefined) {
            this.subscribeToSaveResponse(this.donationRequestService.update(this.donationRequest));
        } else {
            this.subscribeToSaveResponse(this.donationRequestService.create(this.donationRequest));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDonationRequest>>) {
        result.subscribe((res: HttpResponse<IDonationRequest>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
