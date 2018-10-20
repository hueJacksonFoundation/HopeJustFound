import { IDonation } from 'app/shared/model//donation.model';

export interface ISkilled {
    id?: number;
    experience?: string;
    type?: string;
    number?: number;
    donation?: IDonation;
}

export class Skilled implements ISkilled {
    constructor(
        public id?: number,
        public experience?: string,
        public type?: string,
        public number?: number,
        public donation?: IDonation
    ) {}
}
