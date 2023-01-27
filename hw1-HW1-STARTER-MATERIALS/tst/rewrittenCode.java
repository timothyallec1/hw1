//import java.util.*;
//
//public class rewrittenCode {
//    public void validate(){
//        int size = queue.size();
//        Stack<HtmlTag> tags = new Stack();
//        for(int i = 1; i <= size; i++){
//            HtmlTag test = queue.remove();
//            queue.add(test);
//            if(test.isOpenTag()){
//                print(test, tags.size());
//                tags.push(test);
//            }
//            else if(test.isSelfClosing()){
//                print(test,tags.size());
//            } else if(test.matches(tags.peek()) && !test.isEmpty()){
//                print(test, tags.size());
//                tags.pop();
//            }
//            else{
//                System.out.println("ERROR unexpected tag: " + html.toString());
//            }
//
//
//        }
//    }
//}
