/*
 * Copyright (c) 2012 - 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ferstl.maven.pomenforcers;

import org.junit.Test;

import com.github.ferstl.maven.pomenforcers.model.DependencyScope;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * JUnit tests for {@link PedanticDependencyManagementOrderEnforcer}:
 */
public class PedanticDependencyManagementOrderEnforcerTest
extends AbstractPedanticDependencyOrderEnforcerTest<PedanticDependencyManagementOrderEnforcer> {

  @Override
  PedanticDependencyManagementOrderEnforcer createRule() {
    return new PedanticDependencyManagementOrderEnforcer();
  }

  @Test
  @Override
  public void getDescription() {
    assertThat(this.testRule.getDescription(), equalTo(PedanticEnforcerRule.DEPENDENCY_MANAGEMENT_ORDER));
  }

  @Test
  @Override
  public void accept() {
    PedanticEnforcerVisitor visitor = mock(PedanticEnforcerVisitor.class);
    this.testRule.accept(visitor);

    verify(visitor).visit(this.testRule);
  }

  @Override
  public DependencyAdder createDependencyAdder() {
    return new DependencyAdder() {

      @Override
      public void addDependency(String groupId, String artifactId, DependencyScope scope) {
        PedanticDependencyManagementOrderEnforcerTest.this.addManagedDependency(groupId, artifactId, scope);
      }

    };
  }
}
