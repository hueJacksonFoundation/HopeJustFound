import { Moment } from 'moment';
import { ISkilled } from 'app/shared/model//skilled.model';
import { IUnskilled } from 'app/shared/model//unskilled.model';
import { IGoods } from 'app/shared/model//goods.model';
import { IUser } from 'app/core/user/user.model';

export interface IDonation {
    id?: number;
    type?: string;
    initialDate?: Moment;
    expireDate?: Moment;
    skilled?: ISkilled;
    unskilled?: IUnskilled;
    goods?: IGoods;
    user?: IUser;
}

export class Donation implements IDonation {
    constructor(
        public id?: number,
        public type?: string,
        public initialDate?: Moment,
        public expireDate?: Moment,
        public skilled?: ISkilled,
        public unskilled?: IUnskilled,
        public goods?: IGoods,
        public user?: IUser
    ) {}
}
