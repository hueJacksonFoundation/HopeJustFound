import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUnskilled } from 'app/shared/model/unskilled.model';
import { UnskilledService } from './unskilled.service';

@Component({
    selector: 'jhi-unskilled-delete-dialog',
    templateUrl: './unskilled-delete-dialog.component.html'
})
export class UnskilledDeleteDialogComponent {
    unskilled: IUnskilled;

    constructor(private unskilledService: UnskilledService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.unskilledService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'unskilledListModification',
                content: 'Deleted an unskilled'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-unskilled-delete-popup',
    template: ''
})
export class UnskilledDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ unskilled }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UnskilledDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.unskilled = unskilled;
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
