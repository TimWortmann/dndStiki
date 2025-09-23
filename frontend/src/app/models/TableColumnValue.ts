import { TemplateRef } from "@angular/core";

export interface TableColumnValue {
  name: string; // column name
  dataKey: string; // name of key of the actual data in this column
  position?: 'right' | 'left'; // should it be right-aligned or left-aligned?
  isSortable?: boolean; // can a column be sorted?
  template?: TemplateRef<any>; // optional template
}