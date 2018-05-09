import { Title } from '@angular/platform-browser';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-board',
  templateUrl: './board.component.html',
  styleUrls: ['./board.component.css']
})
export class BoardComponent {

  size = 9;

  private unknownPlayer = "_";

  private grid: Array<string> = this.getNewGrid();

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

  isOver(): boolean {
    return (this.isWon() || this.isDraw());
  }

  isDraw(): boolean {
    return ((!this.isWon()) && (this.isBoardFull()));
  }

  isWon(): boolean {
    return (this.checkRows() || this.checkColumns() || this.checkDiagonals());
  }

  whoHasWon(): string {
     let winningPlayer = this.getWinningPlayerByRow();
     if (winningPlayer != this.unknownPlayer) {
       return winningPlayer;
     }
     winningPlayer = this.getWinningPlayerByColumn();
     if (winningPlayer != this.unknownPlayer) {
       return winningPlayer;
     }
     winningPlayer = this.getWinningPlayerByDiagonal();
     if (winningPlayer != this.unknownPlayer) {
       return winningPlayer;
     }

     return winningPlayer;
  }

  reset() {
    this.grid = this.getNewGrid();
  }

  private getNewGrid(): Array<string> {
    return ["", "", "", "", "", "", "", "", ""];
  }

  private isBoardFull() {
    let result = true;
    for (let i = 0; i < 9; i++) {
       result = result && !this.isPositionEmpty(i)
       if (!result) {
         return false;
       }
    }
    return result;
  }

  private checkRows(): boolean {
    for (let i = 0; i < 7; i = i + 3) {
      if (!this.isPositionEmpty(i) && this.returnMark(i) === this.returnMark(i + 1) && this.returnMark(i + 1) === this.returnMark(i + 2)) {
        return true;
      }
    }
    return false;
  }

  private checkColumns(): boolean {
    for (let i = 0; i < 3; i++) {
      if (!this.isPositionEmpty(i) && this.returnMark(i) === this.returnMark(i + 3) && this.returnMark(i + 3) === this.returnMark(i + 6)) {
        return true;
      }
    }
    return false;
  }

  private checkDiagonals(): boolean {
    if (!this.isPositionEmpty(0) && this.returnMark(0) === this.returnMark(4) && this.returnMark(4) === this.returnMark(8)) {
       return true;
    }
    if (!this.isPositionEmpty(2) && this.returnMark(2) === this.returnMark(4) && this.returnMark(4) === this.returnMark(6)) {
       return true;
    }

    return false;
  }

  private isValidRange(position: number): boolean {
    return (position >= 0 && position <= 8);
  }

  isPositionEmpty(position: number): boolean {
    return (this.grid[position] === undefined || this.grid[position] == "");
  }

  private getWinningPlayerByRow(): string {
    for (let i = 0; i < 7; i = i + 3) {
      if (!this.isPositionEmpty(i) && this.returnMark(i) === this.returnMark(i + 1) && this.returnMark(i + 1) === this.returnMark(i + 2)) {
        return this.returnMark(i);
      }
    }
    return this.unknownPlayer;
  }

  private getWinningPlayerByColumn(): string {
    for (let i = 0; i < 3; i++) {
      if (!this.isPositionEmpty(i) && this.returnMark(i) === this.returnMark(i + 3) && this.returnMark(i + 3) === this.returnMark(i + 6)) {
        return this.returnMark(i);
      }
    }
    return this.unknownPlayer;
  }

  private getWinningPlayerByDiagonal(): string {
    if (!this.isPositionEmpty(0) && this.returnMark(0) === this.returnMark(4) && this.returnMark(4) === this.returnMark(8)) {
       return this.returnMark(0);
    }
    if (!this.isPositionEmpty(2) && this.returnMark(2) === this.returnMark(4) && this.returnMark(4) === this.returnMark(6)) {
       return this.returnMark(2);
    }

    return this.unknownPlayer;
  }

}

export interface IState {

  grid: Array<string>;

}

export interface IStates {

  something: Array<string>;

}

export class State implements IState {

  grid: Array<string>;

  constructor(grid: Array<string>) {
    this.grid = grid;
  }

}

