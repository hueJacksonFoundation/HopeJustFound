import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IStatus {
    id?: number;
    approved?: Moment;
    submitted?: Moment;
    user?: IUser;
}

export class Status implements IStatus {
    constructor(public id?: number, public approved?: Moment, public submitted?: Moment, public user?: IUser) {}
}
