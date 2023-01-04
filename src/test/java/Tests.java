import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.JvmUtilities;
import observer.Member;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility



    @BeforeAll
    public static void startTest(){
        System.out.println("Starting tests");
        System.out.println("Info:");
        logger.info(JvmUtilities::jvmInfo);
    }



    @BeforeEach
    public void runTest(){
        System.out.println("runing test......");
        GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);

        printMemory();

        System.out.println("Choose Action");

    }
    @AfterAll
    public static void endTest(){
        System.out.println("Finshing Test");
    }






    @Test
    void Register(){
         GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);
        System.out.println("GroupAdmin: Regster");
        Boss.register(mem1);
        Boss.register(mem2);
        Boss.register(mem3);
        printMemory();

        Boss.append("MR,");
       assertEquals("MR," , Boss.toString());
        printMemory();

        Boss.append("hero");
        assertEquals("MR,hero", Boss.toString());
        printMemory();


    }
    @Test
    void Unregister(){
         GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);
        System.out.println("GroupAdmin Action: Unregster");
        Boss.register(mem1);
        Boss.register(mem2);
        Boss.register(mem3);
        Boss.append("MR,");
       assertEquals("MR,", Boss.toString());
        printMemory();

        Boss.unregister(mem1);
        Boss.unregister(mem2);
        Boss.unregister(mem3);
        assertEquals("MR,",Boss.toString());
        printMemory();

    }

    @Test
    void append(){
        GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);

        System.out.println(" GroupAdmin Action: Append");
        Boss.register(mem1);
        Boss.register(mem2);
        Boss.register(mem3);

        // memory check when append
        Boss.append("MR,");
        assertEquals("MR,", Boss.toString());
        printMemory();

        // memory check when append null
        Boss.append(null);
        assertEquals("MR,", Boss.toString());
        printMemory();

        // memory check when append empty string
        Boss.append("");
        assertEquals("MR,", Boss.toString());
        printMemory();

    }

    @Test
    void delete(){
        GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);

        System.out.println(" GroupAdmin Action: Delete");
        Boss.register(mem1);
        Boss.register(mem2);
        Boss.register(mem3);

        //memory check when delete while the string is null
        Boss.delete(1,3);
        assertEquals("", Boss.toString());
        printMemory();

        //deleting from a normal string
        Boss.append("MR,hero");
        Boss.delete(0,3);
        assertEquals("hero", Boss.toString());
        printMemory();

    }


    @Test
    void insert(){
        GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);

        System.out.println(" GroupAdmin Action: insert");
        Boss.register(mem1);
        Boss.register(mem2);
        Boss.register(mem3);

        // normal inserting
        Boss.append("MRhero");
        Boss.insert(2,",,,,");
        assertEquals("MR,,,,hero", Boss.toString());
        printMemory();

        //memory check when inserting null
        Boss.insert(2,null);
        assertEquals("MR,,,,hero", Boss.toString());
        printMemory();

        // memory check when empty string
        Boss.insert(1,"");
        assertEquals("MR,,,,hero", Boss.toString());
        printMemory();






    }





    @Test
    void undo(){
        GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);


        System.out.println("GroupAdmin Action: Undo");
        Boss.register(mem1);
        Boss.register(mem2);
        Boss.register(mem3);

        Boss.append("Businessman");
        Boss.delete(0,7);
        Boss.undo();
       assertEquals("Businessman", Boss.toString());
        printMemory();

        Boss.insert(0,"you are ");
        printMemory();
        Boss.undo();
        assertEquals("Businessman", Boss.toString());
        printMemory();

        Boss.insert(0,"you are ");
        Boss.delete(0,3);
        assertEquals(" are Businessman", Boss.toString());
        printMemory();

    }



    @Test
    void update(){



        System.out.println("ConcreteMember Action: Update ");
        GroupAdmin Boss= new GroupAdmin();
        Member mem1 = new ConcreteMember(Boss);

        Boss.register(mem1);
        Boss.append("Gentlemen");
        assertEquals("Gentlemen", mem1.toString());
        printMemory();

        Boss.delete(0,6);
        assertEquals("men", mem1.toString());
        printMemory();

        Boss.insert(0,"Gentle");
        assertEquals("Gentlemen", mem1.toString());
        printMemory();

        Boss.undo();
        assertEquals("men", mem1.toString());
        printMemory();


        Boss.unregister(mem1);

    }

    void printMemory(){
        GroupAdmin Boss = new GroupAdmin();

        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);

        System.out.println(JvmUtilities.objectFootprint(Boss,mem1,mem2,mem3));

        System.out.println("GroupAdmin Size");
        System.out.println(JvmUtilities.objectTotalSize(Boss));

        System.out.println("Members Size");
        System.out.println(JvmUtilities.objectTotalSize(mem1,mem2,mem3));



    }






    @Test
    public void test(){
       GroupAdmin Boss = new GroupAdmin();
        Member mem1 = new ConcreteMember(Boss);
        Member mem2 = new ConcreteMember(Boss);
        Member mem3 = new ConcreteMember(Boss);

       System.out.println("Testing sizes");
       logger.info(()-> JvmUtilities.objectTotalSize(Boss,mem1,mem2,mem3));
       logger.info(()-> JvmUtilities.objectFootprint(Boss,mem1,mem2,mem3));

       System.out.println("Registering member");
       Boss.register(mem1);
       Boss.register(mem2);
       Boss.register(mem3);

        logger.info(()-> JvmUtilities.objectTotalSize(Boss));
        logger.info(()-> JvmUtilities.objectFootprint(Boss));

        Boss.append("aaaaaa");
        logger.info(()-> JvmUtilities.objectTotalSize(Boss));
        logger.info(()-> JvmUtilities.objectFootprint(Boss));

        System.out.println("check the memory when unregister one of members");
        Boss.unregister(mem1);
        logger.info(()-> JvmUtilities.objectTotalSize(Boss));
        logger.info(()-> JvmUtilities.objectFootprint(Boss));

        System.out.println("Info:");
        logger.info(JvmUtilities::jvmInfo);

        System.out.println("Printing Memorystats");
        logger.info(()-> JvmUtilities.memoryStats(Boss));



    }
}
