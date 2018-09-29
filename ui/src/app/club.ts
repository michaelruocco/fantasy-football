export class Club {

  id: number;
  attributes: Attributes;
  relationships: ClubRelationships;

}

class Attributes {

  name: string;

}

class ClubRelationships {

  players: ClubPlayers;

}

class ClubPlayers {

  links: ClubPlayerLinks;

}

class ClubPlayerLinks {

  related: string;

}
