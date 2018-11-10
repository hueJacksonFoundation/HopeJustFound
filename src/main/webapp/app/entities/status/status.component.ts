import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IStatus } from 'app/shared/model/status.model';
import { IUser, Principal, UserService } from 'app/core';
import { StatusService } from './status.service';

@Component({
    selector: 'jhi-status',
    templateUrl: './status.component.html'
})
export class StatusComponent implements OnInit, OnDestroy {
    statuses: IStatus[];
    currentAccount: any;
    eventSubscriber: Subscription;

    // addede
    users: IUser[];
    page: any;
    unapprovedUser: IUser[];
    user: any;

    constructor(
        private statusService: StatusService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal,
        private userService: UserService
    ) {}

    loadAll() {
        this.statusService.query().subscribe(
            (res: HttpResponse<IStatus[]>) => {
                this.statuses = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInStatuses();
        this.getUsers();
        this.checkStatus();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IStatus) {
        return item.id;
    }

    registerChangeInStatuses() {
        this.eventSubscriber = this.eventManager.subscribe('statusListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    // added below
    private getUsers() {
        this.userService.getAll().subscribe(data => {
            this.users = data;
        });
    }

    private checkStatus() {
        this.statuses.forEach(status => {
            if (status.approved != null) {
                this.getUserById(status.id);
            }
        });
    }

    private getUserById(id) {
        this.users.forEach(user => {
            if (user.id === id) {
                this.unapprovedUser.push(user);
            }
        });
    }
}
