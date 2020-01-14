import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    // Варианты 1-20 - из задания
    @Test
    public void calculator1() {
        String s = "2+3";
        String actual = Calculator.calculator(s);
        String expected = "5";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator2() {
        String s = "4-6";
        String actual = Calculator.calculator(s);
        String expected = "-2";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator3() {
        String s = "2*3";
        String actual = Calculator.calculator(s);
        String expected = "6";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator4() {
        String s = "12/3";
        String actual = Calculator.calculator(s);
        String expected = "4";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator5() {
        String s = "2+3*4";
        String actual = Calculator.calculator(s);
        String expected = "14";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator6() {
        String s = "10/2-7+3*4";
        String actual = Calculator.calculator(s);
        String expected = "10";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator7() {
        String s = "10/(2-7+3)*4";
        String actual = Calculator.calculator(s);
        String expected = "-20";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator8() {
        String s = "22/3*3.0480";
        String actual = Calculator.calculator(s);
        String expected = "22.352";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator9() {
        String s = "22/4*2.159";
        String actual = Calculator.calculator(s);
        String expected = "11.8745";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator10() {
        String s = "22/4*2,159";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator11() {
        String s = "- 12)1//(";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator12() {
        String s = "10/(5-5)";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator13() {
        String s = null;
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator14() {
        String s = "(12*(5-1)";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator15() {
        String s = "";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator16() {
        String s = "5+41..1-6";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator17() {
        String s = "5++41-6";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator18() {
        String s = "5--41-6";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator19() {
        String s = "5**41-6";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    @Test
    public void calculator20() {
        String s = "5//41-6";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

    // Мои варианты
    @Test
    public void calculator21() {
        String s = "((0.5) + (0.5))";
        String actual = Calculator.calculator(s);
        String expected = "1";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator22() {
        String s = "(1 + 38) * 4.5 - 1 / 2";
        String actual = Calculator.calculator(s);
        String expected = "176";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculator23() {
        String s = "5+2d";
        String actual = Calculator.calculator(s);
        Assert.assertNull(actual);
    }

}