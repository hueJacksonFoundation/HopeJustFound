import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUnskilled } from 'app/shared/model/unskilled.model';

type EntityResponseType = HttpResponse<IUnskilled>;
type EntityArrayResponseType = HttpResponse<IUnskilled[]>;

@Injectable({ providedIn: 'root' })
export class UnskilledService {
    public resourceUrl = SERVER_API_URL + 'api/unskilleds';

    constructor(private http: HttpClient) {}

    create(unskilled: IUnskilled): Observable<EntityResponseType> {
        return this.http.post<IUnskilled>(this.resourceUrl, unskilled, { observe: 'response' });
    }

    update(unskilled: IUnskilled): Observable<EntityResponseType> {
        return this.http.put<IUnskilled>(this.resourceUrl, unskilled, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IUnskilled>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUnskilled[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
