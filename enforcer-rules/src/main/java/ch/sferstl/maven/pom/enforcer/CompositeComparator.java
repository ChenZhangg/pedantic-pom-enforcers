package ch.sferstl.maven.pom.enforcer;

import java.util.Comparator;

public class CompositeComparator<T> implements Comparator<T> {


  private final Comparator<T>[] comparators;

  /**
   *
   */
  @SafeVarargs
  public CompositeComparator(Comparator<T>... comparators) {
    this.comparators = comparators;
  }

  @Override
  public int compare(T o1, T o2) {
    int result = 0;
    for (Comparator<T> comparator : this.comparators) {
      result = comparator.compare(o1, o2);
      if (result != 0) {
        break;
      }
    }
    return result;
  }

}