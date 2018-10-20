import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IDonation } from 'app/shared/model/donation.model';
import { DonationService } from './donation.service';
import { ISkilled } from 'app/shared/model/skilled.model';
import { SkilledService } from 'app/entities/skilled';
import { IUnskilled } from 'app/shared/model/unskilled.model';
import { UnskilledService } from 'app/entities/unskilled';
import { IGoods } from 'app/shared/model/goods.model';
import { GoodsService } from 'app/entities/goods';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-donation-update',
    templateUrl: './donation-update.component.html'
})
export class DonationUpdateComponent implements OnInit {
    donation: IDonation;
    isSaving: boolean;

    skilleds: ISkilled[];

    unskilleds: IUnskilled[];

    goods: IGoods[];

    users: IUser[];
    initialDateDp: any;
    expireDateDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private donationService: DonationService,
        private skilledService: SkilledService,
        private unskilledService: UnskilledService,
        private goodsService: GoodsService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ donation }) => {
            this.donation = donation;
        });
        this.skilledService.query({ filter: 'donation(id)-is-null' }).subscribe(
            (res: HttpResponse<ISkilled[]>) => {
                if (!this.donation.skilled || !this.donation.skilled.id) {
                    this.skilleds = res.body;
                } else {
                    this.skilledService.find(this.donation.skilled.id).subscribe(
                        (subRes: HttpResponse<ISkilled>) => {
                            this.skilleds = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.unskilledService.query({ filter: 'donation(id)-is-null' }).subscribe(
            (res: HttpResponse<IUnskilled[]>) => {
                if (!this.donation.unskilled || !this.donation.unskilled.id) {
                    this.unskilleds = res.body;
                } else {
                    this.unskilledService.find(this.donation.unskilled.id).subscribe(
                        (subRes: HttpResponse<IUnskilled>) => {
                            this.unskilleds = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.goodsService.query({ filter: 'donation(id)-is-null' }).subscribe(
            (res: HttpResponse<IGoods[]>) => {
                if (!this.donation.goods || !this.donation.goods.id) {
                    this.goods = res.body;
                } else {
                    this.goodsService.find(this.donation.goods.id).subscribe(
                        (subRes: HttpResponse<IGoods>) => {
                            this.goods = [subRes.body].concat(res.body);
                        },
                        (subRes: HttpErrorResponse) => this.onError(subRes.message)
                    );
                }
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.donation.id !== undefined) {
            this.subscribeToSaveResponse(this.donationService.update(this.donation));
        } else {
            this.subscribeToSaveResponse(this.donationService.create(this.donation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDonation>>) {
        result.subscribe((res: HttpResponse<IDonation>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSkilledById(index: number, item: ISkilled) {
        return item.id;
    }

    trackUnskilledById(index: number, item: IUnskilled) {
        return item.id;
    }

    trackGoodsById(index: number, item: IGoods) {
        return item.id;
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
