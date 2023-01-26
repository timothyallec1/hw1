// timothy allec 1/23/2023
//collaborators include: d'artagnan, grace, phinehas, vincent, egr227 hints


import java.util.*;

public class HtmlValidator {

    private Queue<HtmlTag> queue;

   // creates an empty queue
    public HtmlValidator() {
        queue= new LinkedList<>();
    }

     //Create an HtmlValidator with the content of tags
     //creates a copy of the passed in queue

    public HtmlValidator(Queue<HtmlTag> tags) {
        this.queue = new LinkedList<>(tags);
        if (tags == null) {
            throw new IllegalArgumentException("null.");
        }
    }

    // this adds tag to HtmlValidator
    public void addTag(HtmlTag tag) {
        queue.add(tag);
        if (tag == null) {
            throw new IllegalArgumentException("null");
        }
    }

    // returns copy of the tags in HtmlValidator
    public Queue<HtmlTag> getTags() {
        // return a deep copy of tags
        return new LinkedList<>(queue);
    }

    // this removes any elements that matches each other
    public void removeAll(String element) {
            queue.removeIf(tag -> tag.getElement().equalsIgnoreCase(element));
            if (element == null) throw new IllegalArgumentException();
        }

    // prints out properly formatted HTML code from HtmlValidator
    public void validate() {
        int size = queue.size();
        Stack<HtmlTag> openTags = new Stack<>();
        for(int i = 0; i < size; i++) {
            HtmlTag html = queue.remove();
            queue.add(html);
            if (html.isSelfClosing()) {
                print(html, openTags.size());
            } else if (html.isOpenTag()) {
                print(html, openTags.size());
                openTags.push(html);
            } else if (!openTags.isEmpty() && html.matches(openTags.peek())) { // By exhaustion, the tag must be a closing tag
                // Closing tag should be at same depth as opening, so we pop before printing
                openTags.pop();
                print(html, openTags.size());
            }
                    else {
                System.out.println("ERROR unexpected tag: " + html.toString());
            }
        }
        // Deal with unclosed tags
        while (!openTags.isEmpty()) {
            HtmlTag tag = openTags.pop();
            System.out.println("ERROR unclosed tag: " + tag.toString());
        }
    }

    // Helper function to make printing at given indentation more convenient
    private static void print(HtmlTag tag, int indentationLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentationLevel; i++) {
            sb.append("    ");
        }
        sb.append(tag.toString());
        System.out.println(sb.toString());
    }
}