import org.junit.Assert;
import org.junit.Test;
import java.io.*;
import java.util.*;

/* timothy allec 1/26/2023 EGR 227 hw1
collaborators include: d'artagnan, grace, phinehas, vincent, egr227 hints, amber harris
*/

public class HtmlValidatorTest {

    // this code returns what was given in the file to a string
    private static String expectedOutputToString(String expectedFileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner fileScanner = new Scanner(new File(expectedFileName));
            while (fileScanner.hasNextLine()) {
                sb.append(fileScanner.nextLine() + System.lineSeparator());
            }
        } catch (FileNotFoundException ex) {
            Assert.fail(expectedFileName + "not found. Make sure this file exists. Use relative path to root in front of the file name");
        }
        return sb.toString();
    }

    // this method returns the string of what the validator method prints to the console
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

 // this tests the ability to add a tag to the html validator (addTag method)
    @Test
    public void addTagTest1() {

        HtmlTag[] Array = {new HtmlTag("The "), new HtmlTag("Sky")};
        Queue<HtmlTag> q = new LinkedList<>(Arrays.asList(Array));
        HtmlValidator v = new HtmlValidator();
        for(int i = 0; i < q.size(); i++){
            v.getTags();
        }
        q.forEach(v::addTag);
        Assert.assertEquals(q, v.getTags());
    }
    // another addTag test
    @Test
    public void addTagTest2() {

        HtmlTag[] Array = {new HtmlTag(""), new HtmlTag("$")};
        Queue<HtmlTag> q = new LinkedList<>(Arrays.asList(Array));
        HtmlValidator v = new HtmlValidator();
        for(int i = 0; i < q.size(); i++){
            v.getTags();
        }
        q.forEach(v::addTag);
        Assert.assertEquals(q, v.getTags());
    }

    // this tests the ability to remove a chosen element from the htmlValidator
    @Test
    public void removeAllTest1() {
        HtmlTag[] qArray = {new HtmlTag("The"), new HtmlTag("Sky")};
        List<HtmlTag> q = new ArrayList<>(Arrays.asList(qArray));
        HtmlValidator v = new HtmlValidator();
            q.forEach(v::addTag);
            v.addTag(new HtmlTag("Is"));
            v.addTag(new HtmlTag("Blue"));
            v.removeAll("Is");
            v.removeAll("Blue");
        Assert.assertEquals(q, v.getTags());
    }
