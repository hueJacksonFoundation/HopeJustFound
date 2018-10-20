import { Moment } from 'moment';

export interface IStatus {
    id?: number;
    approved?: Moment;
    submitted?: Moment;
    approvedBy?: string;
}

export class Status implements IStatus {
    constructor(public id?: number, public approved?: Moment, public submitted?: Moment, public approvedBy?: string) {}
}
