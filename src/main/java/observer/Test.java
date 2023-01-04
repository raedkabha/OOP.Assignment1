package observer;

public class Test {
    public static void main(String[] args) {
        GroupAdmin groupAdmin = new GroupAdmin();

        Member member1 = new ConcreteMember(groupAdmin);
        Member member2 = new ConcreteMember(groupAdmin);
        Member member3 = new ConcreteMember(groupAdmin);
        groupAdmin.register(member1);
        groupAdmin.register(member2);
        groupAdmin.register(member3);


        System.out.println("---------------------");
        groupAdmin.append("ffffz");
        System.out.println(groupAdmin);
        System.out.println(member1);
        System.out.println(member2);
        System.out.println(member3);

        System.out.println("---------------------");
        groupAdmin.append("ffffzhchgchchg");


        System.out.println("---------------------");
        System.out.println(groupAdmin);
        groupAdmin.unregister(member1);
//        groupAdmin.unregister(member2);
//        groupAdmin.unregister(member3);


        groupAdmin.append("aaa");
        System.out.println(groupAdmin);
        System.out.println(member1);
        System.out.println(member2);
        System.out.println(member3);




    }
}
