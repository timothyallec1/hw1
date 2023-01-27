import java.util.*;
/* timothy allec 1/26/2023 EGR 227 hw1
collaborators include: d'artagnan, grace, phinehas, vincent, egr227 hints, amber harris
*/

public class HtmlValidator {

    private Queue<HtmlTag> queue;

    // creates an empty queue
    public HtmlValidator() {
        this.queue = new LinkedList<>();
    }

    //Create an HtmlValidator with the content of tags
    //creates a copy of the passed in queue

    public HtmlValidator(Queue<HtmlTag> tags) {
        if (tags == null) {
            throw new IllegalArgumentException("null.");
        }
        this.queue = new LinkedList<>(tags);
    }

    // use this method to  adds tag to HtmlValidator
    public void addTag(HtmlTag tag) {
        queue.add(tag);
        if (tag == null) {
            throw new IllegalArgumentException("null");
        }
    }

    // returns copy of the tags that were added
    public Queue<HtmlTag> getTags() {
        {
            return new LinkedList<>(queue);
        }
    }

    // removes any tags that match each other
    public void removeAll(String s) {
        Queue<HtmlTag> q = new LinkedList<>();
        for (HtmlTag tag : queue) {
            if (!tag.getElement().equalsIgnoreCase(s)) {
                q.add(tag);
            }
        }
        queue = q;
//        queue.removeIf(tag -> tag.getElement().equalsIgnoreCase(s));
        if (s== null) throw new IllegalArgumentException();

    }
    /* this method prints the validated tags in proper html indentation form
    and also throws an error if there is improper syntax
     */
    public void validate() {
        int indentCount = 0;
        Stack<HtmlTag> openTags = new Stack<>();
        for (int i = 0; i < queue.size(); i++) {
            HtmlTag tag = queue.remove();
            queue.add(tag);

            if (tag.isSelfClosing()) { print(tag, openTags.size());
            } else if (tag.isOpenTag()) { print(tag, openTags.size());
                openTags.push(tag);
            } else if (!openTags.isEmpty() && tag.matches(openTags.peek())) {
                openTags.pop();
                print(tag, openTags.size());
            } else {
                System.out.println("ERROR unexpected tag: " + tag.toString());
            }
        }
        // Deal with unclosed tags
        while (!openTags.isEmpty()) {
            HtmlTag tag = openTags.pop();
            System.out.println("ERROR unclosed tag: " + tag.toString());
        }
    }

    /* this method add proper indent when printing the html string in the validate() method
    this method was found on the hints, i decided to keep it because previously
    I hadimplemented this into the validate method but this makes it easier
    */
    private static void print(HtmlTag tag, int indentationLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentationLevel; i++) {
            sb.append("    ");
        }
        sb.append(tag.toString());
        System.out.println(sb.toString());
    }
}