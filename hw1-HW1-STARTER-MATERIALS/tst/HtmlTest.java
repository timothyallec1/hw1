import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.html.HTMLUListElement;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class HtmlTest {

 /** Below code returns the String format
     * of the content of the given file
     * @param expectedFileName The name of the file that has expected output
     *                         Make sure put relative path in front of
     *                         the file name
     *                         (For example, if your files under tst folder,
            *                         expectedFileName should be "tst/YOUR_FILE_NAME"
            * @return The String format of what the expectedFileName contains

  */
    private static String expectedOutputToString (String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine()+ System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    /** Below code returns the String format
     * of what your validator's validate prints to the console
     * Feel free to use it so that you can compare it with the expected string
     * @param validator HtmlValidator to test
     * @return String format of what HtmlValidator's validate outputs
     */
    private static String validatorOutputToString(HtmlValidator validator) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream oldOut = System.out;
        System.setOut(ps);
        validator.validate();
        System.out.flush();
        System.setOut(oldOut);
        return baos.toString();
    }


 /**    * This test is an instructor given test case to show you some example
     * of testing your validate() method
     * <b>Hi</b><br/> is the hypothetical html file to test
  * */
    public void test0(){
        //<b>Hi</b><br/>
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b", true));      // <b>
        tags.add(new HtmlTag("b", false));     // </b>
        tags.add(new HtmlTag("br"));           // <br/>
        HtmlValidator validator = new HtmlValidator(tags);

        //Note test0_expected_output.txt is placed under tst. Use relative path!
        Assert.assertEquals(expectedOutputToString("tst/test0_expected_output.txt"),
                validatorOutputToString(validator));
    }

    @Test
    public void test1() {
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b",true));  //<b>
        tags.add(new HtmlTag("i",true)); //<i>
        tags.add(new HtmlTag("i",false)); //</i>
        tags.add(new HtmlTag("b", false)); ///b
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test1.txt"),
                validatorOutputToString(validator));
         }

    @Test
    public void test2() {
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("html",true));  //<b>
        tags.add(new HtmlTag("b",true)); //<i>
        tags.add(new HtmlTag("i",true)); //</i>
        tags.add(new HtmlTag("i", false)); ///b
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test2.txt"),
                validatorOutputToString(validator));
    }


    @Test
    public void test3() {
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b",true));
        tags.add(new HtmlTag("i",true));
        tags.add(new HtmlTag("b",false));
        tags.add(new HtmlTag("i", false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test3.txt"),
                validatorOutputToString(validator));
    }


    @Test
    public void test4() {
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("b",true));
        tags.add(new HtmlTag("i",true));
        tags.add(new HtmlTag("b",false));
        tags.add(new HtmlTag("i", false));
        tags.add(new HtmlTag("b",false));
        tags.add(new HtmlTag("html",false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test4.txt"),
                validatorOutputToString(validator));

    }

    @Test
    public void test5() {
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("html",false));
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test5.txt"),
                validatorOutputToString(validator));


    }
// test 6 is an empty test, no tags added
    @Test
    public void test6() {
        Queue<HtmlTag> tags = new LinkedList<>();
        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test6.txt"),
                validatorOutputToString(validator));
    }

    @Test
    public void test7() {
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype"));
        tags.add(new HtmlTag("!--"));

        tags.add(new HtmlTag("html",true));
        tags.add(new HtmlTag("head", true));
        tags.add(new HtmlTag("title",true));
        tags.add(new HtmlTag("title",false));

        tags.add(new HtmlTag("meta"));
        tags.add(new HtmlTag("link"));
        tags.add(new HtmlTag("head",false));
        tags.add(new HtmlTag("body",true));

        tags.add(new HtmlTag("p",true));
        tags.add(new HtmlTag("a", true));
        tags.add(new HtmlTag("a",false));
        tags.add(new HtmlTag("p",false));

        tags.add(new HtmlTag("p",true));
        tags.add(new HtmlTag("img"));
        tags.add(new HtmlTag("p",false));
        tags.add(new HtmlTag("body",false));
        tags.add(new HtmlTag("html",false));

        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test7.txt"),
                validatorOutputToString(validator));

    }

    @Test
    public void test8() {
        Queue<HtmlTag> tags = new LinkedList<>();
        tags.add(new HtmlTag("!doctype"));
        tags.add(new HtmlTag("!--"));

        tags.add(new HtmlTag("html",true));
        tags.add(new HtmlTag("head", true));
        tags.add(new HtmlTag("title",true));
        tags.add(new HtmlTag("meta"));

        tags.add(new HtmlTag("link"));
        tags.add(new HtmlTag("head",false));
        tags.add(new HtmlTag("head",false));
        tags.add(new HtmlTag("body", true));

        tags.add(new HtmlTag("p",true));
        tags.add(new HtmlTag("a", true));
        tags.add(new HtmlTag("a",false));
        tags.add(new HtmlTag("p",false));


        tags.add(new HtmlTag("br", false));
        tags.add(new HtmlTag("p",true));
        tags.add(new HtmlTag("img"));
        tags.add(new HtmlTag("p",false));
        tags.add(new HtmlTag("html",false));

        HtmlValidator validator = new HtmlValidator(tags);

        Assert.assertEquals(expectedOutputToString("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test8.txt"),
                validatorOutputToString(validator));
    }

    @Test
    public void addTagTest() {
        HtmlTag[] tagsArr = {new HtmlTag("Hello"), new HtmlTag("There")};
        List<HtmlTag> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();

        tags.forEach(validator::addTag);

        Assert.assertEquals(tags, validator.getTags());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullTagTest() {
        HtmlValidator validator = new HtmlValidator();
        validator.addTag(null);
    }

    @Test
    public void removeAllTest() {
        HtmlTag[] tagsArr = {new HtmlTag("Hello"), new HtmlTag("There")};
        List<HtmlTag> tags = new ArrayList<>(Arrays.asList(tagsArr));
        HtmlValidator validator = new HtmlValidator();
        tags.forEach(validator::addTag);
        validator.addTag(new HtmlTag("General Kenobi"));

        validator.removeAll("General Kenobi");

        Assert.assertEquals(tags, validator.getTags());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeAllNullTest() {
        HtmlValidator validator = new HtmlValidator();

        validator.removeAll(null);
    }
}

