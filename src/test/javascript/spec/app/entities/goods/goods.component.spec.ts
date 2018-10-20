/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { HopeJustFoundTestModule } from '../../../test.module';
import { GoodsComponent } from 'app/entities/goods/goods.component';
import { GoodsService } from 'app/entities/goods/goods.service';
import { Goods } from 'app/shared/model/goods.model';

describe('Component Tests', () => {
    describe('Goods Management Component', () => {
        let comp: GoodsComponent;
        let fixture: ComponentFixture<GoodsComponent>;
        let service: GoodsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [GoodsComponent],
                providers: []
            })
                .overrideTemplate(GoodsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(GoodsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GoodsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Goods(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.goods[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
