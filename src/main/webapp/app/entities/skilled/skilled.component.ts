import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISkilled } from 'app/shared/model/skilled.model';
import { Principal } from 'app/core';
import { SkilledService } from './skilled.service';

@Component({
    selector: 'jhi-skilled',
    templateUrl: './skilled.component.html'
})
export class SkilledComponent implements OnInit, OnDestroy {
    skilleds: ISkilled[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private skilledService: SkilledService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.skilledService.query().subscribe(
            (res: HttpResponse<ISkilled[]>) => {
                this.skilleds = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSkilleds();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISkilled) {
        return item.id;
    }

    registerChangeInSkilleds() {
        this.eventSubscriber = this.eventManager.subscribe('skilledListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
