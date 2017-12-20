import { BoardComponent } from './board/board.component';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {

  title = 'Tic Tac Toe';
  board = new BoardComponent();
  boardDimension = Math.round(Math.sqrt(this.board.size));
  rows = Array.from(Array(this.boardDimension).keys());
  columns = Array.from(Array(this.boardDimension).keys());

  boardClicked(position: number): void {
    this.board.placeMark('O', position)
  }

}
