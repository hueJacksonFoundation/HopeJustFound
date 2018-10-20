import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IGoods } from 'app/shared/model/goods.model';
import { Principal } from 'app/core';
import { GoodsService } from './goods.service';

@Component({
    selector: 'jhi-goods',
    templateUrl: './goods.component.html'
})
export class GoodsComponent implements OnInit, OnDestroy {
    goods: IGoods[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private goodsService: GoodsService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.goodsService.query().subscribe(
            (res: HttpResponse<IGoods[]>) => {
                this.goods = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInGoods();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IGoods) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInGoods() {
        this.eventSubscriber = this.eventManager.subscribe('goodsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