// another removeAll test
    @Test
    public void removeAllTest2() {
        HtmlTag[] qArray = {new HtmlTag("What time")};
        List<HtmlTag> q = new ArrayList<>(Arrays.asList(qArray));
        HtmlValidator v = new HtmlValidator();
            q.forEach(v::addTag);
            v.addTag(new HtmlTag("Is IT??!"));
            v.removeAll("Is IT??!");
        Assert.assertEquals(q, v.getTags());

    }
    // another removeAll test with an empty instantiated HtmlTagArray
    @Test
    public void removeAllTest3() {
        HtmlTag[] qArray = {new HtmlTag("")};
        List<HtmlTag> q = new ArrayList<>(Arrays.asList(qArray));
        HtmlValidator v = new HtmlValidator();
            q.forEach(v::addTag);
            v.addTag(new HtmlTag("."));
            v.addTag(new HtmlTag("/"));
            v.removeAll(".");
            v.removeAll("/");
        Assert.assertEquals(q, v.getTags());
    }
    // ensures Validate method works for the expected output of test1
    public void test1() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("b", true));  //<b>
        q.add(new HtmlTag("i", true)); //<i>
        q.add(new HtmlTag("i", false)); //</i>
        q.add(new HtmlTag("b", false)); ///b
        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test1.txt"),
                validatorOutputToString(v));
    }
    // ensures Validate method works for the expected output of test2
    @Test
    public void test2() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("html", true));  //<b>
        q.add(new HtmlTag("b", true)); //<i>
        q.add(new HtmlTag("i", true)); //</i>
        q.add(new HtmlTag("i", false)); ///b
        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test2.txt"),
                validatorOutputToString(v));
    }

    // ensures Validate method works for the expected output of test3
    @Test
    public void test3() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("b", true));
        q.add(new HtmlTag("i", true));
        q.add(new HtmlTag("b", false));
        q.add(new HtmlTag("i", false));
        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test3.txt"),
                validatorOutputToString(v));
    }

    // ensures Validate method works for the expected output of test4
    @Test
    public void test4() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("b", true));
        q.add(new HtmlTag("i", true));
        q.add(new HtmlTag("b", false));
        q.add(new HtmlTag("i", false));
        q.add(new HtmlTag("b", false));
        q.add(new HtmlTag("html", false));
        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test4.txt"),
                validatorOutputToString(v));

    }

    // ensures Validate method works for the expected output of test5
    @Test
    public void test5() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("html", false));
        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test5.txt"),
                validatorOutputToString(v));
    }

    // test 6 is an empty test, no q added
    @Test
    public void test6() {
        Queue<HtmlTag> q = new LinkedList<>();
        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test6.txt"),
                validatorOutputToString(v));
    }

    // ensures Validate method works for the expected output of test7
    @Test
    public void test7() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("!doctype"));
        q.add(new HtmlTag("!--"));

        q.add(new HtmlTag("html", true));
        q.add(new HtmlTag("head", true));
        q.add(new HtmlTag("title", true));
        q.add(new HtmlTag("title", false));

        q.add(new HtmlTag("meta"));
        q.add(new HtmlTag("link"));
        q.add(new HtmlTag("head", false));
        q.add(new HtmlTag("body", true));

        q.add(new HtmlTag("p", true));
        q.add(new HtmlTag("a", true));
        q.add(new HtmlTag("a", false));
        q.add(new HtmlTag("p", false));

        q.add(new HtmlTag("p", true));
        q.add(new HtmlTag("img"));
        q.add(new HtmlTag("p", false));
        q.add(new HtmlTag("body", false));
        q.add(new HtmlTag("html", false));

        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test7.txt"),
                validatorOutputToString(v));

    }

    // ensures Validate method works for the expected output of test8
    @Test
    public void test8() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("!doctype"));
        q.add(new HtmlTag("!--"));

        q.add(new HtmlTag("html", true));
        q.add(new HtmlTag("head", true));
        q.add(new HtmlTag("title", true));
        q.add(new HtmlTag("meta"));

        q.add(new HtmlTag("link"));
        q.add(new HtmlTag("head", false));
        q.add(new HtmlTag("head", false));
        q.add(new HtmlTag("body", true));

        q.add(new HtmlTag("p", true));
        q.add(new HtmlTag("a", true));
        q.add(new HtmlTag("a", false));
        q.add(new HtmlTag("p", false));


        q.add(new HtmlTag("br", false));
        q.add(new HtmlTag("p", true));
        q.add(new HtmlTag("img"));
        q.add(new HtmlTag("p", false));
        q.add(new HtmlTag("html", false));

        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/validate_result_for_test8.txt"),
                validatorOutputToString(v));
    }
    @Test
    public void testUniqueValidate() {
        Queue<HtmlTag> q = new LinkedList<>();
        q.add(new HtmlTag("html", true));  //<b>
        q.add(new HtmlTag("i", true)); //<i>
        q.add(new HtmlTag("title", true)); //</i>
        q.add(new HtmlTag("title", false)); ///b
        HtmlValidator v = new HtmlValidator(q);

        Assert.assertEquals(expectedOutputToString
                        ("hw1-HW1-STARTER-MATERIALS/expected_output/my_unique_validate_result"),
                validatorOutputToString(v));
    }

    // i created a test with a new text file to test the validate method against it.
// tests null for removeAll, should throw an exception
    @Test(expected = IllegalArgumentException.class)
    public void removeNullTest() {
        HtmlValidator v = new HtmlValidator();
        v.removeAll(null);
    }
// tests null for addq, should throw an exception
    @Test(expected = IllegalArgumentException.class)
    public void addNullTagTest() {
        HtmlValidator v = new HtmlValidator();
        v.addTag(null);
    }
}

