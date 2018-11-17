import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDonationRequest } from 'app/shared/model/donation-request.model';
import { DonationRequestService } from './donation-request.service';

@Component({
    selector: 'jhi-donation-request-delete-dialog',
    templateUrl: './donation-request-delete-dialog.component.html'
})
export class DonationRequestDeleteDialogComponent {
    donationRequest: IDonationRequest;

    constructor(
        private donationRequestService: DonationRequestService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.donationRequestService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'donationRequestListModification',
                content: 'Deleted an donationRequest'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-donation-request-delete-popup',
    template: ''
})
export class DonationRequestDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ donationRequest }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DonationRequestDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.donationRequest = donationRequest;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
