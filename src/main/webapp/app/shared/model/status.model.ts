import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IStatus {
    id?: number;
    approved?: Moment;
    submitted?: Moment;
    approvedBy?: string;
    user?: IUser;
}

export class Status implements IStatus {
    constructor(public id?: number, public approved?: Moment, public submitted?: Moment, public approvedBy?: string, public user?: IUser) {}
}
