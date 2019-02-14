package MainPackage;

/**
 * Idea la classe qui represente l'idée qu'un noeud peut adopter
 * (travail à faire)
 */
public class Idea {

  private String name;

  private float value;

  public Idea(String name)
{
  this.name=name;	
}

  public String getName() {
    return name;
  }

  public float getValue() {
    return value;
  }

  public void setValue(float value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Idea{" +
            name  +
            " ->" + value +
            '}';
  }
}
