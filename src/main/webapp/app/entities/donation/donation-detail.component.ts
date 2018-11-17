import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IDonation } from 'app/shared/model/donation.model';

@Component({
    selector: 'jhi-donation-detail',
    templateUrl: './donation-detail.component.html'
})
export class DonationDetailComponent implements OnInit {
    donation: IDonation;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ donation }) => {
            this.donation = donation;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
