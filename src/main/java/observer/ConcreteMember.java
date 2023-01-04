package observer;

public class ConcreteMember implements Member {
    private UndoableStringBuilder strBuilder;


    public ConcreteMember(GroupAdmin groupAdmin) {

        this.strBuilder = groupAdmin.getStringBuilder();
    }

//    public ConcreteMember(String m) {
//
//    }





    /**
        Updating the string builder for this class
     */

    @Override
    public void update(UndoableStringBuilder usb) {
        this.strBuilder = usb;
    }
    @Override
    public String toString() {
        return strBuilder.toString();
    }
}
