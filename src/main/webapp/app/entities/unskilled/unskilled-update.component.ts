import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUnskilled } from 'app/shared/model/unskilled.model';
import { UnskilledService } from './unskilled.service';
import { IDonation } from 'app/shared/model/donation.model';
import { DonationService } from 'app/entities/donation';

@Component({
    selector: 'jhi-unskilled-update',
    templateUrl: './unskilled-update.component.html'
})
export class UnskilledUpdateComponent implements OnInit {
    unskilled: IUnskilled;
    isSaving: boolean;

    donations: IDonation[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private unskilledService: UnskilledService,
        private donationService: DonationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ unskilled }) => {
            this.unskilled = unskilled;
        });
        this.donationService.query().subscribe(
            (res: HttpResponse<IDonation[]>) => {
                this.donations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.unskilled.id !== undefined) {
            this.subscribeToSaveResponse(this.unskilledService.update(this.unskilled));
        } else {
            this.subscribeToSaveResponse(this.unskilledService.create(this.unskilled));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUnskilled>>) {
        result.subscribe((res: HttpResponse<IUnskilled>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackDonationById(index: number, item: IDonation) {
        return item.id;
    }
}
