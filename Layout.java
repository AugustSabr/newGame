public class Layout {
  String[][] map = {
    {" ", " ", " ", "d", "i", " ", " ", " "},
    {" ", " ", " ", " ", "e", " ", " ", " "},
    {" ", " ", " ", " ", "e", " ", " ", " "},
    {" ", "a", "h", "i", "k", "b", "a", " "},
    {" ", "g", "n", "o", "n", "f", "o", "b"},
    {"d", "f", "f", "o", "f", "f", "j", " "},
    {"d", "f", "l", "o", "l", "i", " ", " "},
    {" ", " ", "c", "c", "e", "k", "b", " "},
    {" ", " ", " ", " ", "k", "m", " ", " "},
    {" ", " ", " ", " ", "c", "c", " ", " "}
  };
  int x = 3;
  int y = 0;
  String position = map[y][x];
  
  String facing = "east";
  UI ui;

  public Layout(UI u) {
    ui = u;
    System.out.println(position);
    System.out.println(facing);
}

  public void move(String s){
    //move
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
              }
            } break;
          case "down":
            for(int i = 0; i < northDown.length; i++){
              if(position == northDown[i]){
                y++;
                facing="south";
              }
            } break;
          case "right":
            for(int i = 0; i < northRight.length; i++){
              if(position == northRight[i]){
                x++;
                facing="east";
              }
            } break;
          case "left":
            for(int i = 0; i < northLeft.length; i++){
              if(position == northLeft[i]){
                x=x-1;
                facing="west";
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
              }
            } break;
          case "right":
            for(int i = 0; i < southRight.length; i++){
              if(position == southRight[i]){
                x=x-1;
                facing="west";
              }
            } break;
          case "left":
            for(int i = 0; i < southLeft.length; i++){
              if(position == southLeft[i]){
                x++;
                facing="east";
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
              }
            } break;
          case "down":
            for(int i = 0; i < eastDown.length; i++){
              if(position == eastDown[i]){
                x=x-1;
                facing="west";
              }
            } break;
          case "right":
            for(int i = 0; i < eastRight.length; i++){
              if(position == eastRight[i]){
                y++;
                facing="south";
              }
            } break;
          case "left":
            for(int i = 0; i < eastLeft.length; i++){
              if(position == eastLeft[i]){
                y=y-1;
                facing="north";
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
              }
            } break;
          case "down":
            for(int i = 0; i < westDown.length; i++){
              if(position == westDown[i]){
                x++;
                facing="east";
              }
            } break;
          case "right":
            for(int i = 0; i < westRight.length; i++){
              if(position == westRight[i]){
                y=y-1;
                facing="north";
              }
            } break;
          case "left":
            for(int i = 0; i < westLeft.length; i++){
              if(position == westLeft[i]){
                y++;
                facing="south";
              }
            } break;
        } break;
    }

    position = map[y][x];

    //draw
    ui.random("");

    switch(position){
      case "a":
        switch(facing){
          case "north": ui.random("wall"); break;
          case "south": ui.random("i"); break;
        } break;
      case "b":
        switch(facing){
          case "east": ui.random("wall"); break;
          case "west": ui.random("i"); break;
        } break;
      case "c":
        switch(facing){
          case "north": ui.random("i"); break;
          case "south": ui.random("wall"); break;
        } break;
      case "d":
        switch(facing){
          case "east": ui.random("i"); break;
          case "west": ui.random("wall"); break;
        } break;
      case "e": ui.random("i"); break;
      case "f": ui.random("i"); break;
      case "g":
        switch(facing){
          case "south": ui.random("left"); break;
          case "west": ui.random("right"); break;
        } break;
      case "h":
        switch(facing){
          case "north": ui.random("right"); break;
          case "west": ui.random("left"); break;
        } break;
      case "i":
        switch(facing){
          case "north": ui.random("left"); break;
          case "east": ui.random("right"); break;
        } break;
      case "j":
        switch(facing){
          case "south": ui.random("right"); break;
          case "east": ui.random("left"); break;
        } break;
      case "k":
        switch(facing){
          case "north": ui.random("hm"); break;
          case "south": ui.random("klkl"); break;
          case "west": ui.random("T"); break;
        } break;
      case "l":
        switch(facing){
          case "north": ui.random("T"); break;
          case "east": ui.random("hm"); break;
          case "west": ui.random("klkl"); break;
        } break;
      case "m":
        switch(facing){
          case "north": ui.random("klkl"); break;
          case "south": ui.random("hm"); break;
          case "east": ui.random("T"); break;
        } break;
      case "n":
        switch(facing){
          case "south": ui.random("T"); break;
          case "east": ui.random("klkl"); break;
          case "west": ui.random("hm"); break;
        } break;
      case "o": ui.random("x");break;
    }
    System.out.println();
    System.out.println(s);
    System.out.println(position);
    System.out.println(facing);
  }
}
