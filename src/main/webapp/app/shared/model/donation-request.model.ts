import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IDonationRequest {
    id?: number;
    type?: string;
    initialDate?: Moment;
    expireDate?: Moment;
    condition?: string;
    description?: string;
    experience?: string;
    numberOfVolunteers?: number;
    user?: IUser;
}

export class DonationRequest implements IDonationRequest {
    constructor(
        public id?: number,
        public type?: string,
        public initialDate?: Moment,
        public expireDate?: Moment,
        public condition?: string,
        public description?: string,
        public experience?: string,
        public numberOfVolunteers?: number,
        public user?: IUser
    ) {}
}
