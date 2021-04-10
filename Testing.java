import java.util.ArrayList;
import java.util.Arrays;

public class Testing {

    public static void main(String[] args) {

        WordTree test1 = new WordTree();

        ArrayList<String> al = new ArrayList<String>();
        ArrayList<String> result = new ArrayList<String>();
        TreeNode T = new TreeNode();
        al.add("pp");
        al.add("pple");
        al.add("n");

        test1.insertWord("i");
        test1.insertWord("insert");
        test1.insertWord("an");
        test1.insertWord("app");
        test1.insertWord("apples");
        test1.insertWord("applet");
        test1.insertWord("or");
        test1.insertWord("orange");
        test1.insertWord("origin");
//        test1.insertWord("");



        System.out.println(test1.suggestCorrections("orbign",4));

//        result = test1.WordEndings(test1.root, "jell");
//
//
//        for(String word : result){
//            System.out.println(word);
//        }
//        result = test1.suggestCorrections("apples",300);
//
//                for(String word : test1){
//            System.out.println(word);
//        }

//        T = test1.Find_prefixNode(test1.root, "hello");
//
//        System.out.println(T.isWord);






//        result = test1.addToEach('a',al);
//
//        for (String word : result){
//            System.out.println(word);
//        }




    }












}
