import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';  // ✅ fixed import order
import { TableColumnValue } from '../../models/table-column-value';

@Component({
  selector: 'custom-table',
  standalone: false,
  templateUrl: './table.component.html',
  styleUrl: './table.component.scss'
})
export class TableComponent {

  public tableDataSource = new MatTableDataSource<any>([]);
  public displayedColumns: string[] = [];
  paginationSizes!: number[];
  defaultPageSize!: number;

  @ViewChild(MatPaginator) matPaginator!: MatPaginator;
  @ViewChild(MatSort) matSort!: MatSort;

  @Input() isPageable = false;
  @Input() isSortable = false;
  @Input() isFilterable = false;
  @Input() tableColumns: TableColumnValue[] = [];
  @Input() rowActionIcon!: string;

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
    this.paginationSizes = this.getDynamicPaginationSizes();
    this.defaultPageSize = this.paginationSizes[0];
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

    // Reset to first page
    if (this.matPaginator) {
      this.matPaginator.pageIndex = 0;

      // Update page size options based on filtered data
      const newSizes = this.getDynamicPaginationSizes();
      if (newSizes.length) {
        this.matPaginator._changePageSize(newSizes[0]);
      }
    }
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

  getDynamicPaginationSizes(): number[] {
    const dataLength = this.tableDataSource.filteredData.length; // use filteredData
    const sizes: number[] = [];

    if (dataLength > 5) sizes.push(5);
    if (dataLength > 10) sizes.push(10);
    if (dataLength > 20) sizes.push(20);

    // Always include full length if it's not already included
    if (!sizes.includes(dataLength)) sizes.push(dataLength);

    return sizes.sort((a, b) => a - b);
  }
}
