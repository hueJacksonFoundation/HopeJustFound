import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISkilled } from 'app/shared/model/skilled.model';

type EntityResponseType = HttpResponse<ISkilled>;
type EntityArrayResponseType = HttpResponse<ISkilled[]>;

@Injectable({ providedIn: 'root' })
export class SkilledService {
    public resourceUrl = SERVER_API_URL + 'api/skilleds';

    constructor(private http: HttpClient) {}

    create(skilled: ISkilled): Observable<EntityResponseType> {
        return this.http.post<ISkilled>(this.resourceUrl, skilled, { observe: 'response' });
    }

    update(skilled: ISkilled): Observable<EntityResponseType> {
        return this.http.put<ISkilled>(this.resourceUrl, skilled, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISkilled>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISkilled[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
