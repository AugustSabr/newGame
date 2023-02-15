import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Map {
  private String[][] mapLayout = {
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

  public int x, y;
  private String facing, position, structure;
  private int floor = 1;

  private UI ui;
  private Encounter en;
  private _KeyListener key;
  private VisibilityManager vm;

  public Map(UI u, Encounter e, VisibilityManager v,int _x, int _y, String f) {
    ui = u;
    en = e;
    vm = v;

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
    key.z = "";
    key.x = "";
    ui.mainTextArea.setText("");
    switch(s){
      case "up":
        if(ui.roomImgLabel.getIcon().toString().equals("img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/front.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontRight.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontLeft.png")){
          switch(facing){
            case "north": y=y-1; structure = structureLayout[y][x];
              if(structure == " "){
                en.newEncounter();
              } draw(); break;
            case "south": y++; structure = structureLayout[y][x];
              if(structure == " "){
                en.newEncounter();
              } draw(); break;
            case "east": x++; structure = structureLayout[y][x];
              if(structure == " "){
                en.newEncounter();
              } draw(); break;
            case "west": x=x-1; structure = structureLayout[y][x];
              if(structure == " "){
                en.newEncounter();
              } draw(); break;
          }
        }break;
      case "down":
      if(position == "a" && facing == "south" || position == "b" && facing == "west" || position == "c" && facing == "north" || position == "d" && facing == "east"){}else{
        switch(facing){
          case "north": y++; facing="south"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "south": y=y-1; facing="north"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "east": x=x-1; facing="west"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "west": x++; facing="east"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
        }
      } break;
      case "right":
      if(ui.roomImgLabel.getIcon().toString().equals("img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontRight.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/right.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/t-intersection.png")){
        switch(facing){
          case "north": x++; facing="east"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "south": x=x-1; facing="west"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "east": y++; facing="south"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "west": y=y-1; facing="north"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
        }
      }break;
      case "left":
      if(ui.roomImgLabel.getIcon().toString().equals("img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontLeft.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/left.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/t-intersection.png")){
        switch(facing){
          case "north": x=x-1; facing="west"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "south": x++; facing="east"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "east": y=y-1; facing="north"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
          case "west": y++; facing="south"; structure = structureLayout[y][x];
            if(structure == " "){
              en.newEncounter();
            } draw(); break;
        }
      }break;
    }
  }

  public void draw(){
    structure = structureLayout[y][x];
    position = mapLayout[y][x];
    ui.drawRoom("blackScreen");
    ui.drawStructure("");

    Timer timer = new Timer(100, (ActionListener) new ActionListener() {
      public void actionPerformed(ActionEvent e){
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
        if(structure != " "){
          en.noEncounter();
        }
        switch(structure){
          case "p": ui.drawStructure("door"); key.z = "checkDoor"; break;
          case "q": if(position == "a" && facing == "south" || position == "b" && facing == "west" || position == "c" && facing == "north" || position == "d" && facing == "east"){}else{ui.drawStructure("openDoor"); key.z = "goThruDoor";} break;
          case "r": ui.drawStructure("shop"); key.z = "talkToShopkeeper";break;
        }
      }
    }); timer.setRepeats(false);
    timer.start();    
    // System.out.println(ui.roomImgLabel.getIcon());
    // System.out.println();
    // System.out.println(x+" "+y);
    // System.out.println(structure);
    // System.out.println(position);
    // System.out.println(facing);
  }

  public String getFacing() {
    return facing;
  }
}
