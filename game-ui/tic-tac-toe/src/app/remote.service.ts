import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

import { IState, State } from './board/board.component';

@Injectable()
export class RemoteService {

  private GAME_ENGINE_URL = 'http://localhost:8080/tic-tac-toe/next-state';

  constructor (private http: Http) {}

  getNextState(state: State): Observable<IState> {
      let headers = new Headers({'Content-Type': 'application/json'});
      let options = new RequestOptions({headers});
      return this.http.post(this.GAME_ENGINE_URL, state, options)
            .map(this.parseData)
            .catch(this.handleError);
  }

  private parseData(res: Response)  {
      return res.json() || [];
  }

  private handleError(error: Response | any) {
      return error.message ? error.message : error.toString();
  }

}
