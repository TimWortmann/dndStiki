import { Component, ElementRef, EventEmitter, Input, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-single-file-upload',
  standalone: false,
  templateUrl: './single-file-upload.component.html',
  styleUrl: './single-file-upload.component.scss'
})
export class SingleFileUploadComponent {
  @Input() isDisabled: boolean | undefined;
  @Input() buttonText: string | undefined;
  @Output() fileUpload = new EventEmitter<File>();
  @ViewChild('fileUpload') fileUploadInput!: ElementRef;

  fileSelected : boolean = false;

  triggerFileUpload() {
    this.fileUploadInput.nativeElement.click();
  }

  onChange(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      this.fileSelected = true;
      this.fileUpload.emit(file);
    }
  }

}
