public class GeneralHasing {
    int[] randomNumberArray = new int[100];
    int[] hashedTable = new int[randomNumberArray.length];
    int[] hashedTableLinear = new int[randomNumberArray.length];
    DoublLinkedListNod[] hashChainingTable;



    int QuadraticCollisionCounter = 0;
    int QuadraticCollisionSolvingAttemptsCounter = 0;

    int LinearCollisionCounter = 0;
    int LinearCollisionSolvingAttemptsCounter = 0;

    int ChainingCollisionCounter = 0;


    //   constructor create 100 random numbers.
    public GeneralHasing() {
        hashChainingTable = new DoublLinkedListNod[100];
        for (int i = 0; i < randomNumberArray.length; i++) {
            randomNumberArray[i] = (int) (Math.random() * (99999 - 10000) + 10000);
        }
    }

    //hash function returns the mode
    public int hash(int value) {
        return value % randomNumberArray.length;
    }

    //  insert with Quadratic probing function
    public void insertWithQuadraticProbing(int value) {
        int index = hash(value);
        int i = 1;
        int singleConflictCounter = 0;

        while (hashedTable[index] != 0) {
            if (singleConflictCounter == randomNumberArray.length){
                insertWithLinearProbing(value);
            }
            // to count how many times the collision happened
            if (i == 1) {
                QuadraticCollisionCounter++;
            }

            index = (hash(value)+(i^2)) % randomNumberArray.length;
            i++;
            // count how many attempts to solve the collision
            QuadraticCollisionSolvingAttemptsCounter++;
            singleConflictCounter++;
        }
//        System.out.println("Inserting " + value + " at index " + index + " With collision times = " + singleConflictCounter);
        hashedTable[index] = value;
    }

    //  insert with Linear probing function
    public void insertWithLinearProbing(int value) {
        int index = hash(value);
        int i = 1;
        int singleConflictCounter = 0;
        while (hashedTableLinear[index] != 0) {
            // to count how many times the collision happened
            if (i == 1) {
                LinearCollisionCounter++;
            }
            index = (hash(value)+i) % randomNumberArray.length;
            i++;
            // count how many attempts to solve the collision
            LinearCollisionSolvingAttemptsCounter++;
            singleConflictCounter++;
        }
//        System.out.println("Inserting " + value + " at index " + index + " With collision times = " + singleConflictCounter);
        hashedTableLinear[index] = value;
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
            insertWithQuadraticProbing(randomNumberArray[i]);
            insertWithLinearProbing(randomNumberArray[i]);
            insertWithChaining(randomNumberArray[i]);
        }
    }

    public void print() {
        System.out.println("************ Statistics For Quadratic Probing ************");
        System.out.println("Collision Counter = " + QuadraticCollisionCounter);
        System.out.println("Collision Solving Attempts Counter = " + QuadraticCollisionSolvingAttemptsCounter);
        System.out.println("************ Statistics For Linear Probing ************");
        System.out.println("Collision Counter = " + LinearCollisionCounter);
        System.out.println("Collision Solving Attempts Counter = " + LinearCollisionSolvingAttemptsCounter);
        System.out.println("************ Statistics For Chaining ************");
        System.out.println("Collision Counter = " + ChainingCollisionCounter);
    }





}
