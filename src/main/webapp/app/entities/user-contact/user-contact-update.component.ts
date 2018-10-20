import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IUserContact } from 'app/shared/model/user-contact.model';
import { UserContactService } from './user-contact.service';

@Component({
    selector: 'jhi-user-contact-update',
    templateUrl: './user-contact-update.component.html'
})
export class UserContactUpdateComponent implements OnInit {
    userContact: IUserContact;
    isSaving: boolean;

    constructor(private userContactService: UserContactService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userContact }) => {
            this.userContact = userContact;
        });
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
}
