package ch.sferstl.maven.pomenforcer;

import java.util.Collection;

import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;


public abstract class AbstractPedanticEnforcer implements EnforcerRule {

  private static final Splitter COMMA_SPLITTER = Splitter.on(",");
  protected static final Joiner COMMA_JOINER = Joiner.on(",");

  protected MavenProject getMavenProject(EnforcerRuleHelper helper) {
    MavenProject project;
    try {
      project = (MavenProject) helper.evaluate("${project}");
    } catch (ExpressionEvaluationException e) {
      throw new IllegalStateException("Unable to get maven project", e);
    }
    return project;
  }

  protected void splitAndAddToCollection(
      String commaSeparatedItems, Collection<String> collection) {
    Function<String, String> identity = Functions.identity();
    this.splitAndAddToCollection(commaSeparatedItems, collection, identity);
  }

  protected <T> void splitAndAddToCollection(
      String commaSeparatedItems, Collection<T> collection, Function<String, T> transformer) {
    Iterable<String> items = COMMA_SPLITTER.split(commaSeparatedItems);
    // Don't touch the collection if there is nothing to add.
    if (items.iterator().hasNext()) {
      collection.clear();
    }
    Iterables.addAll(collection, Iterables.transform(items, transformer));
  }

  protected abstract void accept(PedanticEnforcerVisitor visitor);

  @Override
  public boolean isCacheable() {
    return false;
  }

  @Override
  public boolean isResultValid(EnforcerRule cachedRule) {
    return false;
  }

  @Override
  public String getCacheId() {
    return getClass() + "-uncachable";
  }

}
