import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterUploadComponentDelila } from './newsletter-upload.component';

describe('NewsletterUploadComponentDelila', () => {
  let component: NewsletterUploadComponentDelila;
  let fixture: ComponentFixture<NewsletterUploadComponentDelila>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsletterUploadComponentDelila ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsletterUploadComponentDelila);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
