export class Player {

  id: number;
  attributes: Attributes;
  relationships: PlayerRelationships;

}

class Attributes {

  firstName: string;
  lastName: string;
  position: string;
  value: number;

}

class PlayerRelationships {

  club: PlayerClub;

}

class PlayerClub {

  links: PlayerClubLinks;

}

class PlayerClubLinks {

  related: string;

}
