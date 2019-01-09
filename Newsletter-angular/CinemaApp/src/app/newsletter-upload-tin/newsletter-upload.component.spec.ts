import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterUploadComponentTin } from './newsletter-upload.component';

describe('NewsletterUploadComponentTin', () => {
  let component: NewsletterUploadComponentTin;
  let fixture: ComponentFixture<NewsletterUploadComponentTin>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsletterUploadComponentTin ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsletterUploadComponentTin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
