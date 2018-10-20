import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ISkilled } from 'app/shared/model/skilled.model';
import { SkilledService } from './skilled.service';
import { IDonation } from 'app/shared/model/donation.model';
import { DonationService } from 'app/entities/donation';

@Component({
    selector: 'jhi-skilled-update',
    templateUrl: './skilled-update.component.html'
})
export class SkilledUpdateComponent implements OnInit {
    skilled: ISkilled;
    isSaving: boolean;

    donations: IDonation[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private skilledService: SkilledService,
        private donationService: DonationService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ skilled }) => {
            this.skilled = skilled;
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
        if (this.skilled.id !== undefined) {
            this.subscribeToSaveResponse(this.skilledService.update(this.skilled));
        } else {
            this.subscribeToSaveResponse(this.skilledService.create(this.skilled));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISkilled>>) {
        result.subscribe((res: HttpResponse<ISkilled>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
