package semestr2num2;

public class SegmentTree {
    int segArr[];
    public int count = 0;

    SegmentTree(int a[], int s){
        int SegHeight = (int) (Math.ceil(Math.log(s) / Math.log(2)));
        int maxSeg = 2 * (int) Math.pow(2, SegHeight) - 1;
        segArr = new int[maxSeg];
        constructSegTree(a, 0, s - 1, 0);
    }

    int getMiddleInx(int first, int last){
        count++;
        return first + (last - first) / 2;
    }

    void valUpdateUtil(int x, int y, int inputIdx, int val, int sidx){
        if (inputIdx < x || inputIdx > y){
            count++;
            return;
        }
        count++;
        segArr[sidx] = segArr[sidx] + val;
        if (y != x){
            count++;
            int middle = getMiddleInx(x, y);
            valUpdateUtil(x, middle, inputIdx, val, 2 * sidx + 1);
            valUpdateUtil(middle + 1, y, inputIdx, val, 2 * sidx + 2);
        }
    }

    public void valUpdate(int a[], int size, int index, int newVal) {
        if (index < 0 || index > size - 1) {
            count++;
            System.out.println("Input is Invalid");
            return;
        }
        count++;
        int diffVal = newVal - a[index];
        segArr[index] = newVal;
        valUpdateUtil(0, size - 1, index, diffVal, 0);
    }

    int getSumUtil(int x, int y, int i, int j, int sidx){
        if (i <= x && j >= y){
            count++;
            return segArr[sidx];
        }
        if (y < i || x > j){
            count++;
            return 0;
        }
        count++;
        int middle = getMiddleInx(x, y);
        return getSumUtil(x, middle, i, j, 2 * sidx + 1) + getSumUtil(middle + 1, y, i, j, 2 * sidx + 2);
    }

    int getSum(int s, int x, int y){
        if (x < 0 || x > s - 1 || x > y){
            count++;
            return -1;
        }
        count++;
        return getSumUtil(0, s - 1, x, y, 0);
    }

    int constructSegTree(int a[], int x, int y, int sidx){// sidx - индекс текущего узла дерева
        if (x == y){ // случай когда в массиве один элемент
            count++;
            segArr[sidx] = a[x];
            return a[x];
        }
        count++;
        int middle = getMiddleInx(x, y);
        segArr[sidx] = constructSegTree(a, x, middle, sidx * 2 + 1) + constructSegTree(a, middle + 1, y, sidx * 2 + 2);
        return segArr[sidx];
    }

    public int getCount(){
        return count;
    }

    public void deleteVal(int size, int index) {
        if (index < 0 || index > size - 1) {
            count++;
            System.out.println("Input is Invalid");
            return;
        }
        count++;
        valUpdate(segArr,size, index, 0);
    }

    public void insertVal(int a[], int size, int index, int newVal) {
        if (index < 0 || index > size - 1) {
            count++;
            System.out.println("Input is Invalid");
            return;
        }
        count++;
        a[index] = newVal;
        valUpdate(a, size, index, newVal);
    }

    public int searchVal(int size, int val) {
        for (int i = 0; i < size; i++) {
            count++;
            if (segArr[i] == val) {
                count++;
                return i;
            }
        }
        count++;
        System.out.println("Value not found");
        return -1;
    }
}