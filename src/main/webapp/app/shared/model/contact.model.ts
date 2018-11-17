import { IUser } from 'app/core/user/user.model';

export interface IContact {
    id?: number;
    companyName?: string;
    companyEIN?: string;
    companyWebsite?: string;
    phoneNumber?: string;
    mailingAddress?: string;
    mailingCity?: string;
    mailingState?: string;
    mailingZipCode?: number;
    phyisicalAddress?: string;
    phyisicalCity?: string;
    phyisicalState?: string;
    phyisicalZipCode?: number;
    contactDays?: string;
    contactTimes?: string;
    user?: IUser;
}

export class Contact implements IContact {
    constructor(
        public id?: number,
        public companyName?: string,
        public companyEIN?: string,
        public companyWebsite?: string,
        public phoneNumber?: string,
        public mailingAddress?: string,
        public mailingCity?: string,
        public mailingState?: string,
        public mailingZipCode?: number,
        public phyisicalAddress?: string,
        public phyisicalCity?: string,
        public phyisicalState?: string,
        public phyisicalZipCode?: number,
        public contactDays?: string,
        public contactTimes?: string,
        public user?: IUser
    ) {}
}
