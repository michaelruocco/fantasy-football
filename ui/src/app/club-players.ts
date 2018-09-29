import { Player } from './player';

export class ClubPlayers {

  meta: ClubPlayersMeta;
  data: Player[];

}

class ClubPlayersMeta {

  totalPages: number;
  totalPlayers: number;
  pageNumber: number;
  pageSize: number;
  clubId: number;

}

class ClubPlayersLinks {

  self: string;
  first: string;
  last: string;
  next: string;
  previous: string;

}

