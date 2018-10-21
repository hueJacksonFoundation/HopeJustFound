import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUserContact } from 'app/shared/model/user-contact.model';
import { UserContactService } from './user-contact.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-user-contact-update',
    templateUrl: './user-contact-update.component.html'
})
export class UserContactUpdateComponent implements OnInit {
    userContact: IUserContact;
    isSaving: boolean;

    users: IUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private userContactService: UserContactService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userContact }) => {
            this.userContact = userContact;
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
        if (this.userContact.id !== undefined) {
            this.subscribeToSaveResponse(this.userContactService.update(this.userContact));
        } else {
            this.subscribeToSaveResponse(this.userContactService.create(this.userContact));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUserContact>>) {
        result.subscribe((res: HttpResponse<IUserContact>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
