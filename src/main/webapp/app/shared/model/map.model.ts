export interface IMap {
    id?: number;
}

export class Map implements IMap {
    constructor(public id?: number) {}
}
