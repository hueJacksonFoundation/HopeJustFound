import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IGoods } from 'app/shared/model/goods.model';

@Component({
    selector: 'jhi-goods-detail',
    templateUrl: './goods-detail.component.html'
})
export class GoodsDetailComponent implements OnInit {
    goods: IGoods;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ goods }) => {
            this.goods = goods;
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
