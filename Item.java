public class Item {
  private String name, path;
  private int tier, value, index;

  public Item(String n, String p, int v, int t, int i){
    name = n;
    path = p;
    value = v;
    tier = t;
    index = i;
  }

  public String getName() {
    return name;
  }
  public String getPath() {
    return path;
  }
  public int getValue() {
    return value;
  }
  public int getTier() {
    return tier;
  }
  public int getIndex() {
    return index;
  }
}
