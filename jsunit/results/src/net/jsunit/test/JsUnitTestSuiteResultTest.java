package net.jsunit.test;
import net.jsunit.*;
/**
 * @author Edward Hieatt
 * 
 * ***** BEGIN LICENSE BLOCK *****
   - Version: MPL 1.1/GPL 2.0/LGPL 2.1
   -
   - The contents of this file are subject to the Mozilla Public License Version
   - 1.1 (the "License"); you may not use this file except in compliance with
   - the License. You may obtain a copy of the License at
   - http://www.mozilla.org/MPL/
   -
   - Software distributed under the License is distributed on an "AS IS" basis,
   - WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
   - for the specific language governing rights and limitations under the
   - License.
   -
   - The Original Code is Edward Hieatt code.
   -
   - The Initial Developer of the Original Code is
   - Edward Hieatt, edward@jsunit.net.
   - Portions created by the Initial Developer are Copyright (C) 2003
   - the Initial Developer. All Rights Reserved.
   -
   - Author Edward Hieatt, edward@jsunit.net
   -
   - Alternatively, the contents of this file may be used under the terms of
   - either the GNU General Public License Version 2 or later (the "GPL"), or
   - the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
   - in which case the provisions of the GPL or the LGPL are applicable instead
   - of those above. If you wish to allow use of your version of this file only
   - under the terms of either the GPL or the LGPL, and not to allow others to
   - use your version of this file under the terms of the MPL, indicate your
   - decision by deleting the provisions above and replace them with the notice
   - and other provisions required by the LGPL or the GPL. If you do not delete
   - the provisions above, a recipient may use your version of this file under
   - the terms of any one of the MPL, the GPL or the LGPL.
   -
   - ***** END LICENSE BLOCK *****
   
   @author Edward Hieatt
 */
public class JsUnitTestSuiteResultTest extends JsUnitTest {
	protected JsUnitTestSuiteResult result;
	public JsUnitTestSuiteResultTest(String name) {
		super(name);
	}
	public void setUp() throws Exception {
		super.setUp();
		result = new JsUnitTestSuiteResult();
		result.setJsUnitVersion("2.5");
		result.setOs("Windows 2000");
		result.setBrowser("IE 6.0");
		result.setTime(4.3);
		result.setTestCaseStrings(new String[] { "testFoo|1.3|S||", "testFoo|1.3|E|Error Message|", "testFoo|1.3|F|Failure Message|" });
	}
	public void testId() {
		assertNotNull(result.getId());
		result = new JsUnitTestSuiteResult();
		result.setId("foo");
		assertEquals("foo", result.getId());
	}
	public void testFields() {
		assertEquals("2.5", result.getJsUnitVersion());
		assertEquals("Windows 2000", result.getOs());
		assertEquals("IE 6.0", result.getBrowser());
		assertEquals(4.3d, result.getTime(), 0.001d);
		assertEquals(3, result.getTestCaseResults().size());
	}
	public void testXml() {
		assertEquals(
			"<testsuite errors=\"1\" failures=\"1\" name=\"JsUnitTest\" tests=\"3\" time=\"4.3\">"+
				"<properties><property name=\"JsUnitVersion\" value=\"2.5\" />"+
					"<property name=\"os\" value=\"Windows 2000\" />"+
					"<property name=\"browser\" value=\"IE 6.0\" />"+
					"</properties>"+
				"<testcase name=\"testFoo\" time=\"1.3\" />"+
				"<testcase name=\"testFoo\" time=\"1.3\">"+
					"<error message=\"Error Message\" />"+
				"</testcase>"+
				"<testcase name=\"testFoo\" time=\"1.3\">"+
					"<failure message=\"Failure Message\" />"+
				"</testcase>"+
			"</testsuite>",
			result.writeXmlFragment());
	}
}
