package zadanie10;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AnnotationBasedToStringTest {

  private final ToStringGenerator generator = new AnnotationBasedToString();

  @Test
  void testSingleFieldFormatting() {
    class TestClass {
      @ToString
      public int p = 10;
    }

    TestClass obj = new TestClass();
    String result = generator.toString(obj);

    String expected = "TestClass p:10";

    Assertions.assertEquals(expected, result);
  }
  @Test
  void testTwoFieldsWithAnnotations() {
    class TestClass {
      @ToString
      public int pbds = 12345;

      public int p200s = 1;
    }

    TestClass obj = new TestClass();
    String result = generator.toString(obj);

    String expected = "TestClass pbds:12345";

    Assertions.assertEquals(expected, result,
            "Incorrect result for TestClass with a mix of annotated and non-annotated fields.");
  }
  @Test
  void testMultipleAnnotatedFields() {
    class TestClass {
      @ToString
      public int cppp = 4040;

      @ToString
      public int ap = 20304;

      @ToString
      public int bpp = 321;
    }

    TestClass obj = new TestClass();
    String result = generator.toString(obj);

    String expected = "TestClass cppp:4040 ap:20304 bpp:321";

    Assertions.assertEquals(expected, result,
            "Incorrect result for TestClass with multiple annotated fields.");
  }
  @Test
  void testFieldsWithPriorities() {
    class TestClass {
      @ToString(priority = 400)
      public int cappp = 1510;

      @ToString(priority = 400)
      public int cbppp = 5220;

      @ToString(priority = 400)
      public int ccppp = 5330;

      @ToString(priority = 200)
      public int ap = 260;

      @ToString(priority = 300)
      public int bp = 1300;
    }

    TestClass obj = new TestClass();
    String result = generator.toString(obj);

    String expected = "TestClass cappp:1510 cbppp:5220 ccppp:5330 bp:1300 ap:260";

    Assertions.assertEquals(expected, result,
            "Incorrect result for TestClass with fields having priorities.");
  }

  @Test
  void testFieldsWithPrioritiesAndDescendingOrder() {
    class TestClass {
      @ToString(priority = 400, order = Order.DESCENDING)
      public int capppv = 410;

      @ToString(priority = 400, order = Order.DESCENDING)
      public int cbpppv = 420;

      @ToString(priority = 400, order = Order.DESCENDING)
      public int ccpppv = 430;

      @ToString(priority = 200)
      public int ap = 280;

      @ToString(priority = 300)
      public int bpp = 307;
    }

    TestClass obj = new TestClass();
    String result = generator.toString(obj);

    String expected = "TestClass ccpppv:430 cbpppv:420 capppv:410 bpp:307 ap:280";

    Assertions.assertEquals(expected, result,
            "Incorrect result for TestClass with fields having priorities and descending order.");
  }

}
