import { IDonation } from 'app/shared/model//donation.model';

export interface IUnskilled {
    id?: number;
    climate?: string;
    intensity?: string;
    number?: number;
    donation?: IDonation;
}

export class Unskilled implements IUnskilled {
    constructor(
        public id?: number,
        public climate?: string,
        public intensity?: string,
        public number?: number,
        public donation?: IDonation
    ) {}
}
