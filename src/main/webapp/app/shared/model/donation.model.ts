import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export interface IDonation {
    id?: number;
    goodsType?: string;
    serviceType?: string;
    imagesContentType?: string;
    images?: any;
    initialDate?: Moment;
    expireDate?: Moment;
    condition?: string;
    description?: string;
    numberOfVolunteers?: number;
    user?: IUser;
}

export class Donation implements IDonation {
    constructor(
        public id?: number,
        public goodsType?: string,
        public serviceType?: string,
        public imagesContentType?: string,
        public images?: any,
        public initialDate?: Moment,
        public expireDate?: Moment,
        public condition?: string,
        public description?: string,
        public numberOfVolunteers?: number,
        public user?: IUser
    ) {}
}
