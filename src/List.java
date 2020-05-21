public interface List {
    /**
     * inserts element in the list if not there yet
     * @param element
     */
    void add(int element);

    /**
     * @return - size of the list
     */
    int size();

    /**
     * @return true if list is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * removes element from the list, if present
     * @param element
     * @return true if element was removed, false otherwise
     */
    boolean remove(int element);

    /**
     *
     * @return elements of the list separated by commas ","
     */
    String toString();
}
