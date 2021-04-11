package day12;

interface State<T> {

    void update(int[][] pos, int[][] vel);

    boolean halt();

    T getResult(int[][] pos, int[][] vel);
}
