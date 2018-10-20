import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGoods } from 'app/shared/model/goods.model';

type EntityResponseType = HttpResponse<IGoods>;
type EntityArrayResponseType = HttpResponse<IGoods[]>;

@Injectable({ providedIn: 'root' })
export class GoodsService {
    public resourceUrl = SERVER_API_URL + 'api/goods';

    constructor(private http: HttpClient) {}

    create(goods: IGoods): Observable<EntityResponseType> {
        return this.http.post<IGoods>(this.resourceUrl, goods, { observe: 'response' });
    }

    update(goods: IGoods): Observable<EntityResponseType> {
        return this.http.put<IGoods>(this.resourceUrl, goods, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IGoods>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IGoods[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
