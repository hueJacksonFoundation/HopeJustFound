import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDonationRequest } from 'app/shared/model/donation-request.model';
import { Principal } from 'app/core';
import { DonationRequestService } from './donation-request.service';

@Component({
    selector: 'jhi-donation-request',
    templateUrl: './donation-request.component.html'
})
export class DonationRequestComponent implements OnInit, OnDestroy {
    donationRequests: IDonationRequest[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private donationRequestService: DonationRequestService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.donationRequestService.query().subscribe(
            (res: HttpResponse<IDonationRequest[]>) => {
                this.donationRequests = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDonationRequests();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDonationRequest) {
        return item.id;
    }

    registerChangeInDonationRequests() {
        this.eventSubscriber = this.eventManager.subscribe('donationRequestListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
