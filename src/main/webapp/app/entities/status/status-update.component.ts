import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';

import { IStatus } from 'app/shared/model/status.model';
import { StatusService } from './status.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-status-update',
    templateUrl: './status-update.component.html'
})
export class StatusUpdateComponent implements OnInit {
    status: IStatus;
    isSaving: boolean;

    users: IUser[];
    approvedDp: any;
    submittedDp: any;

    constructor(
        private jhiAlertService: JhiAlertService,
        private statusService: StatusService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ status }) => {
            this.status = status;
        });
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
        if (this.status.id !== undefined) {
            this.subscribeToSaveResponse(this.statusService.update(this.status));
        } else {
            this.subscribeToSaveResponse(this.statusService.create(this.status));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStatus>>) {
        result.subscribe((res: HttpResponse<IStatus>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
