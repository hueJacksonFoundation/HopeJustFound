import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDonationRequest } from 'app/shared/model/donation-request.model';

type EntityResponseType = HttpResponse<IDonationRequest>;
type EntityArrayResponseType = HttpResponse<IDonationRequest[]>;

@Injectable({ providedIn: 'root' })
export class DonationRequestService {
    public resourceUrl = SERVER_API_URL + 'api/donation-requests';

    constructor(private http: HttpClient) {}

    create(donationRequest: IDonationRequest): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(donationRequest);
        return this.http
            .post<IDonationRequest>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(donationRequest: IDonationRequest): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(donationRequest);
        return this.http
            .put<IDonationRequest>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDonationRequest>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDonationRequest[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(donationRequest: IDonationRequest): IDonationRequest {
        const copy: IDonationRequest = Object.assign({}, donationRequest, {
            initialDate:
                donationRequest.initialDate != null && donationRequest.initialDate.isValid()
                    ? donationRequest.initialDate.format(DATE_FORMAT)
                    : null,
            expireDate:
                donationRequest.expireDate != null && donationRequest.expireDate.isValid()
                    ? donationRequest.expireDate.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.initialDate = res.body.initialDate != null ? moment(res.body.initialDate) : null;
            res.body.expireDate = res.body.expireDate != null ? moment(res.body.expireDate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((donationRequest: IDonationRequest) => {
                donationRequest.initialDate = donationRequest.initialDate != null ? moment(donationRequest.initialDate) : null;
                donationRequest.expireDate = donationRequest.expireDate != null ? moment(donationRequest.expireDate) : null;
            });
        }
        return res;
    }
}
