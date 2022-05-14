public class Chaining {


    // Declaration of Hash Table
    DoublLinkedListNod[] hashChainingTable;
    int[] randomNumberArray = new int[100];
    int ChainingCollisionCounter = 0;





    //   constructor create 100 random numbers.
    Chaining()
    {
        // Creating an empty Hash Table
        hashChainingTable = new DoublLinkedListNod[100];
        for (int i = 0; i < randomNumberArray.length; i++) {
            randomNumberArray[i] = (int) (Math.random() * (99999 - 10000) + 10000);
        }
    }

    //hash function returns the mode
    public int hash(int value) {
        return value % randomNumberArray.length;
    }


    // Function to insert a value/element
    public void insertWithChaining(int value){
        int position = hash(value);

        // creates a node for storing the new value
        DoublLinkedListNod node = new DoublLinkedListNod(value);

        if (hashChainingTable[position] == null){
            // if the position is empty then add it as the first node
            hashChainingTable[position] = node;
//            System.out.println("Inserting " + value + " at index " + position);
        }else {
            // if the position is not empty then add it as the first Nod in the position
            // creates a node for the start position
            DoublLinkedListNod start = hashChainingTable[position];
            ChainingCollisionCounter++;
            node.next = start;
            start.prev = node;
            hashChainingTable[position] = node;
//            System.out.println("Inserting " + value + " at index " + position);

        }
    }

    //    insert values from randomNumberArray to hashedTable with insert functions
    public void insertValues() {
        for (int i = 0; i < randomNumberArray.length; i++) {
            insertWithChaining(randomNumberArray[i]);
        }
    }

    public void print() {
        System.out.println("\n************ Statistics For Chaining ************");
        System.out.println("Collision Counter = " + ChainingCollisionCounter);
    }

}
