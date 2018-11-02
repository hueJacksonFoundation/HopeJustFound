import { IUser } from 'app/core/user/user.model';

export interface IContact {
    id?: number;
    companyName?: string;
    phoneNumber?: string;
    address?: string;
    city?: string;
    state?: string;
    zipCode?: number;
    contactDays?: string;
    contactTimes?: string;
    user?: IUser;
}

export class Contact implements IContact {
    constructor(
        public id?: number,
        public companyName?: string,
        public phoneNumber?: string,
        public address?: string,
        public city?: string,
        public state?: string,
        public zipCode?: number,
        public contactDays?: string,
        public contactTimes?: string,
        public user?: IUser
    ) {}
}
