package observer;


import java.util.ArrayList;

public class GroupAdmin implements Sender {
    UndoableStringBuilder strBuilder;
    ArrayList<Member> members;

    public GroupAdmin() {
        this.strBuilder = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
    }


    /**
        notifying all members to update
     */
    public void notifyAllMembers() {
        for (Member member : this.members) {
            member.update(strBuilder);
        }
    }

    /**
        register new member by adding him to the members list
     */
    @Override
    public void register(Member obj) {
        members.add(obj);
    }

    /**
      unregister member by removing him from members list
     */
    @Override
    public void unregister(Member obj) {
       members.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        if(obj== null) {
            return;
        }
        strBuilder.insert(offset, obj);
        notifyAllMembers();
    }

    @Override
    public void append(String obj) {
        if(obj== null) {
            return;
        }
        strBuilder.append(obj);

        notifyAllMembers();
    }

    @Override
    public void delete(int start, int end) {

        strBuilder.delete(start, end);
        notifyAllMembers();
    }

    @Override
    public void undo() {
        strBuilder.undo();
        notifyAllMembers();
    }

    @Override
    public String toString() {
        return strBuilder.toString();
    }

    /**
       getting the string builder for group Admin
     */

    public  UndoableStringBuilder getStringBuilder (){
         return strBuilder;
    }


}
