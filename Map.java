public class Map {
  String[][] mapLayout = {
    {" ", " ", " ", "d", "i", " ", " ", " "},
    {" ", " ", " ", " ", "e", " ", " ", " "},
    {" ", " ", " ", " ", "e", " ", " ", " "},
    {" ", "a", "h", "i", "k", "b", "a", " "},
    {" ", "g", "n", "o", "n", "f", "o", "b"},
    {"d", "f", "f", "o", "f", "f", "j", " "},
    {"d", "f", "l", "o", "l", "i", " ", " "},
    {" ", " ", "c", "c", "e", "k", "b", " "},
    {" ", " ", "c", " ", "k", "m", " ", " "},
    {" ", " ", " ", " ", "c", "c", " ", " "}
  };

  public String[][] structureLayout = {
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", "p", " ", " ", " ", " ", " "},
    {" ", " ", "r", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "}
  };

  public int x;
  public int y;
  String facing, position, structure;
  int floor = 1;

  UI ui;
  Encounter en;
  _KeyListener key;

  public Map(UI u, Encounter e, int _x, int _y, String f) {
    ui = u;
    en = e;

    x = _x;
    y = _y;
    facing = f;
    en.addMap(this);
    draw();
  }
  public void addKeyListener(_KeyListener k){
    key = k;
  }

  public void move(String s){
    switch(facing){
      case "north":
        String[] northUp = {"c", "e", "k", "m", "o"};
        String[] northDown = {"a", "e", "h", "i", "k", "l", "m", "o"};
        String[] northRight = {"h", "k", "l", "o"};
        String[] northLeft = {"i", "l", "m", "o"};
        switch(s){
          case "up":
            for(int i = 0; i < northUp.length; i++){
              if(position == northUp[i]){
                y=y-1;

                en.newEncounter();
              }
            } break;
          case "down":
            for(int i = 0; i < northDown.length; i++){
              if(position == northDown[i]){
                y++;
                facing="south";

                en.newEncounter();
              }
            } break;
          case "right":
            for(int i = 0; i < northRight.length; i++){
              if(position == northRight[i]){
                x++;
                facing="east";

                en.newEncounter();
              }
            } break;
          case "left":
            for(int i = 0; i < northLeft.length; i++){
              if(position == northLeft[i]){
                x=x-1;
                facing="west";

                en.newEncounter();
              }
            } break;
        } break;

      case "south":
        String[] southUp = {"a", "e", "k", "m", "o"};
        String[] southDown = {"c", "e", "g", "j", "k", "m", "n", "o"};
        String[] southRight = {"j", "m", "n", "o"};
        String[] southLeft = {"g", "k", "n", "o"};
        switch(s){
          case "up":
            for(int i = 0; i < southUp.length; i++){
              if(position == southUp[i]){
                y++;
              }
            } break;
          case "down":
            for(int i = 0; i < southDown.length; i++){
              if(position == southDown[i]){
                y=y-1;
                facing="north";

                en.newEncounter();
              }
            } break;
          case "right":
            for(int i = 0; i < southRight.length; i++){
              if(position == southRight[i]){
                x=x-1;
                facing="west";

                en.newEncounter();
              }
            } break;
          case "left":
            for(int i = 0; i < southLeft.length; i++){
              if(position == southLeft[i]){
                x++;
                facing="east";

                en.newEncounter();
              }
            } break;
        } break;

      case "east":
        String[] eastUp = {"d", "f", "l", "n", "o"};
        String[] eastDown = {"b", "f", "i", "j", "l", "m", "n", "o"};
        String[] eastRight = {"i", "l", "m", "o"};
        String[] eastLeft = {"j", "m", "n", "o"};
        switch(s){
          case "up":
            for(int i = 0; i < eastUp.length; i++){
              if(position == eastUp[i]){
                x++;

                en.newEncounter();
              }
            } break;
          case "down":
            for(int i = 0; i < eastDown.length; i++){
              if(position == eastDown[i]){
                x=x-1;
                facing="west";

                en.newEncounter();
              }
            } break;
          case "right":
            for(int i = 0; i < eastRight.length; i++){
              if(position == eastRight[i]){
                y++;
                facing="south";

                en.newEncounter();
              }
            } break;
          case "left":
            for(int i = 0; i < eastLeft.length; i++){
              if(position == eastLeft[i]){
                y=y-1;
                facing="north";

                en.newEncounter();
              }
            } break;
        } break;

      case "west":
        String[] westUp = {"b", "f", "l", "n", "o"};
        String[] westDown = {"d", "f", "g", "h", "k", "l", "n", "o"};
        String[] westRight = {"g", "k", "n", "o"};
        String[] westLeft = {"h", "k", "l", "o"};
        switch(s){
          case "up":
            for(int i = 0; i < westUp.length; i++){
              if(position == westUp[i]){
                x=x-1;

                en.newEncounter();
              }
            } break;
          case "down":
            for(int i = 0; i < westDown.length; i++){
              if(position == westDown[i]){
                x++;
                facing="east";

                en.newEncounter();
              }
            } break;
          case "right":
            for(int i = 0; i < westRight.length; i++){
              if(position == westRight[i]){
                y=y-1;
                facing="north";

                en.newEncounter();
              }
            } break;
          case "left":
            for(int i = 0; i < westLeft.length; i++){
              if(position == westLeft[i]){
                y++;
                facing="south";

                en.newEncounter();
              }
            } break;
        } break;
    }
    draw();
  }

  public void draw(){
    position = mapLayout[y][x];
    structure = structureLayout[y][x];
    switch(position){
      case "a":
        switch(facing){
          case "north": ui.drawRoom("wall"); break;
          case "south": ui.drawRoom("front"); break;
        } break;
      case "b":
        switch(facing){
          case "east": ui.drawRoom("wall"); break;
          case "west": ui.drawRoom("front"); break;
        } break;
      case "c":
        switch(facing){
          case "north": ui.drawRoom("front"); break;
          case "south": ui.drawRoom("wall"); break;
        } break;
      case "d":
        switch(facing){
          case "east": ui.drawRoom("front"); break;
          case "west": ui.drawRoom("wall"); break;
        } break;
      case "e": ui.drawRoom("front"); break;
      case "f": ui.drawRoom("front"); break;
      case "g":
        switch(facing){
          case "south": ui.drawRoom("left"); break;
          case "west": ui.drawRoom("right"); break;
        } break;
      case "h":
        switch(facing){
          case "north": ui.drawRoom("right"); break;
          case "west": ui.drawRoom("left"); break;
        } break;
      case "i":
        switch(facing){
          case "north": ui.drawRoom("left"); break;
          case "east": ui.drawRoom("right"); break;
        } break;
      case "j":
        switch(facing){
          case "south": ui.drawRoom("right"); break;
          case "east": ui.drawRoom("left"); break;
        } break;
      case "k":
        switch(facing){
          case "north": ui.drawRoom("frontRight"); break;
          case "south": ui.drawRoom("frontLeft"); break;
          case "west": ui.drawRoom("t-intersection"); break;
        } break;
      case "l":
        switch(facing){
          case "north": ui.drawRoom("t-intersection"); break;
          case "east": ui.drawRoom("frontRight"); break;
          case "west": ui.drawRoom("frontLeft"); break;
        } break;
      case "m":
        switch(facing){
          case "north": ui.drawRoom("frontLeft"); break;
          case "south": ui.drawRoom("frontRight"); break;
          case "east": ui.drawRoom("t-intersection"); break;
        } break;
      case "n":
        switch(facing){
          case "south": ui.drawRoom("t-intersection"); break;
          case "east": ui.drawRoom("frontLeft"); break;
          case "west": ui.drawRoom("frontRight"); break;
        } break;
      case "o": ui.drawRoom("crossroad");break;
    }
    ui.drawStructure("");
    switch(structure){
      case "p": ui.drawStructure("door"); key.z = "check door"; break;
      case "q": ui.drawStructure("openDoor"); key.z = "go thru door"; break;
      case "r": ui.drawStructure("shop"); break;
    }
    // ui.drawMonster("me");
    System.out.println();
    System.out.println(structure);
    System.out.println(position);
    System.out.println(facing);
  }
}
