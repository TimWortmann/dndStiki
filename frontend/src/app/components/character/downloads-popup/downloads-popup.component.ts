import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { CharacterValue } from '../../../models/character-value';
import { PdfService } from '../../../services/pdf/pdf.service';

@Component({
  selector: 'app-downloads-popup',
  standalone: false,
  templateUrl: './downloads-popup.component.html',
  styleUrl: './downloads-popup.component.scss'
})
export class DownloadsPopupComponent {

  characterValue!: CharacterValue;

  constructor(
    private dialogRef: MatDialogRef<DownloadsPopupComponent>,
    private pdfService: PdfService,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ){
    this.characterValue = data;
  }

  close(): void {
    this.dialogRef.close();
  }

  downloadCharacterSheet() {
    this.pdfService.downloadCharacterSheet(this.characterValue.id).subscribe((response) => {

      this.openPdf(response, 'Character Sheet (' + this.characterValue.name + ') (Level ' + this.characterValue.level + ')')
    });
  }

  downloadFeatureSheet(filterLevelFeatures : boolean) {
    this.pdfService.downloadFeatureSheet(this.characterValue.id, filterLevelFeatures).subscribe((response) => {

      let fileName = 'Feature Sheet (' + this.characterValue.name + ')';

      if (filterLevelFeatures) {
        fileName += ' (Level ' + this.characterValue.level + ')';
      } else {
        fileName += ' (Level 20)'
      }

      this.openPdf(response, fileName);
    });
  }

  openPdf(file : Blob, fileName : string) {
    const url = window.URL.createObjectURL(file);

      const a = document.createElement('a');
      a.href = url;
      a.download = fileName + '.pdf'; 
      a.click();

      window.URL.revokeObjectURL(url);
  }

  downloadSpellcastingSheet() {
    this.pdfService.downloadSpellcastingSheet(this.characterValue.id).subscribe((response) => {

      this.openPdf(response, 'Spellcasting Sheet (' + this.characterValue.name + ') (Level ' + this.characterValue.level + ')')
    });
  }

  downloadSpellInfoSheet() {
    this.pdfService.downloadSpellInfoSheet(this.characterValue.id).subscribe((response) => {

      this.openPdf(response, 'Spell Info Sheet (' + this.characterValue.name + ') (Level ' + this.characterValue.level + ')')
    });
  }

}
