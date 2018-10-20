/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { HopeJustFoundTestModule } from '../../../test.module';
import { UnskilledUpdateComponent } from 'app/entities/unskilled/unskilled-update.component';
import { UnskilledService } from 'app/entities/unskilled/unskilled.service';
import { Unskilled } from 'app/shared/model/unskilled.model';

describe('Component Tests', () => {
    describe('Unskilled Management Update Component', () => {
        let comp: UnskilledUpdateComponent;
        let fixture: ComponentFixture<UnskilledUpdateComponent>;
        let service: UnskilledService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HopeJustFoundTestModule],
                declarations: [UnskilledUpdateComponent]
            })
                .overrideTemplate(UnskilledUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UnskilledUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UnskilledService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Unskilled(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.unskilled = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Unskilled();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.unskilled = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
