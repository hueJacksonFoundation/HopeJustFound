export interface IUserContact {
    id?: number;
    phoneNumber?: string;
    address?: string;
    city?: string;
    state?: string;
    zipCode?: number;
    contactDays?: string;
    contactTimes?: string;
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
        public contactTimes?: string
    ) {}
}
