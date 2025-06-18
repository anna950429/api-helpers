package org.otus.tests;

import org.otus.tests.helpers.SqlHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SqlHelperTest {

  @Test
  void testSelect() {
    SqlHelper helper = new SqlHelper();
    assertTrue(helper.selectUserNames().contains("Test user"));
  }
}
