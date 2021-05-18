package fr.univ_amu.heromanager.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * An ArrayList extended class where we can add listeners on add, set and remove methods
 *
 * @see SimpleListener used listeners
 */
public class ListenableArrayList<T> extends ArrayList<T> {

    /**
     * Simple listeners, performing action on list modification
     */
    private List<SimpleListener> addListeners, setListeners, removeListeners;

    /**
     * Constructor, just init lists of listeners
     */
    public ListenableArrayList() {
        addListeners = new ArrayList<>();
        setListeners = new ArrayList<>();
        removeListeners = new ArrayList<>();
    }

    /**
     * Adding object to list, same as ADD method in ArrayList, but add listeners will be triggered when called
     *
     * @param object object to add
     * @return TRUE if object was added correctly, FALSE otherwise
     */
    @Override
    public boolean add(T object) {
        boolean res = super.add(object);
        addListenersEvent();
        return res;
    }

    /**
     * Adding a collection to list, same as ADDALL method in ArrayList, but add listeners will be triggered when called
     *
     * @param c collection to add
     * @return TRUE if object was added correctly, FALSE otherwise
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = super.addAll(c);
        addListenersEvent();
        return res;
    }

    /**
     * Adding a collection to list, same as ADDALL method in ArrayList, but add listeners will be triggered when called
     *
     * @param c collection to add
     * @return TRUE if object was added correctly, FALSE otherwise
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean res = super.addAll(index, c);
        addListenersEvent();
        return res;
    }

    /**
     * Set object to list from an index, same as SET method in ArrayList, but set listeners will be triggered when called
     *
     * @param index  index to update
     * @param object object to set
     * @return TRUE if object was added correctly, FALSE otherwise
     */
    @Override
    public T set(int index, T object) {
        T res = super.set(index, object);
        setListenersEvent();
        return res;
    }

    /**
     * Removing object to list, same as REMOVE method in ArrayList, but remove listeners will be triggered when called
     *
     * @param object object to remove
     * @return TRUE if object was added correctly, FALSE otherwise
     */
    @Override
    public boolean remove(Object object) {
        boolean res = super.remove(object);
        removeListenersEvent();
        return res;
    }

    /**
     * Removing object to list by index, same as REMOVE method in ArrayList, but remove listeners will be triggered when called
     *
     * @param index index of object to remove
     * @return removed object
     */
    @Override
    public T remove(int index) {
        T res = super.remove(index);
        removeListenersEvent();
        return res;
    }

    /**
     * Adding a listener on ADD method
     *
     * @param listener listener to add
     */
    public void addAddListener(SimpleListener listener) {
        addListeners.add(listener);
    }

    /**
     * Removing a listener on ADD method
     *
     * @param listener listener to remove
     */
    public void removeAddListener(SimpleListener listener) {
        addListeners.remove(listener);
    }

    /**
     * Removing a listener on ADD method by its index
     *
     * @param index index of listener to remove
     * @return removed listener
     */
    public SimpleListener removeAddListener(int index) {
        return addListeners.remove(index);
    }

    /**
     * Returns list of listeners attached to ADD method
     *
     * @return list of listeners
     */
    public List<SimpleListener> getAddListeners() {
        return addListeners;
    }

    /**
     * Adding a listener on SET method
     *
     * @param listener listener to add
     */
    public void addSetListener(SimpleListener listener) {
        setListeners.add(listener);
    }

    /**
     * Removing a listener on SET method
     *
     * @param listener listener to remove
     */
    public boolean removeSetListener(SimpleListener listener) {
        return removeListeners.remove(listener);
    }

    /**
     * Removing a listener on SET method by its index
     *
     * @param index index of listener to remove
     * @return removed listener
     */
    public SimpleListener removeSetListener(int index) {
        return setListeners.remove(index);
    }

    /**
     * Returns list of listeners attached to SET method
     *
     * @return list of listeners
     */
    public List<SimpleListener> getSetListeners() {
        return setListeners;
    }

    /**
     * Adding a listener on REMOVE method
     *
     * @param listener listener to add
     */
    public void addRemoveListener(SimpleListener listener) {
        removeListeners.add(listener);
    }

    /**
     * Removing a listener on REMOVE method
     *
     * @param listener listener to remove
     */
    public boolean removeRemoveListener(SimpleListener listener) {
        return removeListeners.remove(listener);
    }

    /**
     * Removing a listener on SET method by its index
     *
     * @param index index of listener to remove
     * @return removed listener
     */
    public SimpleListener removeRemoveListener(int index) {
        return removeListeners.remove(index);
    }

    /**
     * Returns list of listeners attached to REMOVE method
     *
     * @return list of listeners
     */
    public List<SimpleListener> getRemoveListeners() {
        return removeListeners;
    }

    /**
     * Adding a listener for all modifications methods (ADD, SET and REMOVE)
     *
     * @param listener listener to add
     */
    public void addListenerForAllActions(SimpleListener listener) {
        addAddListener(listener);
        addSetListener(listener);
        addRemoveListener(listener);
    }


    /**
     * To trigger all "add" listeners
     */
    private void addListenersEvent() {
        addListeners.forEach(SimpleListener::event);
    }

    /**
     * To trigger all "set" listeners
     */
    private void setListenersEvent() {
        setListeners.forEach(SimpleListener::event);
    }

    /**
     * To trigger all "remove" listeners
     */
    private void removeListenersEvent() {
        removeListeners.forEach(SimpleListener::event);
    }
}

