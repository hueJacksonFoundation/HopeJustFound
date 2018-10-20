import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IGoods } from 'app/shared/model/goods.model';
import { GoodsService } from './goods.service';
import { IDonation } from 'app/shared/model/donation.model';
import { DonationService } from 'app/entities/donation';

@Component({
    selector: 'jhi-goods-update',
    templateUrl: './goods-update.component.html'
})
export class GoodsUpdateComponent implements OnInit {
    goods: IGoods;
    isSaving: boolean;

    donations: IDonation[];

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private goodsService: GoodsService,
        private donationService: DonationService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ goods }) => {
            this.goods = goods;
        });
        this.donationService.query().subscribe(
            (res: HttpResponse<IDonation[]>) => {
                this.donations = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.goods, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.goods.id !== undefined) {
            this.subscribeToSaveResponse(this.goodsService.update(this.goods));
        } else {
            this.subscribeToSaveResponse(this.goodsService.create(this.goods));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGoods>>) {
        result.subscribe((res: HttpResponse<IGoods>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
