import javax.sound.sampled.SourceDataLine;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;


public class Map {

  ArrayList<ArrayList<String>> mapLayout1 = new ArrayList<ArrayList<String>>();

  public String[][] structureLayout = {
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", "s", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "},
    {" ", " ", "p", " ", " ", " ", " ", " "},
    {" ", " ", "r", " ", " ", " ", " ", " "},
    {" ", " ", " ", " ", " ", " ", " ", " "}
  };


  public int x, y, floor;
  private String facing, position, structure;

  private UI ui;
  private Encounter en;
  private _KeyListener key;
  private VisibilityManager vm;

  public Map(UI u, Encounter e, VisibilityManager v, GameInventory in, int _x, int _y, String f) {

    ui = u;
    en = e;
    vm = v;

    x = _x;
    y = _y;
    facing = f;
    floor = 1;
    in.addMap(this);
    e.addMap(this);
    ui.addMap(this);

    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", "d", "i", " ", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", "e", " ", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", "e", " ", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", "a", "h", "i", "k", "b", "a", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", "g", "n", "o", "n", "f", "o", "b", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList("d", "f", "f", "o", "f", "f", "j", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList("d", "f", "l", "o", "l", "i", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", "c", "c", "e", "k", "b", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", "c", " ", "k", "m", " ", " ", " ", " ")));
    mapLayout1.add(new ArrayList<String>(Arrays.asList(" ", " ", " ", " ", "c", "c", " ", " ", " ", " ")));
    maps.add(mapLayout1);
    draw();
  }
  public void addKeyListener(_KeyListener k){
    key = k;
  }

  public ArrayList<ArrayList<ArrayList<String>>> maps = new ArrayList<ArrayList<ArrayList<String>>>();
  

  public void createFloor(){
    ArrayList<ArrayList<String>> mapLayout = new ArrayList<ArrayList<String>>();
    for (int i = 0; i < 10; i++) {
      ArrayList<String> x = new ArrayList<String>();
      for (int j = 0; j < 10; j++) {
        ArrayList<String> roomPossibilities = new ArrayList<String>(Arrays.asList(" ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o"));
        String[] upCheck = {"a", "e", "h", "i", "k", "l", "m", "o"};
        boolean up=false;
        for (int k = 0; k < upCheck.length; k++) {
          try {
            if(mapLayout.get(i-1).get(j) == upCheck[k]){
              up=true;
              break;
            }
          } catch (Exception e) {}
        }
        if(up){
          roomPossibilities.remove(" ");
          roomPossibilities.remove("a");
          roomPossibilities.remove("b");
          roomPossibilities.remove("d");
          roomPossibilities.remove("f");
          roomPossibilities.remove("h");
          roomPossibilities.remove("i");
          roomPossibilities.remove("l");
        } else {
          roomPossibilities.remove("c");
          roomPossibilities.remove("e");
          roomPossibilities.remove("g");
          roomPossibilities.remove("j");
          roomPossibilities.remove("k");
          roomPossibilities.remove("m");
          roomPossibilities.remove("n");
          roomPossibilities.remove("o");
        }
        String[] leftCheck = {"d", "f", "g", "h", "k", "l", "n", "o"};
        boolean left=false;
        for (int k = 0; k < leftCheck.length; k++) {
          try {
            if(x.get(j-1) == leftCheck[k]){
              left=true;
              break;
            }
          } catch (Exception e) {}
        }
        if(left){
          roomPossibilities.remove(" ");
          roomPossibilities.remove("a");
          roomPossibilities.remove("c");
          roomPossibilities.remove("d");
          roomPossibilities.remove("e");
          roomPossibilities.remove("g");
          roomPossibilities.remove("h");
          roomPossibilities.remove("k");
        } else {
          roomPossibilities.remove("b");
          roomPossibilities.remove("f");
          roomPossibilities.remove("i");
          roomPossibilities.remove("j");
          roomPossibilities.remove("l");
          roomPossibilities.remove("m");
          roomPossibilities.remove("n");
          roomPossibilities.remove("o");
        }
        if(i==9){
          roomPossibilities.remove("a");
          roomPossibilities.remove("e");
          roomPossibilities.remove("h");
          roomPossibilities.remove("i");
          roomPossibilities.remove("k");
          roomPossibilities.remove("l");
          roomPossibilities.remove("m");
          roomPossibilities.remove("o");
        }
        if(j==9){
          roomPossibilities.remove("d");
          roomPossibilities.remove("f");
          roomPossibilities.remove("g");
          roomPossibilities.remove("h");
          roomPossibilities.remove("k");
          roomPossibilities.remove("l");
          roomPossibilities.remove("n");
          roomPossibilities.remove("o");
        }
        x.add(roomPossibilities.get(new Random().nextInt(roomPossibilities.size())));
      }
      mapLayout.add(x);
    }
    maps.add(mapLayout);
  }

  public void move(String s){
    en.newMonster = null;
    key.z = "";
    key.x = "";
    key.c = "openInventory";
    switch(s){
      case "up":
        if(ui.roomImgLabel.getIcon().toString().equals("img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/front.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontRight.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontLeft.png")){
          switch(facing){
            case "north": y=y-1; structure = structureLayout[y][x]; draw(); break;
            case "south": y++; structure = structureLayout[y][x]; draw(); break;
            case "east": x++; structure = structureLayout[y][x]; draw(); break;
            case "west": x=x-1; structure = structureLayout[y][x]; draw(); break;
          }
        }break;
      case "down":
      if(position == "a" && facing == "south" || position == "b" && facing == "west" || position == "c" && facing == "north" || position == "d" && facing == "east"){}else{
        switch(facing){
          case "north": y++; facing="south"; structure = structureLayout[y][x]; draw(); break;
          case "south": y=y-1; facing="north"; structure = structureLayout[y][x]; draw(); break;
          case "east": x=x-1; facing="west"; structure = structureLayout[y][x]; draw(); break;
          case "west": x++; facing="east"; structure = structureLayout[y][x]; draw(); break;
        }
      } break;
      case "right":
      if(ui.roomImgLabel.getIcon().toString().equals("img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontRight.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/right.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/t-intersection.png")){
        switch(facing){
          case "north": x++; facing="east"; structure = structureLayout[y][x]; draw(); break;
          case "south": x=x-1; facing="west"; structure = structureLayout[y][x]; draw(); break;
          case "east": y++; facing="south"; structure = structureLayout[y][x]; draw(); break;
          case "west": y=y-1; facing="north"; structure = structureLayout[y][x]; draw(); break;
        }
      }break;
      case "left":
      if(ui.roomImgLabel.getIcon().toString().equals("img/rooms/crossroad.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/frontLeft.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/left.png") || ui.roomImgLabel.getIcon().toString().equals("img/rooms/t-intersection.png")){
        switch(facing){
          case "north": x=x-1; facing="west"; structure = structureLayout[y][x]; draw(); break;
          case "south": x++; facing="east"; structure = structureLayout[y][x]; draw(); break;
          case "east": y=y-1; facing="north"; structure = structureLayout[y][x]; draw(); break;
          case "west": y++; facing="south"; structure = structureLayout[y][x]; draw(); break;
        }
      }break;
    }
  }

  public void draw(){
    ui.mainTextArea.setText("");
    structure = structureLayout[y][x];
    position = maps.get(floor-1).get(y).get(x);
    // System.out.println("position: "+position);
    ui.drawplayer();
    ui.drawRoom("blackScreen");
    ui.drawStructure("");
    ui.drawEncounter("","");

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
        if(structure == " "){
          en.newEncounter();
        }
        switch(structure){
          case "p": ui.drawStructure("door"); key.z = "checkDoor"; break;
          case "q": if(position == "a" && facing == "south" || position == "b" && facing == "west" || position == "c" && facing == "north" || position == "d" && facing == "east"){}else{ui.drawStructure("openDoor"); key.z = "goThruDoor";} break;
          case "r": ui.drawStructure("shop"); key.z = "talkToShopkeeper"; break;
          case "s": ui.drawStructure("hole"); en.hole(); break;
        }
      }
    }); timer.setRepeats(false);
    timer.start();    
  }

  public String getFacing() {
    return facing;
  }
}
