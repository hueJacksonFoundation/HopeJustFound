/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HopeJustFoundTestModule } from '../../../test.module';
import { UnskilledDetailComponent } from 'app/entities/unskilled/unskilled-detail.component';
import { Unskilled } from 'app/shared/model/unskilled.model';

describe('Component Tests', () => {
    describe('Unskilled Management Detail Component', () => {
        let comp: UnskilledDetailComponent;
        let fixture: ComponentFixture<UnskilledDetailComponent>;
        const route = ({ data: of({ unskilled: new Unskilled(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [UnskilledDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UnskilledDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UnskilledDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.unskilled).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
