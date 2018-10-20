import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISkilled } from 'app/shared/model/skilled.model';

@Component({
    selector: 'jhi-skilled-detail',
    templateUrl: './skilled-detail.component.html'
})
export class SkilledDetailComponent implements OnInit {
    skilled: ISkilled;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ skilled }) => {
            this.skilled = skilled;
        });
    }

    previousState() {
        window.history.back();
    }
}
