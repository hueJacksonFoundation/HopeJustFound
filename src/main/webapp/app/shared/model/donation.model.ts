import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IDonation {
    id?: number;
    type?: string;
    initialDate?: Moment;
    expireDate?: Moment;
    condition?: string;
    description?: string;
    experience?: string;
    climate?: string;
    intensity?: string;
    numberOfVolunteers?: number;
    userid?: IUser;
}

export class Donation implements IDonation {
    constructor(
        public id?: number,
        public type?: string,
        public initialDate?: Moment,
        public expireDate?: Moment,
        public condition?: string,
        public description?: string,
        public experience?: string,
        public climate?: string,
        public intensity?: string,
        public numberOfVolunteers?: number,
        public userid?: IUser
    ) {}
}
