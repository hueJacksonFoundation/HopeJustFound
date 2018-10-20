import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUnskilled } from 'app/shared/model/unskilled.model';
import { Principal } from 'app/core';
import { UnskilledService } from './unskilled.service';

@Component({
    selector: 'jhi-unskilled',
    templateUrl: './unskilled.component.html'
})
export class UnskilledComponent implements OnInit, OnDestroy {
    unskilleds: IUnskilled[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private unskilledService: UnskilledService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.unskilledService.query().subscribe(
            (res: HttpResponse<IUnskilled[]>) => {
                this.unskilleds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInUnskilleds();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUnskilled) {
        return item.id;
    }

    registerChangeInUnskilleds() {
        this.eventSubscriber = this.eventManager.subscribe('unskilledListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
