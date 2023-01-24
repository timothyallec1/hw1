// timothy allec 1/23/2023
//collaborators include: d'artagnan, grace, phinehas, vincent, egr227 hints


import java.util.*;

public class HtmlValidator {

    private Queue<HtmlTag> tagsList;

   // creates an empty queue
    public HtmlValidator() {
        tagsList = new LinkedList<>();
    }

     //Create an HtmlValidator with the content of tags
     //creates a copy of the passed in queue

    public HtmlValidator(Queue<HtmlTag> tags) {
        this.tagsList = new LinkedList<>(tags);
        if (tags == null) {
            throw new IllegalArgumentException("cannot be null.");
        }
    }

    // this adds tag to HtmlValidator
    public void addTag(HtmlTag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Cannot add a null tag");
        }
        tagsList.add(tag);
    }

    // returns copy of the tags in HtmlValidator
    public Queue<HtmlTag> getTags() {
        // return a deep copy of tags
        return new LinkedList<>(tagsList);
    }

    // this removes any elements that matches each other
    public void removeAll(String element) {
        if (element == null) throw new IllegalArgumentException();
        tagsList.removeIf(tag -> tag.getElement().equalsIgnoreCase(element));

    }

    // prints out properly formatted HTML code from HtmlValidator
    public void validate() {
        Stack<HtmlTag> openTags = new Stack<>();
        for(int i = 0; i < tagsList.size(); i++) {
            HtmlTag tag = tagsList.remove();
            tagsList.add(tag);

            if (tag.isSelfClosing()) {
                printWithIndentation(tag, openTags.size());
            } else if (tag.isOpenTag()) {
                printWithIndentation(tag, openTags.size());
                openTags.push(tag);
            } else if (!openTags.isEmpty() && tag.matches(openTags.peek())) { // By exhaustion, the tag must be a closing tag
                // Closing tag should be at same depth as opening, so we pop before printing
                openTags.pop();
                printWithIndentation(tag, openTags.size());
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

    // Helper function to make printing at given indentation more convenient
    private static void printWithIndentation(HtmlTag tag, int indentationLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentationLevel; i++) {
            sb.append("    ");
        }
        sb.append(tag.toString());
        System.out.println(sb.toString());
    }
}