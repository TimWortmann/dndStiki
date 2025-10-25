import { AfterViewInit, Component, EventEmitter, Input, OnInit, Output, SimpleChanges, ViewChild } from '@angular/core';
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
export class TableComponent implements AfterViewInit {

  public tableDataSource = new MatTableDataSource<any>([]);
  public displayedColumns: string[] = [];
  public filterVisibleColumnsOnly = true;
  public filterValue = '';
  paginationSizes!: number[];
  defaultPageSize!: number;

  @ViewChild(MatPaginator) matPaginator!: MatPaginator;
  @ViewChild(MatSort) matSort!: MatSort;

  @Input() isPageable = false;
  @Input() isSortable = false;
  @Input() isFilterable = false;
  @Input() tableColumns: TableColumnValue[] = [];
  @Input() rowActionIcon!: string;
  @Input() isFilterableOnlyVisible = false;

  @Output() sort: EventEmitter<Sort> = new EventEmitter();
  @Output() rowAction: EventEmitter<any> = new EventEmitter<any>();

  @Input() set tableData(data: any[]) {
    this.setTableDataSource(data);
  }

  constructor() {}

  ngOnInit(): void {
    this.updateDisplayedColumns();
  }

  ngAfterViewInit(): void {
    this.paginationSizes = this.getDynamicPaginationSizes();
    this.defaultPageSize = this.paginationSizes[0];
    this.tableDataSource.paginator = this.matPaginator;
    this.tableDataSource.sort = this.matSort;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['tableColumns'] && this.tableColumns?.length) {
      this.updateDisplayedColumns();
    }
  }

  private updateDisplayedColumns() {
     const columnNames = this.tableColumns.map(col => col.name);
    this.displayedColumns = this.rowActionIcon ? [this.rowActionIcon, ...columnNames] : columnNames;
  }

  setTableDataSource(data: any[]) {
    this.tableDataSource = new MatTableDataSource<any>(data);
    this.tableDataSource.paginator = this.matPaginator;
    this.tableDataSource.sort = this.matSort;
    this.applyFilter(this.filterValue); // preserve filter after data set
  }

  applyFilter(value: string) {
    this.filterValue = value; // keep the typed input
    const filter = value.trim().toLowerCase(); // lowercase only for comparison

    // Set the filter predicate based on checkbox
    if (this.filterVisibleColumnsOnly) {
      this.tableDataSource.filterPredicate = (data: any) => {
        return this.tableColumns.some(col => {
          const val = data[col.dataKey];
          return val != null && val.toString().toLowerCase().includes(filter);
        });
      };
    } else {
      this.tableDataSource.filterPredicate = (data: any) => {
        const rowStr = Object.values(data).map(v => v?.toString().toLowerCase()).join(' ');
        return rowStr.includes(filter);
      };
    }

    this.tableDataSource.filter = filter;

    if (this.matPaginator) {
      // Reset to first page
      this.matPaginator.firstPage();

      // Reset page size to the first entry of dynamic sizes
      const newSizes = this.getDynamicPaginationSizes();
      if (newSizes.length) {
        this.matPaginator._changePageSize(newSizes[0]);
      }
    }
  }

  onFilterToggle() {
    this.applyFilter(this.filterValue); // live update when checkbox toggled
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

    if (!sizes.includes(dataLength)) sizes.push(dataLength);

    return sizes.sort((a, b) => a - b);
  }
}
