import { IUser } from 'app/core/user/user.model';

export interface IUserContact {
    id?: number;
    phoneNumber?: string;
    address?: string;
    city?: string;
    state?: string;
    zipCode?: number;
    contactDays?: string;
    contactTimes?: string;
    user?: IUser;
}

export class UserContact implements IUserContact {
    constructor(
        public id?: number,
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
