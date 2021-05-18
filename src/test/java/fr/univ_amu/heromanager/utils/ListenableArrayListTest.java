package fr.univ_amu.heromanager.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

@DisplayName("ListenableArrayList tests")
class ListenableArrayListTest {

    private ListenableArrayList<Integer> list;

    private boolean addEventTriggered, setEventTriggered, removeEventTriggered;

    @BeforeEach
    public void initAttributes() {
        list = new ListenableArrayList<>() {{add(1); add(2); add(3);}};
        list.addAddListener(() -> addEventTriggered = true);
        list.addSetListener(() -> setEventTriggered = true);
        list.addRemoveListener(() -> removeEventTriggered = true);

        addEventTriggered = false;
        setEventTriggered = false;
        removeEventTriggered = false;
    }

    @Test
    @DisplayName("Event trigger tests")
    public void testEvents() {
        Assertions.assertFalse(addEventTriggered);
        Assertions.assertFalse(setEventTriggered);
        Assertions.assertFalse(removeEventTriggered);

        list.add(10);

        Assertions.assertTrue(addEventTriggered);
        Assertions.assertFalse(setEventTriggered);
        Assertions.assertFalse(removeEventTriggered);

        list.remove(Integer.valueOf(10));

        Assertions.assertTrue(addEventTriggered);
        Assertions.assertFalse(setEventTriggered);
        Assertions.assertTrue(removeEventTriggered);

        list.set(0, 20);

        Assertions.assertTrue(addEventTriggered);
        Assertions.assertTrue(setEventTriggered);
        Assertions.assertTrue(removeEventTriggered);
    }

    @Test
    @DisplayName("Check if all listeners called when multiple")
    public void testMultipleListeners() {
        AtomicBoolean boolA = new AtomicBoolean(false);
        AtomicBoolean boolB = new AtomicBoolean(false);
        AtomicBoolean boolC = new AtomicBoolean(false);
        SimpleListener a = () -> boolA.set(true);
        SimpleListener b = () -> boolB.set(true);
        SimpleListener c = () -> boolC.set(true);

        list.addAddListener(a);
        list.addSetListener(b);
        list.addRemoveListener(c);

        Assertions.assertFalse(addEventTriggered);
        Assertions.assertFalse(setEventTriggered);
        Assertions.assertFalse(removeEventTriggered);
        Assertions.assertFalse(boolA.get());
        Assertions.assertFalse(boolB.get());
        Assertions.assertFalse(boolC.get());

        list.add(50);

        Assertions.assertTrue(addEventTriggered);
        Assertions.assertFalse(setEventTriggered);
        Assertions.assertFalse(removeEventTriggered);
        Assertions.assertTrue(boolA.get());
        Assertions.assertFalse(boolB.get());
        Assertions.assertFalse(boolC.get());

        list.set(0, 50);

        Assertions.assertTrue(addEventTriggered);
        Assertions.assertTrue(setEventTriggered);
        Assertions.assertFalse(removeEventTriggered);
        Assertions.assertTrue(boolA.get());
        Assertions.assertTrue(boolB.get());
        Assertions.assertFalse(boolC.get());

        list.remove(0);

        Assertions.assertTrue(addEventTriggered);
        Assertions.assertTrue(setEventTriggered);
        Assertions.assertTrue(removeEventTriggered);
        Assertions.assertTrue(boolA.get());
        Assertions.assertTrue(boolB.get());
        Assertions.assertTrue(boolC.get());
    }

    @Test
    @DisplayName("Check removing listener")
    public void testRemovingListener() {
        AtomicBoolean boolA = new AtomicBoolean(false);
        SimpleListener a = () -> boolA.set(true);

        list.addAddListener(a);

        Assertions.assertFalse(addEventTriggered);
        Assertions.assertFalse(boolA.get());

        list.removeAddListener(a);

        Assertions.assertFalse(addEventTriggered);
        Assertions.assertFalse(boolA.get());

        list.add(50);

        Assertions.assertTrue(addEventTriggered);
        Assertions.assertFalse(boolA.get());
    }

    @Test
    @DisplayName("Check getters returns correct lists")
    public void testGetterListeners() {
        Assertions.assertEquals(1, list.getAddListeners().size());
        Assertions.assertEquals(1, list.getSetListeners().size());
        Assertions.assertEquals(1, list.getRemoveListeners().size());

        list.addSetListener(() -> {});

        Assertions.assertEquals(1, list.getAddListeners().size());
        Assertions.assertEquals(2, list.getSetListeners().size());
        Assertions.assertEquals(1, list.getRemoveListeners().size());

        list.addRemoveListener(() -> {});
        list.addRemoveListener(() -> {});

        Assertions.assertEquals(1, list.getAddListeners().size());
        Assertions.assertEquals(2, list.getSetListeners().size());
        Assertions.assertEquals(3, list.getRemoveListeners().size());
    }

    @Test
    @DisplayName("Check add listener method for all actions")
    public void testListenerAddAll() {
        AtomicBoolean boolA = new AtomicBoolean(false);
        SimpleListener a = () -> boolA.set(true);

        list.addListenerForAllActions(a);
        Assertions.assertFalse(boolA.get());

        list.add(10);
        Assertions.assertTrue(boolA.get());

        boolA.set(false);
        Assertions.assertFalse(boolA.get());

        list.remove(0);
        Assertions.assertTrue(boolA.get());

        boolA.set(false);
        Assertions.assertFalse(boolA.get());

        list.set(0, 0);
        Assertions.assertTrue(boolA.get());
    }
}