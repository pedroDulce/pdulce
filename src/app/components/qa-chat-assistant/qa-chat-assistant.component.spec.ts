import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QaChatAssistantComponent } from './qa-chat-assistant.component';

describe('QaChatAssistantComponent', () => {
  let component: QaChatAssistantComponent;
  let fixture: ComponentFixture<QaChatAssistantComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [QaChatAssistantComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(QaChatAssistantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
