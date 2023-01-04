package observer;

/*
Use the class you've implemented in previous assignment
 */
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;



public class UndoableStringBuilder {


    private LinkedList<Character> strList;
    private LinkedList<LinkedList<String>> operationsList;
    private boolean doOperation = true;


    /* String Builder Constructor */
    public UndoableStringBuilder() {
        //Initializing Linkedlist
        strList = new LinkedList<Character>();
        operationsList = new LinkedList<LinkedList<String>>();
    }


    //   // [[ACTION ,start ,end]]
    /* Appending New String to the end of the list */
    public UndoableStringBuilder append(String str) {

        if (doOperation) {
            LinkedList<String> operation = new LinkedList<String>();
            operation.add("APPEND");
            operation.add(Integer.toString(strList.size()));
            operation.add(Integer.toString(strList.size() + str.length()));
            operationsList.add(operation);
        }
        for (int i = 0; i < str.length(); i++) {
            strList.add(str.charAt(i));
        }


        return this;
    }

    /* Deleting chars from the list at specific interval  */
    public UndoableStringBuilder delete(int start, int end) {
        if(strList.size()==0){
         return null;

        }


        if (doOperation) {
            String t="";
            for(int i= start; i<end; i++){
                t += strList.get(i);

            }
            LinkedList<String> operation = new LinkedList<String>();
            operation.add("DELETE");
            operation.add(Integer.toString(start));
            operation.add(t);
            operationsList.add(operation);



        }
        try {
            strList.subList(start, end).clear();
//            operationsList.add(temp);

        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(error);
        }


        return this;
    }

    /* inserting New String to the list at specific offset */
    public UndoableStringBuilder insert(int offset, String str) {
        if (doOperation) {
            LinkedList<String> operation = new LinkedList<String>();
            operation.add("INSERT");
            operation.add(Integer.toString(offset));
            operation.add(Integer.toString(offset+str.length()));
            operationsList.add(operation);

        }
        int track = offset;
        try {

            for (int i = 0; i < str.length(); i++) {
                strList.add(track, str.charAt(i));
                track++;
            }

        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println(error);
        }
        //undo

        return this;
    }


    // read with his friend
    public UndoableStringBuilder replace(int start, int end, String str) {
        if (doOperation) {
            String t = "";
            for (int i = start; i <= end; i++) {
                t += strList.get(i);

            }
            LinkedList<String> operation = new LinkedList<String>();
            operation.add("REPLACE");
            operation.add(Integer.toString(start));
            operation.add(Integer.toString(end));
            operation.add(t);
            operationsList.add(operation);
        }


        try {
            if(end - start+1==str.length()) {
                for (int i = 0; i < str.length(); i++) {
                    strList.set(start, str.charAt(i));
                    start++;
                }
            }
        }catch (ArrayIndexOutOfBoundsException error) {

            System.out.println(error);
        }



        return this;



    }

    /* Reversing the list of chars using Java Collections */
    public UndoableStringBuilder reverse() {

        Collections.reverse(strList);
        if (doOperation) {
            LinkedList<String> operation = new LinkedList<String>();
            operation.add("REVERSE");
            operationsList.add(operation);
        }

        return this;
    }


    // read with his friend
    public UndoableStringBuilder undo() {
        LinkedList<String> lastoperation = operationsList.pollLast();

        String action = lastoperation.get(0);
        doOperation = false;
        if (action == "APPEND") {
            int start = Integer.parseInt(lastoperation.get(1));
            int end = Integer.parseInt(lastoperation.get(2));
            delete(start, end);
        } else if (action == "DELETE") {
            int start= Integer.parseInt(lastoperation.get(1));
            String t= lastoperation.get(2);
            insert(start,t);


        } else if(action=="REPLACE"){
            int start= Integer.parseInt(lastoperation.get(1));
            int end= Integer.parseInt(lastoperation.get(2));
            String t= lastoperation.get(3);
            replace(start,end,t);


        }else if(action=="INSERT") {
            int start= Integer.parseInt(lastoperation.get(1));
            int end= Integer.parseInt(lastoperation.get(2));
            delete(start,end);


        }else{
            reverse();
        }
        doOperation = true;
        return this;
    }


    @Override
    public String toString() {
        String str = "";
        for (Character c : strList) {
            str += c;
        }
        return str;
    }

}
