import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IDonation } from 'app/shared/model/donation.model';
import { Principal } from 'app/core';
import { DonationService } from './donation.service';

@Component({
    selector: 'jhi-donation',
    templateUrl: './donation.component.html'
})
export class DonationComponent implements OnInit, OnDestroy {
    donations: IDonation[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private donationService: DonationService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.donationService.query().subscribe(
            (res: HttpResponse<IDonation[]>) => {
                this.donations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInDonations();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IDonation) {
        return item.id;
    }

    registerChangeInDonations() {
        this.eventSubscriber = this.eventManager.subscribe('donationListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
