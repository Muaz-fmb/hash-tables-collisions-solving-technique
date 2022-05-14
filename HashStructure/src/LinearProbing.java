public class LinearProbing {

    int[] randomNumberArray = new int[100];
    int[] hashedTableLinear = new int[randomNumberArray.length];


    int LinearCollisionCounter = 0;
    int LinearCollisionSolvingAttemptsCounter = 0;

    public LinearProbing() {
        for (int i = 0; i < randomNumberArray.length; i++) {
            randomNumberArray[i] = (int) (Math.random() * (99999 - 10000) + 10000);
        }
    }

    //hash function returns the mode
    public int hash(int value) {
        return value % randomNumberArray.length;
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
        System.out.println("Inserting " + value + " at index " + index + " With collision times = " + singleConflictCounter);
        hashedTableLinear[index] = value;
    }

    //    insert values from randomNumberArray to hashedTable with insert functions
    public void insertValues() {
        for (int i = 0; i < randomNumberArray.length; i++) {
            insertWithLinearProbing(randomNumberArray[i]);
        }
    }

    public void print() {
        System.out.println("************ Statistics For Linear Probing ************");
        System.out.println("Collision Counter = " + LinearCollisionCounter);
        System.out.println("Collision Solving Attempts Counter = " + LinearCollisionSolvingAttemptsCounter);
    }

}
