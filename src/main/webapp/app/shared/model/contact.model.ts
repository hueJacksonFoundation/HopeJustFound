import { IUser } from 'app/core/user/user.model';

export interface IContact {
    id?: number;
    phoneNumber?: string;
    address?: string;
    city?: string;
    state?: string;
    zipCode?: number;
    contactDays?: string;
    contactTimes?: string;
    userid?: IUser;
}

export class Contact implements IContact {
    constructor(
        public id?: number,
        public phoneNumber?: string,
        public address?: string,
        public city?: string,
        public state?: string,
        public zipCode?: number,
        public contactDays?: string,
        public contactTimes?: string,
        public userid?: IUser
    ) {}
}
