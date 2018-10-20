import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStatus } from 'app/shared/model/status.model';

type EntityResponseType = HttpResponse<IStatus>;
type EntityArrayResponseType = HttpResponse<IStatus[]>;

@Injectable({ providedIn: 'root' })
export class StatusService {
    public resourceUrl = SERVER_API_URL + 'api/statuses';

    constructor(private http: HttpClient) {}

    create(status: IStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(status);
        return this.http
            .post<IStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(status: IStatus): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(status);
        return this.http
            .put<IStatus>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(status: IStatus): IStatus {
        const copy: IStatus = Object.assign({}, status, {
            approved: status.approved != null && status.approved.isValid() ? status.approved.format(DATE_FORMAT) : null,
            submitted: status.submitted != null && status.submitted.isValid() ? status.submitted.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.approved = res.body.approved != null ? moment(res.body.approved) : null;
        res.body.submitted = res.body.submitted != null ? moment(res.body.submitted) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((status: IStatus) => {
            status.approved = status.approved != null ? moment(status.approved) : null;
            status.submitted = status.submitted != null ? moment(status.submitted) : null;
        });
        return res;
    }
}
