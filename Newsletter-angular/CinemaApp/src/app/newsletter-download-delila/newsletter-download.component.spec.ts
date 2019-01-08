import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterDownloadComponentDelila } from './newsletter-download.component';

describe('NewsletterDownloadComponentDelila', () => {
  let component: NewsletterDownloadComponentDelila;
  let fixture: ComponentFixture<NewsletterDownloadComponentDelila>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsletterDownloadComponentDelila ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsletterDownloadComponentDelila);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
