import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IStatus {
    id?: number;
    approved?: Moment;
    submitted?: Moment;
    role?: string;
    userid?: IUser;
}

export class Status implements IStatus {
    constructor(public id?: number, public approved?: Moment, public submitted?: Moment, public role?: string, public userid?: IUser) {}
}
