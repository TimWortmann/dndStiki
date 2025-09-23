import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';  // ✅ fixed import order
import { TableColumnValue } from '../../models/TableColumnValue';

@Component({
  selector: 'custom-table',
  standalone: false,
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent {

  public tableDataSource = new MatTableDataSource<any>([]);
  public displayedColumns: string[] = [];

  @ViewChild(MatPaginator) matPaginator!: MatPaginator;
  @ViewChild(MatSort) matSort!: MatSort;

  @Input() isPageable = false;
  @Input() isSortable = false;
  @Input() isFilterable = false;
  @Input() tableColumns: TableColumnValue[] = [];
  @Input() rowActionIcon!: string;
  @Input() paginationSizes: number[] = [5, 10, 15];
  @Input() defaultPageSize = this.paginationSizes[1];

  @Output() sort: EventEmitter<Sort> = new EventEmitter();
  @Output() rowAction: EventEmitter<any> = new EventEmitter<any>();

  // dynamically get data from parent
  @Input() set tableData(data: any[]) {
    this.setTableDataSource(data);
  }

  constructor() {}

  ngOnInit(): void {
    const columnNames = this.tableColumns.map((col) => col.name);
    this.displayedColumns = this.rowActionIcon ? [this.rowActionIcon, ...columnNames] : columnNames;
  }

  ngAfterViewInit(): void {
    this.tableDataSource.paginator = this.matPaginator;
    this.tableDataSource.sort = this.matSort;
  }

  setTableDataSource(data: any[]) {
    this.tableDataSource = new MatTableDataSource<any>(data);
    this.tableDataSource.paginator = this.matPaginator;
    this.tableDataSource.sort = this.matSort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.tableDataSource.filter = filterValue.trim().toLowerCase();
  }

  sortTable(sortParameters: Sort) {
    const column = this.tableColumns.find(c => c.name === sortParameters.active);
    if (column) {
      sortParameters.active = column.dataKey;
      this.sort.emit(sortParameters);
    }
  }

  emitRowAction(row: any) {
    this.rowAction.emit(row);
  }
}
