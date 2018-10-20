import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUnskilled } from 'app/shared/model/unskilled.model';

@Component({
    selector: 'jhi-unskilled-detail',
    templateUrl: './unskilled-detail.component.html'
})
export class UnskilledDetailComponent implements OnInit {
    unskilled: IUnskilled;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ unskilled }) => {
            this.unskilled = unskilled;
        });
    }

    previousState() {
        window.history.back();
    }
}
