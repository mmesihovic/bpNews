import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterDownloadComponent } from './newsletter-download.component';

describe('NewsletterDownloadComponent', () => {
  let component: NewsletterDownloadComponent;
  let fixture: ComponentFixture<NewsletterDownloadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewsletterDownloadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewsletterDownloadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
