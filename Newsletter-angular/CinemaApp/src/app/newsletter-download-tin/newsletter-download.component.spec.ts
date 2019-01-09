import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterDownloadComponentTin } from './newsletter-download.component';

describe('NewsletterDownloadComponentTin', () => {
  let component: NewsletterDownloadComponentTin;
  let fixture: ComponentFixture<NewsletterDownloadComponentTin>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsletterDownloadComponentTin ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsletterDownloadComponentTin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
