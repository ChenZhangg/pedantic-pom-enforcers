/*
 * Copyright (c) 2012 by The Author(s)
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
package ch.sferstl.maven.pomenforcer.reader;

import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.XStream;


public class DeclaredModulesReader extends AbstractPomSectionReader<List<String>> {

  private static final String MODULES_XPATH = "/project/modules";
  private static final String MODULES_ALIAS = "modules";
  private static final String MODULE_ALIAS = "module";

  public DeclaredModulesReader(Document pom) {
    super(pom);
  }

  @Override
  protected XPathExpression createXPathExpression(XPath xpath) throws XPathExpressionException {
    return xpath.compile(MODULES_XPATH);
  }

  @Override
  protected void configureXStream(XStream xstream) {
    xstream.alias(MODULES_ALIAS, List.class);
    xstream.alias(MODULE_ALIAS, String.class);
  }

  @Override
  protected List<String> getUndeclaredSection() {
    return Lists.newArrayList();
  }
}
