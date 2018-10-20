import { IDonation } from 'app/shared/model//donation.model';

export interface IGoods {
    id?: number;
    type?: string;
    condition?: string;
    descriptionContentType?: string;
    description?: any;
    transport?: string;
    imageContentType?: string;
    image?: any;
    donation?: IDonation;
}

export class Goods implements IGoods {
    constructor(
        public id?: number,
        public type?: string,
        public condition?: string,
        public descriptionContentType?: string,
        public description?: any,
        public transport?: string,
        public imageContentType?: string,
        public image?: any,
        public donation?: IDonation
    ) {}
}
