/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HopeJustFoundTestModule } from '../../../test.module';
import { SkilledDetailComponent } from 'app/entities/skilled/skilled-detail.component';
import { Skilled } from 'app/shared/model/skilled.model';

describe('Component Tests', () => {
    describe('Skilled Management Detail Component', () => {
        let comp: SkilledDetailComponent;
        let fixture: ComponentFixture<SkilledDetailComponent>;
        const route = ({ data: of({ skilled: new Skilled(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [SkilledDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SkilledDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SkilledDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.skilled).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
