import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {

  size = 9;

  private grid: Array<any>;

  constructor() {
    this.grid = new Array<any>(this.size);
  }

  placeMark(mark: string, position: number) {
    if (this.isValid(position)) {
      this.grid[position] = mark;
    }
  }

  returnMark(position: number) {
    return this.grid[position];
  }

  isValid(position: number): boolean {
    return (this.isValidRange(position) && this.isPositionEmpty(position));
  }

  private isValidRange(position: number): boolean {
    return (position >= 0 && position <= 8);
  }

  private isPositionEmpty(position: number): boolean {
    return (this.grid[position] === undefined);
  }

}
