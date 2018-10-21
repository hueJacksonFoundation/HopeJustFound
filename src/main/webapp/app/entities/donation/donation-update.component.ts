import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IDonation } from 'app/shared/model/donation.model';
import { DonationService } from './donation.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-donation-update',
    templateUrl: './donation-update.component.html'
})
export class DonationUpdateComponent implements OnInit {
    donation: IDonation;
    isSaving: boolean;

    users: IUser[];
    initialDateDp: any;
    expireDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private donationService: DonationService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ donation }) => {
            this.donation = donation;
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
        if (this.donation.id !== undefined) {
            this.subscribeToSaveResponse(this.donationService.update(this.donation));
        } else {
            this.subscribeToSaveResponse(this.donationService.create(this.donation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDonation>>) {
        result.subscribe((res: HttpResponse<IDonation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
