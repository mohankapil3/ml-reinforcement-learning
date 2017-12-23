import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {

  size = 9;

  private grid: Array<string> = ["", "", "", "", "", "", "", "", ""];

  placeMark(mark: string, position: number) {
    if (this.isValid(position)) {
      this.grid[position] = mark;
    }
  }

  returnMark(position: number) {
    return this.grid[position];
  }

  getCurrentState() {
    return new State(this.grid);
  }

  updateState(state: IState) {
    this.grid = state.grid;
  }

  isValid(position: number): boolean {
    return (this.isValidRange(position) && this.isPositionEmpty(position));
  }

  private isValidRange(position: number): boolean {
    return (position >= 0 && position <= 8);
  }

  private isPositionEmpty(position: number): boolean {
    return (this.grid[position] === undefined || this.grid[position] == "");
  }

}

export interface IState {

  grid: Array<string>;

}

export class State implements IState {

  grid: Array<string>;

  constructor(grid: Array<string>) {
    this.grid = grid;
  }

}

